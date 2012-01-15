/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author mcoffin
 */
public class RMRJaguar extends Jaguar {
    
    private boolean inverted = false;
    
    public RMRJaguar(int channel) {
        super(channel);
    }
    
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    
    public void set(double speed) {
        super.set(inverted?(-speed):speed);
    }
    
    public void set(double speed, byte syncGroup) {
        super.set(inverted?(-speed):speed, syncGroup);
    }
    
}
