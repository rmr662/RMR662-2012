/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.component.TransmissionComponent;
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
        TransmissionComponent.getInstance().reset();
    }

    protected void update() {
        if (joystick.getRawButton(2)) {
            currentState = true;
            if (!lastState && currentState) {

                TransmissionComponent.getInstance().toggleTransmission();

            }
        } else {
            currentState = false;
        }
        lastState = currentState;
    }
}
