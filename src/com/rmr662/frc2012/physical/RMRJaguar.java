/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * This class adds the functionality of inverting a motor to the default 
 * WPIlib Jaguar class.
 * @author mcoffin
 */
public class RMRJaguar extends Jaguar {
    
    private boolean inverted = false;
    private double lastOutput = 0;
    
    public RMRJaguar(int channel) {
        super(channel);
    }
    
    /**
     * Sets the inverted nature of the motor.
     * @param inverted whether or not the motor should be inverted.
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    
    public void pidWrite(double output) {
        lastOutput += output;
        super.pidWrite(lastOutput);
    }
    
    public void set(double speed) {
        super.set(inverted?(-speed):speed);
    }
    
    public void set(double speed, byte syncGroup) {
        super.set(inverted?(-speed):speed, syncGroup);
    }
    
}
