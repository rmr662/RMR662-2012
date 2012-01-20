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
    
    private Joystick[] joysticks = new Joystick[2];
    
    public TestController() {
        for (int i = 0; i < joysticks.length; i++) {
            joysticks[i] = new Joystick(JOYSTICKS[i]);
        }
    }
    
    public void run() {
        while (true) {
                Drive.getInstance().setTargetValues(joysticks);
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
