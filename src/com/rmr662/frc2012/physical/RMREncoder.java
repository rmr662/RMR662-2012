/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author RMR Programming
 */
public class RMREncoder extends Encoder {
    
    private int callCount = 0;
    private final int UPDATE_RATE = 5;
    private double rate;
    private double prev_rate;
    
    /**
     * Creates a new Encoder.
     * @param aChannel 'A' channel for the encoder
     * @param bChannel 'B' channel for the encoder
     */
    public RMREncoder(int aChannel, int bChannel) {
        super(aChannel, bChannel);
    }
    
    /**
     * Gets rate from the encoder. Only updates every UPDATE_RATE cycles so as
     * to decrease the tolerance in measurement.
     * @return 
     */
    public double getRate() {
        //if we want to update the value sent to the PIDController
        rate += super.getRate();
        callCount++;
        if(callCount == UPDATE_RATE) {
            prev_rate = rate / UPDATE_RATE;
            rate = 0.0;
            callCount = 0;
        }
        return prev_rate;
    }
    
    /**
     * Gets the raw rate from the encoder. Simply measures the time between the
     * last encoder clicks.
     * @return the raw rate from the encoder.
     */
    public double getLegacyRate() {
        return super.getRate();
    }
    
}
