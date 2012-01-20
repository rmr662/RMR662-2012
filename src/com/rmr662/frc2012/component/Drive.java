/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.library.RMRRobotDrive;
import com.rmr662.frc2012.physical.RMREncoder;
import com.rmr662.frc2012.physical.RMRJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;

/**
 * This class represents the drive wheels of the robot and knows how to control
 * them so that they will adapt to a given target speed.
 * @author mcoffin
 */
public class Drive extends Component {
    
    private volatile static Drive instance;
    
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    
    private static final double DISTANCE_PER_PULSE = 0.001198473; //0.001198473 0.000465839
    
    private static final double[] KP = {0.05, 0.05};
    private static final double[] KI = {0.0, 0.0};
    private static final double[] KD = {0.0, 0.0};
    
    private static final double SPEED_MIN = -1d;
    private static final double SPEED_MAX = -SPEED_MIN;
    
    private static final int[] MOTOR_CHANNELS = {1, 2};
    private static final int[] ENCODER_CHANNELS_A = {3, 5};
    private static final int[] ENCODER_CHANNELS_B = {4, 6};
    
    private RMRJaguar[] motors = new RMRJaguar[MOTOR_CHANNELS.length];
    private RMREncoder[] encoders = new RMREncoder[ENCODER_CHANNELS_A.length];
    private PIDController[] controllers = new PIDController[MOTOR_CHANNELS.length];
    private RMRRobotDrive robotDrive;
    private double[] targetValues = {0d, 0d};
    
    private Drive() {
        for (int i = 0; i < MOTOR_CHANNELS.length; i++) {
            motors[i] = new RMRJaguar(MOTOR_CHANNELS[i]);
            motors[i].setInverted(true);
            encoders[i] = new RMREncoder(ENCODER_CHANNELS_A[i], ENCODER_CHANNELS_B[i]);
            encoders[i].setDistancePerPulse(DISTANCE_PER_PULSE);
            encoders[i].start();
            encoders[i].setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
            controllers[i] = new PIDController(KP[i], KI[i], KD[i], encoders[i], motors[i]);
            controllers[i].enable();
            controllers[i].setInputRange(SPEED_MIN, SPEED_MAX);
        }
        encoders[LEFT].setReverseDirection(true);
        robotDrive = new RMRRobotDrive(controllers[LEFT], controllers[RIGHT]);
    }
    
    public void update() {
        robotDrive.tankDrive(targetValues[LEFT], targetValues[RIGHT]);
        System.out.println(encoders[LEFT].getRate() + " " + encoders[RIGHT].getRate());
    }
    
    public void reset() {
        for (int i = 0; i < targetValues.length; i++) {
            targetValues[i] = 0d;
        }
    }
    
    public synchronized void setTargetValues(double leftValue, double rightValue) {
        targetValues[LEFT] = leftValue;
        targetValues[RIGHT] = rightValue;
    }
    
    public synchronized void setTargetValues(Joystick[] joysticks) {
        for (int i = 0; i < joysticks.length; i++) {
            targetValues[i] = -(joysticks[i].getY());
        }
    }
    
    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }
        return instance;
    }
    
    public String getRMRName() {
        return "Drive";
    }
    
}