/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRJaguar;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author RMR Programming
 */
public class ShooterTurret extends Component {
    
    private static ShooterTurret instance;
    
    private static final int SWITCH_CHANNEL_LEFT = 1;
    private static final int SWITCH_CHANNEL_RIGHT = 2;
    private static final int SWITCH_CHANNEL_CENTRE = 3;
    private static final int MOTOR_CHANNEL = 4;
    
    private RMRJaguar motor;
    private DigitalInput limitSwitchLeft;
    private DigitalInput limitSwitchRight;
    private DigitalInput limitSwitchCentre;
    
    private double targetSpeed = 0.0;
    
    public ShooterTurret () {
        motor = new RMRJaguar(MOTOR_CHANNEL);
        limitSwitchLeft = new DigitalInput(SWITCH_CHANNEL_LEFT);
        limitSwitchRight = new DigitalInput(SWITCH_CHANNEL_RIGHT);
        limitSwitchCentre = new DigitalInput(SWITCH_CHANNEL_CENTRE);
    }
    
    public void update() {
        if (getOuterSwitches()) {
            motor.stopMotor();
        } else {
            motor.set(targetSpeed);
        }
    }

    public void setTarget(double target) {
        targetSpeed = target;
    }
    public void reset() {
    }

    public String getRMRName() {
        return "Turret Platform";
    }
    
    public static ShooterTurret getInstance() {
        if (instance == null) {
            instance = new ShooterTurret();
        }
        return instance;
    }
    
    public boolean getOuterSwitches() {
        if (limitSwitchLeft.get()) {
            return true;
        }
        if (limitSwitchRight.get()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean getSwitches() {
        if (limitSwitchLeft.get()) {
            return true;
        }
        if (limitSwitchRight.get()) {
            return true;
        }
        if (limitSwitchCentre.get()) {
            return true;
        }
        else {
            return false;
        }
    }

}
