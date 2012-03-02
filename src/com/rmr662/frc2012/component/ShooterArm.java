/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRJaguar;
import com.rmr662.frc2012.physical.RMRLimitSwitch;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author RMR Programming
 */
public class ShooterArm extends Component {

    private static ShooterArm instance;
    //TODO: put in motor channel, switch channel, and motor speed
    private static int MOTOR_CHANNEL = 3;
    private static int SWITCH_CHANNEL = 4;
    private static double MOTOR_SPEED = 1d;
    private static boolean MOTOR_INVERTED = false;
    private RMRJaguar motor;
    private RMRLimitSwitch limitSwitch;
    private boolean shootingTarget = false;
    private DigitalInput autoSwitch = new DigitalInput(13);
    private boolean inverted = false;

    /**
     * sets limitSwitch as a digital input
     */
    public ShooterArm() {
        motor = new RMRJaguar(MOTOR_CHANNEL);
        motor.setInverted(MOTOR_INVERTED);
        limitSwitch = new RMRLimitSwitch(SWITCH_CHANNEL, false);
    }

    /**
     * if the limit switch is hit stops motor, if not sets the motor speed
     */
    public void update() {
        boolean localShootingTarget;
        synchronized (this) {
            localShootingTarget = shootingTarget;
        }
        // System.out.println("Auto: "+autoSwitch.get() +"\t"+"Trigger: "+localShootingTarget);        
        if (BallLoader.getInstance().isReadyToFire()) {
            if (localShootingTarget) {
                if (!inverted) {
                    motor.set(MOTOR_SPEED);
                } else {
                    motor.set(-MOTOR_SPEED);
                }
            } else {
                motor.set(0d);
                motor.stopMotor();
            }
        } else {
            motor.set(0d);
        }
//        if (!BallLoader.getInstance().isReadyToFire()) {
//            localShootingTarget = false;
//        }
//        if (limitSwitch.get() && !localShootingTarget) {
//            motor.set(0d);
//            motor.stopMotor();
//        } else {
//            motor.set(MOTOR_SPEED);
//        }
    }

    /*
     * stops motor if not shooting
     */
    public void reset() {
        synchronized (this) {
            shootingTarget = false;
        }
        motor.set(0d);
    }

    public String getRMRName() {
        return "Shooter Arm";
    }

    /*
     * S
     * creates new instance ShooterArm
     */
    public static ShooterArm getInstance() {
        if (instance == null) {
            instance = new ShooterArm();
        }
        return instance;
    }

    /*
     * sets target values as shoot for shooting
     */
    public synchronized void setShooting(boolean shootingTarget) {
        this.shootingTarget = shootingTarget;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public synchronized boolean isShooting() {
        return shootingTarget;
    }
}
