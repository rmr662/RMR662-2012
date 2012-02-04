/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMREncoder;
import com.rmr662.frc2012.physical.RMRJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;

/**
 * This class represents the drive wheels of the robot and knows how to control
 * them so that they will adapt to a given target speed.
 *
 * @author mcoffin
 */
public class Drive extends Component {

    private volatile static Drive instance;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private static final double DISTANCE_PER_PULSE = 0.001198473; //0.001198473 0.000465839
    private boolean pidEnabled = true;
    private static final double[] KP = {0.05, 0.05};
    private static final double[] KI = {0.0, 0.0};
    private static final double[] KD = {0.0, 0.0};
    private static final double MAX_SPEED = 2d;
    private static final int[] MOTOR_CHANNELS = {1, 2};
    private static final int[] ENCODER_CHANNELS_A = {6, 8};
    private static final int[] ENCODER_CHANNELS_B = {7, 9};
    private RMRJaguar[] motors = new RMRJaguar[MOTOR_CHANNELS.length];
    private RMREncoder[] encoders = new RMREncoder[ENCODER_CHANNELS_A.length];
    private PIDController[] controllers = new PIDController[MOTOR_CHANNELS.length];
    private double[] targetValues = {0d, 0d};
    
    /**
     * Creates a new Drive component with motors and encoders on the default
     * channels
     */
    private Drive() {
        for (int i = 0; i < MOTOR_CHANNELS.length; i++) {
            motors[i] = new RMRJaguar(MOTOR_CHANNELS[i]);
            motors[i].setInverted(true);
            encoders[i] = new RMREncoder(ENCODER_CHANNELS_A[i], ENCODER_CHANNELS_B[i]);
            encoders[i].setDistancePerPulse(DISTANCE_PER_PULSE);
            encoders[i].start();
            encoders[i].setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
            controllers[i] = new PIDController(KP[i], KI[i], KD[i], encoders[i], motors[i]);
            if (pidEnabled) {
                controllers[i].enable();
            }
            controllers[i].setInputRange(-MAX_SPEED, MAX_SPEED);
        }
        encoders[LEFT].setReverseDirection(true);
    }

    public void update() {
        tankDrive(targetValues[LEFT], targetValues[RIGHT]);
    }

    public void reset() {
        for (int i = 0; i < targetValues.length; i++) {
            targetValues[i] = 0d;
        }
    }
    
    /**
     * Set the target values for the motors.
     * @param leftValue desired left motor target value
     * @param rightValue desired right motor target value
     */
    public synchronized void setTargetValues(double leftValue, double rightValue) {
        targetValues[LEFT] = leftValue;
        targetValues[RIGHT] = rightValue;
        System.out.println("Left : " + leftValue);
        System.out.println("Right: " + rightValue);
    }
    
    /**
     * Sets motor values based on a left and right target value.
     * @param leftValue
     * @param rightValue 
     */
    public void tankDrive(double leftValue, double rightValue) {
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);
        if (rightValue >= 0d) {
            rightValue *= rightValue;
        } else {
            rightValue = -(rightValue * rightValue);
        }
        if (leftValue >= 0d) {
            leftValue *= leftValue;
        } else {
            leftValue = -(leftValue * leftValue);
        }
        if (pidEnabled) {
            controllers[LEFT].setSetpoint(leftValue);
            controllers[RIGHT].setSetpoint(rightValue);
        } else {
            motors[LEFT].set(leftValue);
            motors[RIGHT].set(rightValue);
        }
    }

    private static double limit(double num) {
        if (num < -1d) {
            return -1d;
        }
        if (num > 1d) {
            return 1d;
        }
        return num;
    }

    /**
     * Adds deltas to the gains on the PID controllers
     * @param p change in KP
     * @param i change in KI
     * @param d change in KD
     */
    public void setRelativePIDValues(double p, double i, double d) {
        for (int j = 0; j < controllers.length; ++j) {
            controllers[j].setPID(controllers[j].getP() + p, controllers[j].getI() + i, controllers[j].getD() + d);
        }
    }

    /**
     * Enables or disables the PID
     * @param enabled whether or not the PID should be enabled.
     */
    public void setPID(boolean enabled) {
        pidEnabled = enabled;
        if (enabled) {
            for (int i = 0; i < controllers.length; ++i) {
                controllers[i].enable();
            }
        } else {
            for (int i = 0; i < controllers.length; ++i) {
                controllers[i].disable();
            }
        }
    }
    
    /**
     * 
     * @return The current P value for the left controller
     */
    public double getP(){
        return controllers[LEFT].getP();
    }
    
    /**
     * 
     * @return The current I value for the left controller
     */
    public double getI() {
        return controllers[LEFT].getI();
    }
    
    /**
     * 
     * @return The current D value for the left controller
     */
    public double getD() {
        return controllers[LEFT].getD();
    }

    /**
     * Enables the PID
     */
    public void enablePID() {
        setPID(true);
    }
    
    /**
     * Disables the PID
     */
    public void disablePID() {
        setPID(false);
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