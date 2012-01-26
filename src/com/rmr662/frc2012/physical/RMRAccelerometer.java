/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * sss This class should read data from the accelerometer.
 *
 * @author RMR662 0x1D
 */
public class RMRAccelerometer {

    private ADXL345_I2C accel;

    public RMRAccelerometer(int port) {
        accel = new ADXL345_I2C(port, ADXL345_I2C.DataFormat_Range.k2G);
    }

    public double getXAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kX);
    }

    public double getYAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kY);
    }

    public double getZAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kZ);
    }
}
