/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;

/**sss
 * This class should read data from the accelerometer.
 * @author RMR662
 * 0x1D
 */
public class RMRAccelerometer {
    
    public double getAcceleration() {
        // TODO: read acceleration from accelerometer.
        return 0.0;
        I2C i2c = new I2C(new ADXL345_I2C(),0x1D );
        new ADXL345_I2C(slot, ADXL345_I2C.DataFormat_Range.k2G)
        
    }
    
}
