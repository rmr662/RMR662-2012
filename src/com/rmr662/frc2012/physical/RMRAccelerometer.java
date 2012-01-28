/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * This class should read data from the accelerometer.
 *
 * @author RMR662 0x1D
 */
public class RMRAccelerometer {

    private ADXL345_I2C accel;

    /**
     * assigns the range for the measurements on the accelerometer
     */
    public RMRAccelerometer(int port) {
        accel = new ADXL345_I2C(port, ADXL345_I2C.DataFormat_Range.k2G);
    }

    /**
     * returns the measurements on the x-axis
     * @return 
     */
    public double getXAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kX);
    }

    /**
     * returns the measurements on the y-axis
     * @return 
     */
    public double getYAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kY);
    }

    /**
     * returns the measurements on the z-axis
     * @return 
     */
    public double getZAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kZ);
    }
}
