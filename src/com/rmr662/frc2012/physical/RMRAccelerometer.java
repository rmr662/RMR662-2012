/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * This class should read data from the accelerometer.
 *
 * @author RMR-Programming
 */
public class RMRAccelerometer {

    /**
     * Default slot for IC2 chips
     */
    private static final int SLOT = 1;
    private ADXL345_I2C accel;
    private static RMRAccelerometer instance = null;
    private static double xBase = 0;
    private static double yBase = 0;
    private static double zBase = 0;

    /**
     * Singleton accessor method
     */
    public static RMRAccelerometer getInstance() {
        if (instance == null) {
            instance = new RMRAccelerometer();
        }
        return instance;
    }

    /**
     * Creates an RMRAccelerometer with the default slot.
     */
    private RMRAccelerometer() {
        this(SLOT);
    }

    /**
     * Creates a new RMRAccelerometer
     *
     * @param slot the slot to which the I2C cables are connected.
     */
    private RMRAccelerometer(int slot) {
        accel = new ADXL345_I2C(slot, ADXL345_I2C.DataFormat_Range.k2G);
        getZeroedVales();
    }

    /**
     * returns the measurements on the x-axis
     *
     * @return
     */
    public double getXAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kX);
    }

    /**
     * returns the measurements on the y-axis
     *
     * @return
     */
    public double getYAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kY);
    }

    /**
     * returns the measurements on the z-axis
     *
     * @return
     */
    public double getZAccel() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kZ);
    }

    private void getZeroedVales() {
        for (int i = 0; i < 100; i++) {
            xBase += getXAccel();
            yBase += getYAccel();
            zBase += getZAccel();
        }
        xBase /= 100;
        yBase /= 100;
        zBase /= 100;

    }

    public double getAdjXAccel() {
        return (getXAccel() - xBase);
    }

    public double getAdjYAccel() {
        return (getYAccel() - yBase);
    }

    public double getAdjZAccel() {
        return (getZAccel() - zBase);
    }
}