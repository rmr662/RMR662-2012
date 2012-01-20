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
             
    public RMRPneumatics(int channel){
        this.channel = channel;
        Solenoid sole = new Solenoid(channel);
    }
    
    public boolean getStatus() {
        return sole.get();
    }
    
    public void setStatus(boolean status){
        sole.set(status);
    }
            
}
