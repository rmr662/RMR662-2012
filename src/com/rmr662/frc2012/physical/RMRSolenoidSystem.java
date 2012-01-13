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
    
    private Solenoid correspondent;
    private Solenoid inverted;
    
    public RMRSolenoidSystem(Solenoid correspondent, Solenoid inverted) {
        this.correspondent = correspondent;
        this.inverted = inverted;
    }
    
    public void set(boolean on) {
        correspondent.set(on);
        inverted.set(!on);
    }
    
}
