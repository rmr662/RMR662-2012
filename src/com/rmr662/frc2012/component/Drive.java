/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author mcoffin
 */
public class Drive extends Component {
    
    private volatile static Drive instance;
    
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    
    private static final int[] CHANNELS = {1, 2};
    
    private RMRJaguar[] motors = new RMRJaguar[CHANNELS.length];
    private RobotDrive robotDrive;
    private double[] targetValues = {0d, 0d};
    
    private Drive() {
        for (int i = 0; i < CHANNELS.length; i++) {
            motors[i] = new RMRJaguar(CHANNELS[i]);
        }
        motors[RIGHT].setInverted(true);
        robotDrive = new RobotDrive(motors[LEFT], motors[RIGHT]);
    }
    
    public void update() {
        robotDrive.tankDrive(targetValues[LEFT], targetValues[RIGHT]);
    }
    
    public void disable() {
        for (int i = 0; i < targetValues.length; i++) {
            targetValues[i] = 0d;
        }
    }
    
    public synchronized void setTargetValue(int index, double value) {
        targetValues[index] = value;
    }
    
    public synchronized void setTargetValues(Joystick[] joysticks) {
        for (int i = 0; i < joysticks.length; i++) {
            targetValues[i] = joysticks[i].getY();
        }
    }
    
    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }
        return instance;
    }
    
    public String getRMRName() {
        return "Drive";
    }
    
}