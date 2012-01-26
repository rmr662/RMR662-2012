/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller;

import com.rmr662.frc2012.RMRRobot;
import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.generic.Controller;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * A controller that can be used for testing components during development.
 * @author RMR2012
 */
public class TestController implements Controller {
    
    private static TestController instance;
    
    private static final int[] JOYSTICKS = {1, 2};
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    
    private boolean[] isPressed = new boolean[6];
    private Joystick[] joysticks = new Joystick[2];
    
    private double relativePTuning = 0.01;
    private double relativeITuning = 0.01;
    private double relativeDTuning = 0.01;
    
    public TestController() {
        for (int i = 0; i < joysticks.length; i++) {
            joysticks[i] = new Joystick(JOYSTICKS[i]);
        }
        for(int i = 0; i < isPressed.length; ++i) {
            isPressed[i] = false;
        }
    }
    
    public void run() {
        while (true) {
            Drive.getInstance().setTargetValues(joysticks);
            
            //Check for changes in joysick buttons and update PID tunings
            for(int i = 0; i < isPressed.length; ++i) {
                //if currently pressed, but wasn't last loop
                if(joysticks[LEFT].getRawButton(i + 1) && !isPressed[i]) {
                    //Increment or decrement the value for the PID
                    //Probably a bad way to go about this, but whatever
                    switch(i + 1) {
                        // TODO: turn numbers into constants numbnutss
                        case 1:
                        {
                            Drive.getInstance().setRelativePIDValues(relativePTuning,0,0);
                            break;
                        }
                        case 2:
                        {
                            Drive.getInstance().setRelativePIDValues(-relativePTuning,0,0);
                            break;
                        }
                        case 3:
                        {
                            Drive.getInstance().setRelativePIDValues(0,-relativeITuning,0);
                            break;
                        }
                        case 4:
                        {
                            Drive.getInstance().setRelativePIDValues(0,0,-relativeDTuning);
                            break;
                        }
                        case 5:
                        {
                            Drive.getInstance().setRelativePIDValues(0,relativeITuning,0);
                            break;
                        }
                        case 6:
                        {
                            Drive.getInstance().setRelativePIDValues(0,0,relativeDTuning);
                            break;
                        }
                    }
                }
                isPressed[i] = joysticks[LEFT].getRawButton(i + 1);
            }
            Timer.delay(RMRRobot.PERIOD);
        }
    }
    
    public String getRMRName() {
        return "Test Controller";
    }
    
    public static TestController getInstance() {
        if (instance == null) {
            instance = new TestController();
        }
        return instance;
    }
    
}
