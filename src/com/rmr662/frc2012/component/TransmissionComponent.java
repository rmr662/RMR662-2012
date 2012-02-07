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

   /**
    * creates the instance for TransmissionComponent if none exists
    * prevents more than one instance from being created
    * @return 
    */
    
    public static TransmissionComponent getInstance() {
        if (instance == null) {
            instance = new TransmissionComponent();
        }
        return instance;
    }

    /**
     * constructor
     */
    
    private TransmissionComponent() {
        solenoidSystem = new RMRSolenoidSystem(new Solenoid(5), new Solenoid(6));
    }

    /**
     * sets transmission as low
     */
    
    public void setTransmissionHigh() {
        transmissionLow = false;
    }
    
    /**
     * sets transmission as high
     */

    public void setTransmissionLow() {
        transmissionLow = true;
    }
    
    /**
     * switches transmission
     */

    public void toggleTransmission() {
        transmissionLow = !transmissionLow;
    }
    
    /**
     * sets the transmission if the robot is moving
     */

    public void update() {
        if (Drive.getInstance().isMoving()) {
            solenoidSystem.set(transmissionLow);
        }
    }

    /**
     * resets target values for transmissionLow
     */
    
    public void reset() {
        transmissionLow = true;
    }

    public String getRMRName() {
        return "Transmission Component";
    }
}
