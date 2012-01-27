/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author RMR Programming
 */
public class RMRCompressor extends Component {
    
    private static final int SWITCH = 0;
    private static final int RELAY = 1;
    
    private static final int[] COMPRESSOR_CHANNEL = {7,1};
    private static final int COMPRESSOR_SWITCH_CHANNEL = 12;
    
    private Compressor compressor;
    private DigitalInput disableSwitch;
    
    private static RMRCompressor instance;
    
    public RMRCompressor() {
        compressor = new Compressor(COMPRESSOR_CHANNEL[SWITCH], COMPRESSOR_CHANNEL[RELAY]);
        disableSwitch = new DigitalInput(COMPRESSOR_SWITCH_CHANNEL);
    }
    
    public void update() {
        if (disableSwitch.get()) {
            compressor.stop();
        } else {
            if (!compressor.enabled()) {
                compressor.start();
            }
        }
    }
    
    public void reset() {
        compressor.stop();
    }
    
    public String getRMRName() {
        return "Compressor";
    }
    
    public static RMRCompressor getInstance() {
        if (instance == null) {
            instance = new RMRCompressor();
        }
        return instance;
    }
}
