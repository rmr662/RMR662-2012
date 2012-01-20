/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.Accelerometer;

/**
 * sss This class should read data from the accelerometer.
 *
 * @author RMR662 0x1D
 */
public class RMRAccelerometer {

    Accelerometer xAccel;
    Accelerometer yAccel;
    Accelerometer zAccel;
    int port;
    int channel;
    private static final double X_ZERO = 0.0;
    private static final double Y_ZERO = 0.0;
    private static final double Z_ZERO = 0.0;
    private static final double X_SENSE = 0.5;
    private static final double Y_SENSE = 0.5;
    private static final double Z_SENSE = 0.5;

    public RMRAccelerometer(int port, int channel) {
        this.port = port;
        this.channel = channel;
        Accelerometer xAccel = new Accelerometer(port, channel);
        Accelerometer yAccel = new Accelerometer(port, channel);
        Accelerometer zAccel = new Accelerometer(port, channel);
        xAccel.setZero(X_ZERO);
        yAccel.setZero(Y_ZERO);
        zAccel.setZero(Z_ZERO);
        xAccel.setSensitivity(X_SENSE);
        yAccel.setSensitivity(Y_SENSE);
        zAccel.setSensitivity(Z_SENSE);
    }

    public double getXAccel() {
        return xAccel.getAcceleration();
    }

    public double getYAccel() {
        return yAccel.getAcceleration();
    }

    public double getZAccel() {
        return zAccel.getAcceleration();
    }
}
