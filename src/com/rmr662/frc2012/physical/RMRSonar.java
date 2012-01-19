/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.physical;

import edu.wpi.first.wpilibj.AnalogModule;

/**
 *
 * @author RMR Programming
 */
public class RMRSonar {

    private int slot;
    private int channel;
    private static final int TESTS = 10;
    private static final double VOLTAGE_CONVERSION_FACTOR = 9.8e-3;
    AnalogModule module;

    public RMRSonar(int slot, int channel) {
        this.slot = slot;
        this.channel = channel;
        AnalogModule module = AnalogModule.getInstance(channel);
    }

    public double getDistance() {

        double sum = 0.0;

        for (int counter = 0; counter < TESTS; counter++) {
            sum += (module.getVoltage(channel) / VOLTAGE_CONVERSION_FACTOR);
        }
        return sum / TESTS;
    }
}
