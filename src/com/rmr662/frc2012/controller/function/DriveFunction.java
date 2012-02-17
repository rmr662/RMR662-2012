/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class DriveFunction extends Function {

    private Joystick[] joysticks;

    /**
     * constructor
     *
     * @param joysticks
     */
    public DriveFunction(Joystick[] joysticks) {
        this.joysticks = joysticks;
    }

    /**
     * sets target values for the drive instance and the joystick
     */
    protected void update() {
        if (Drive.tankDrive) {
            Drive.getInstance().setTargetValues(-joysticks[Drive.LEFT].getY(),
                    -joysticks[Drive.RIGHT].getY());
        } else {
            Drive.getInstance().setTargetValues(-joysticks[Drive.LEFT].getY(),
                    -joysticks[Drive.LEFT].getX());
        }
    }

    /**
     * resets the target values for the instance to their default state
     */
    protected void defaultState() {
        Drive.getInstance().reset();
    }
}
