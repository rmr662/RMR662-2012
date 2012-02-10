/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author ROBOTics
 */
public class RMRLimitSwitch extends DigitalInput {
    
    private boolean inverted;
    
    public RMRLimitSwitch(int channel, boolean inverted) {
        super(channel);
        this.inverted = inverted;
    }
    
    public boolean get() {
        return (inverted)?(!super.get()):super.get();
    }
    
}
