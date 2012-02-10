/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.BallBucket;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class PickupFunction extends Function {

    private boolean isPressed[] = new boolean[2];
    private Joystick joystick;

    public PickupFunction(Joystick joystick) {
        this.joystick = joystick;
        for (int i = 0; i < isPressed.length; ++i) {
            isPressed[i] = false;
        }
    }

    protected void update() {
        for (int i = 0; i < isPressed.length; ++i) {
            if (joystick.getRawButton(i + 1) && !isPressed[i]) {
                switch (i + 1) {
                    case 1:
                        this.activate();
                        break;
                    default:
                        break;
                }
                isPressed[i] = joystick.getRawButton(i + 1);
            }
        }
    }

    protected void defaultState() {
        BallBucket.getInstance().reset();
    }

    private void activate() {
        BallBucket.getInstance().setElbowTarget(true);
    }
}
