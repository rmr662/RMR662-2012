/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.CameraComponent;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class CameraFunction extends Function {
    
    private Joystick joystick;
    
    public CameraFunction(Joystick joystick) {
        this.joystick = joystick;
    }
    
    protected void defaultState() {
        CameraComponent.getInstance().reset();
    }

    protected void update() {
        double y = joystick.getY() + 1;
        y /= 2;

        double x = joystick.getX() + 1;
        x /= 2;

        CameraComponent.getInstance().setPanTargetState(x);
        CameraComponent.getInstance().setTiltTargetState(y);

    }
}
