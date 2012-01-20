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

    public String getRMRName() {
        return "Compressor";
    }
    
    public boolean getStatus(){
        return compressed;
    }
    
    public void startComp(){
        compress.start();
        compressing = true;
    }
}
