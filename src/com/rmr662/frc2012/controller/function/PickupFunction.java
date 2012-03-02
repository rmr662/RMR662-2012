/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.BallBucket;
import com.rmr662.frc2012.component.Turret;
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
    }

    protected void update() {
        BallBucket.getInstance().setElbowTarget(joystick.getRawButton(2) && !Turret.getInstance().isCentered());
        //      BallBucket.getInstance().setElbowTarget(joystick.getRawButton(2));
    }

    protected void defaultState() {
        BallBucket.getInstance().reset();
    }
}
