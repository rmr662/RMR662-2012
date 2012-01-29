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
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final double DISTANCE_PER_PULSE = 0.001198473; //0.001198473 0.000465839
    private boolean pidEnabled = false;
    private static final double[] KP = {0.05, 0.05};
    private static final double[] KI = {0.0, 0.0};
    private static final double[] KD = {0.0, 0.0};
    private static final double MAX_SPEED = 2d;
    private static final int[] MOTOR_CHANNELS = {1, 2};
    private static final int[] ENCODER_CHANNELS_A = {3, 5};
    private static final int[] ENCODER_CHANNELS_B = {4, 6};
    private RMRJaguar[] motors = new RMRJaguar[MOTOR_CHANNELS.length];
    private RMREncoder[] encoders = new RMREncoder[ENCODER_CHANNELS_A.length];
    private PIDController[] controllers = new PIDController[MOTOR_CHANNELS.length];
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
            controllers[i].setInputRange(-MAX_SPEED, MAX_SPEED);
        }
        encoders[LEFT].setReverseDirection(true);
    }

    public void update() {
        tankDrive(targetValues[LEFT], targetValues[RIGHT]);
        System.out.println("PID: " + controllers[LEFT].getP() + ", " + controllers[LEFT].getI() + ", " + controllers[LEFT].getD());
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

    public void setRelativePIDValues(double p, double i, double d) {
        for (int j = 0; j < controllers.length; ++j) {
            controllers[j].setPID(controllers[j].getP() + p, controllers[j].getI() + i, controllers[j].getD() + d);
        }
    }

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

    public void enablePID() {
        setPID(true);
    }

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