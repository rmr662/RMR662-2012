/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author RMR Programming
 */
public class RMRPneumatics {
    
    int channel;
    Solenoid sole;
            
    /**
     * assigns the channel
     * @param channel 
     */
    public RMRPneumatics(int channel){
        this.channel = channel;
        Solenoid sole = new Solenoid(channel);
    }
    
    /**
     * returns the status of the solenoid
     * @return 
     */
    public boolean getStatus() {
        return sole.get();
    }
    
    /**
     * sets the status of the solenoid
     * @param status 
     */
    public void setStatus(boolean status){
        sole.set(status);
    }
            
}
