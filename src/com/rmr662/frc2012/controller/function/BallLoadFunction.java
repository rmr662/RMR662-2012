/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.BallLoader;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ROBOTics
 */
public class BallLoadFunction extends Function {
    
    private static final int JOYSTICK_BUTTON = 1; // TODO
    
    private Joystick joystick;
    
    public BallLoadFunction(Joystick joystick) {
        this.joystick = joystick;
    }
    
    public void defaultState() {
        BallLoader.getInstance().reset();
    }
    
    public void update() {
        BallLoader.getInstance().setTargetState(joystick.getRawButton(JOYSTICK_BUTTON));
    }
    
}
