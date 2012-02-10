/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRJaguar;
import com.rmr662.frc2012.physical.RMRLimitSwitch;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author RMR Programming
 */
public class Turret extends Component {

    private static Turret instance;
    private static final int SWITCH_CHANNEL_LEFT = 1;
    private static final int SWITCH_CHANNEL_RIGHT = 2;
    private static final int SWITCH_CHANNEL_CENTER = 3;
    private static final int MOTOR_CHANNEL = 4;
    private RMRJaguar motor;
    private RMRLimitSwitch limitSwitchLeft;
    private RMRLimitSwitch limitSwitchRight;
    private RMRLimitSwitch limitSwitchCenter;
    private double targetSpeed = 0.0;

    public Turret() {
        motor = new RMRJaguar(MOTOR_CHANNEL);
        limitSwitchLeft = new RMRLimitSwitch(SWITCH_CHANNEL_LEFT, false);
        limitSwitchRight = new RMRLimitSwitch(SWITCH_CHANNEL_RIGHT, false);
        limitSwitchCenter = new RMRLimitSwitch(SWITCH_CHANNEL_CENTER, false);
    }

    public void update() {
        double localTargetSpeed = 0d;
        synchronized(this) {
            localTargetSpeed = targetSpeed;
        }
        // Asuming left is negative and right is positive
        if (localTargetSpeed < 0 && !limitSwitchLeft.get()) {
            motor.set(localTargetSpeed);
        } else if (localTargetSpeed > 0 && !limitSwitchRight.get()) {
            motor.set(localTargetSpeed);
        } else {
            motor.set(0d);
        }
    }

    public synchronized void setTarget(double target) {
        targetSpeed = target;
    }

    public synchronized void reset() {
        targetSpeed = 0d;
    }

    public String getRMRName() {
        return "Turret Platform";
    }
    
    public boolean isCentered() {
        return limitSwitchCenter.get();
    }

    public static Turret getInstance() {
        if (instance == null) {
            instance = new Turret();
        }
        return instance;
    }
    
}