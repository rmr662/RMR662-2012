/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import com.rmr662.frc2012.generic.Component;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author RMR Programming
 */
public class RMRCompressor extends Component{
    
    int pressureSwitchSlot;
    int pressureSwitchChannel;
    int compressorRelaySlot;
    int compressorRelayChannel;
    Compressor compress;
    boolean compressing;
    boolean compressed;
    
    /**
     * assigns target values for booleans compressing and compressed
     * assigns slots and channels
     * @param pressureSwitchSlot
     * @param pressureSwitchChannel
     * @param compresssorRelaySlot
     * @param compressorRelayChannel 
     */

    public RMRCompressor(int pressureSwitchSlot, int pressureSwitchChannel, int compresssorRelaySlot, int compressorRelayChannel){
         this.pressureSwitchSlot = pressureSwitchSlot;
         this.pressureSwitchChannel = pressureSwitchChannel;
         this.compressorRelaySlot = compressorRelaySlot;
         this.compressorRelayChannel = compressorRelayChannel;
         Compressor compress = new Compressor(pressureSwitchSlot, pressureSwitchChannel, compresssorRelaySlot, compressorRelayChannel);
         compressing = false;
         compressed = false;
    }

    public void update() {
        //if ( (compressing) && (compress.getPressureSwitchValue() < 120) ){
            
        //FINISH THIS    
        
    }

    public void reset() {
    }

    /**
     * return value compressor for getRMRName
     * @return 
     */
    public String getRMRName() {
        return "Compressor";
    }
    
    /**
     * gets current status for whether or not the compressor is done compressing
     * @return 
     */
    public boolean getStatus(){
        return compressed;
    }
    
    /**
     * starts the compressor, sets boolean compressing as true
     */
    public void startComp(){
        compress.start();
        compressing = true;
    }
}
