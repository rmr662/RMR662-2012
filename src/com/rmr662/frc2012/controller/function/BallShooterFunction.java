/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.ShooterArm;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ROBOTics
 */
public class BallShooterFunction extends Function {
    
    private static final int JOYSTICK_BUTTON = 1;
    
    private Joystick joystick;
    
    public BallShooterFunction(Joystick joystick) {
        this.joystick = joystick;
    }
    
    public void defaultState() {
        ShooterArm.getInstance().reset();
    }
    
    public void update() {
        ShooterArm.getInstance().setShooting(joystick.getRawButton(JOYSTICK_BUTTON));
    }
    
}
