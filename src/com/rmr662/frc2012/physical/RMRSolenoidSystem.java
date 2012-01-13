/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Represents a a system of two solenoids that should always have different
 * values.
 * @author RMR662
 */
public class RMRSolenoidSystem {
    
    /**
     * The non-inverted Solenoid
     */
    private Solenoid correspondent;
    /**
     * The inverted Solenoid
     */
    private Solenoid inverted;
    
    /**
     * Creates a new RMRSolenoidSystem with two pre-initialized solenoids.
     * @param correspondent the non-inverted solenoid
     * @param inverted the inverted solenoid
     */
    public RMRSolenoidSystem(Solenoid correspondent, Solenoid inverted) {
        this.correspondent = correspondent;
        this.inverted = inverted;
    }
    
    /**
     * Sets the value of the solenoid system.
     * @param on the new value of the solenoid system
     */
    public void set(boolean on) {
        correspondent.set(on);
        inverted.set(!on);
    }
    
    /**
     * Toggles the value of the solenoid system
     */
    public void toggle() {
        set(inverted.get());
    }
    
}
