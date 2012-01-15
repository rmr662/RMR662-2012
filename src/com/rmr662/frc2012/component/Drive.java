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
    
    private static Drive instance;
    
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    
    private static final int[] CHANNELS = {1, 2};
    private static final int[] JOYSTICKS = {1, 2};
    
    private RMRJaguar[] motors = new RMRJaguar[CHANNELS.length];
    private RobotDrive robotDrive;
    private Joystick[] joysticks = new Joystick[CHANNELS.length];
    
    public Drive() {
        for (int i = 0; i < CHANNELS.length; i++) {
            motors[i] = new RMRJaguar(CHANNELS[i]);
            joysticks[i] = new Joystick(JOYSTICKS[i]);
        }
        motors[RIGHT].setInverted(true);
        robotDrive = new RobotDrive(motors[LEFT], motors[RIGHT]);
    }
    
    public void update() {
        robotDrive.tankDrive(joysticks[LEFT], joysticks[RIGHT]);
    }
    
    public void disable() {
        
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
