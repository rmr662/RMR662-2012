/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Digital input requ port. This is a limit switch. Need a return if on/off.
 * need to be able to invert potentially...
 *
 * @author RMR Programming
 */
public class RMRLimitSwitch {

    DigitalInput digitalIn;
    boolean inverted;

    /**
     * Creates new digital input
     * @param channel
     * @param inverted 
     */
    public RMRLimitSwitch(int channel, boolean inverted) {
        digitalIn = new DigitalInput(channel);
        this.inverted = inverted;
    }
    /**
     * Creates new digital input that isn't inverted.
     * @param channel the channel number for the digital input
     */
    public RMRLimitSwitch(int channel) {
        digitalIn = new DigitalInput(channel);
        this.inverted = false;
    }
    /**
     * Returns status of the switch
     * @param inverted
     * @return 
     */

    public boolean getSwitch(boolean inverted){
        if (inverted){
            return !digitalIn.get();
        }
        return digitalIn.get();
    }
}
