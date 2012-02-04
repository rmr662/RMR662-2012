/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRSolenoidSystem;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author RMR Programming
 */
public class TransmissionComponent extends Component {

    private boolean transmissionLow = true;
    private RMRSolenoidSystem solenoidSystem;
    private static TransmissionComponent instance = null;

    public static TransmissionComponent getInstance() {
        if (instance == null) {
            instance = new TransmissionComponent();
        }
        return instance;
    }

    public TransmissionComponent() {
        solenoidSystem = new RMRSolenoidSystem(new Solenoid(5), new Solenoid(6));
    }

    public void setTransmissionHigh() {
        transmissionLow = false;
    }

    public void setTransmissionLow() {
        transmissionLow = true;
    }

    public void toggleTransmission() {
        transmissionLow = !transmissionLow;
    }

    public void update() {
        if (Drive.getInstance().isMoving()) {
            solenoidSystem.set(transmissionLow);
        }
    }

    public void reset() {
        transmissionLow = true;
    }

    public String getRMRName() {
        return "Transmission Component";
    }
}
