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
     * @param port
     * @param inverted 
     */
    public RMRLimitSwitch(int port, boolean inverted) {
        digitalIn = new DigitalInput(port);
        this.inverted = inverted;
    }
    /**
     * Creates new digital input
     * sets inverted as false
     * @param port 
     */
    public RMRLimitSwitch(int port) {
        digitalIn = new DigitalInput(port);
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
