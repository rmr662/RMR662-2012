/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRSolenoidSystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author RMR Programming
 */
public class Transmission extends Component {

    private boolean transmissionLow = true;
    private RMRSolenoidSystem solenoidSystem;
    private static Transmission instance = null;

   /**
    * creates the instance for TransmissionComponent if none exists
    * prevents more than one instance from being created
    * @return 
    */
    
    public static Transmission getInstance() {
        if (instance == null) {
            instance = new Transmission();
        }
        return instance;
    }

    /**
     * constructor
     */
    
    private Transmission() {
        solenoidSystem = new RMRSolenoidSystem(new Solenoid(5), new Solenoid(6));
    }

    /**
     * sets transmission as low
     */
    
    public void setTransmissionHigh() {
        transmissionLow = false;
        NetworkTable.getTable("status").putBoolean("transmissionLow", transmissionLow);
    }
    
    /**
     * sets transmission as high
     */

    public void setTransmissionLow() {
        transmissionLow = true;
        NetworkTable.getTable("status").putBoolean("transmissionLow", transmissionLow);
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
        return "Transmission";
    }
}
