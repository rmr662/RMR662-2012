/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.generic.Function;
import com.rmr662.frc2012.physical.RMRSolenoidSystem;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class PickupResetFunction extends Function {
    
    private static final int WRIST_SOLENOIDS = 1;
    private static final int ELBOW_SOLENOIDS = 0;
    
    private boolean isPressed[] = new boolean[3];
    private RMRSolenoidSystem[] solenoids;
    private Joystick joystick;
    
    public PickupResetFunction(RMRSolenoidSystem[] solenoids, Joystick joystick) {
        this.solenoids = solenoids;
        this.joystick = joystick;
        for (int i=0; i<isPressed.length; ++i) {
            isPressed[i] = false;
        }
    }
    
    protected void update() {
        for(int i=0; i<isPressed.length; ++i) {
            if (joystick.getRawButton(i+1) && !isPressed[i]) {
                switch(i+1){
                    case 3:
                        this.reset();
                    default:
                        break;
                }
                isPressed[i] = joystick.getRawButton(i+1);
            }
        }
    }
    
    protected void defaultState() {
        for(int i=0; i<solenoids.length; ++i) {
            solenoids[i].set(false);
        }
        for(int i=0; i<isPressed.length; ++i) {
            isPressed[i] = false;
        }
    }
    
    private void reset() {
        solenoids[ELBOW_SOLENOIDS].set(false);
        solenoids[WRIST_SOLENOIDS].set(false);
    }
}
