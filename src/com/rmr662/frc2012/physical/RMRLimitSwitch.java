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

    public RMRLimitSwitch(int port, boolean inverted) {
        digitalIn = new DigitalInput(port);
        this.inverted = inverted;
    }
    public RMRLimitSwitch(int port) {
        digitalIn = new DigitalInput(port);
        this.inverted = false;
    }

    public boolean getSwitch(boolean inverted){
        if (inverted){
            return !digitalIn.get();
        }
        return digitalIn.get();
    }
}
