/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.component.Transmission;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class TransmissionFunction extends Function {

    private Joystick joystick;
    private boolean currentState, lastState = false;

    public TransmissionFunction(Joystick joystick1) {
        this.joystick = joystick1;
    }

    protected void defaultState() {
        Transmission.getInstance().reset();
    }

    protected void update() {
        currentState = joystick.getRawButton(2);
        if (lastState && !currentState) {
            Transmission.getInstance().toggleTransmission();
        }
        lastState = currentState;
    }
}
