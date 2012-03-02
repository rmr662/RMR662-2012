/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.ShooterArm;
import com.rmr662.frc2012.component.Transmission;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ROBOTics
 */
public class BallShooterFunction extends Function {

    private static final int JOYSTICK_BUTTON = 1;
    private Joystick joystick;
    private boolean currentState = false, lastState = false, isShooting = false;
    long start;

    public BallShooterFunction(Joystick joystick) {
        this.joystick = joystick;
    }

    public void defaultState() {
        ShooterArm.getInstance().reset();
    }

    public void update() {

        ShooterArm.getInstance().setInverted(joystick.getRawButton(2));

        currentState = joystick.getRawButton(JOYSTICK_BUTTON);
        if (lastState && !currentState) {
            if (!isShooting) {
                isShooting = true;
                start = System.currentTimeMillis();
                ShooterArm.getInstance().setShooting(true);
            }
        }

        lastState = currentState;
        if (isShooting) {
            if ((System.currentTimeMillis() - start) > 450) {
                ShooterArm.getInstance().setShooting(false);
                isShooting = false;
            }
        }
        //ShooterArm.getInstance().setShooting(joystick.getRawButton(JOYSTICK_BUTTON));
    }
}
