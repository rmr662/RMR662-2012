/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.rmr662.frc2012;


import com.rmr662.frc2012.controller.TestController;
import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.generic.Controller;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RMRRobot extends SimpleRobot {
    
    private Component[] components;
    private Controller activeController;
    private Thread controllerThread;
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        // TODO: initialize activeController and start a thread for it.
        updateComponents();
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        activeController = TestController.getInstance();
        controllerThread = new Thread(activeController);
        controllerThread.start();
        updateComponents();
    }
    
    /**
     * This function is called exactly once when the robot is powered on.
     */
    protected void robotInit() {
        // TODO: Initialize all components by adding them to components array
    }
    
    /**
     * This method is called to update all of the physical components
     */
    private void updateComponents() {
        for (int i = 0; i < components.length; i++) {
            components[i].update();
        }
    }
    
}
