/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.rmr662.frc2012;

import com.rmr662.frc2012.component.*;
import com.rmr662.frc2012.controller.AutonomousController;
import com.rmr662.frc2012.controller.TeleopController;
import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.generic.Controller;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RMRRobot extends SimpleRobot {

    /**
     * The update cycle for pushing updates to components (in millis) The
     * delay() method expects seconds
     */
    public static final double PERIOD = 0.05;
    private Component[] components;
    private NetworkComms networkComms; //Seperate due to special behavior
    private Controller activeController;
    private Thread controllerThread;
    public static RMRRobot robot;

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        // TODO: initialize activeController and start a thread for it.
        // updateComponents();
        activeController = AutonomousController.getInstance();
        if (controllerThread != null) {
            controllerThread.interrupt();
        }
        controllerThread = new Thread(activeController);
        controllerThread.start();
        while(isEnabled() && isAutonomous()) {
            DriverStationLCD.getInstance().println(Line.kUser2, 0, "Autonomous Loop");
            DriverStationLCD.getInstance().updateLCD();
            updateComponents();
            Timer.delay(PERIOD);
        }
        controllerThread.interrupt();
        try {
            controllerThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
//        System.out.println("PID status: " + Drive.getInstance().isPIDEnabled());
//        try {
//              System.out.println("Start Sleep");
//              Thread.sleep(3000);
//              System.out.println("Stop Sleep");
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
        activeController = TeleopController.getInstance();
        if (controllerThread != null) {
            controllerThread.interrupt();
        }
        controllerThread = new Thread(activeController);
        controllerThread.start();

//        Drive.getInstance().enablePID(); // temp

        while (isEnabled() && isOperatorControl()) {
            updateComponents();
            networkComms.update();
            Timer.delay(PERIOD);
        }
    }

    /**
     * This function is called exactly once when the robot is powered on.
     */
    protected void robotInit() {
        robot = this;
        components = new Component[8];
        components[0] = Drive.getInstance();
        components[1] = RMRCompressor.getInstance();
        components[2] = Transmission.getInstance();
        components[3] = BallBucket.getInstance();
        components[4] = ShooterArm.getInstance();
        components[5] = Camera.getInstance();
        components[6] = Turret.getInstance();
        components[7] = BallLoader.getInstance();
        networkComms = NetworkComms.getInstance(components);
        // PCR : Initialize variables
        controllerThread = null;
        
    }

    protected void disabled() {
        try {
            controllerThread.join();
        } catch (NullPointerException e) {
            System.out.println("No controller thread was running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < components.length; i++) {
            if (components[i] != null) {
                components[i].reset();
            }
        }

    }

    /**
     * This method is called to update all of the physical components
     */
    private void updateComponents() {
        for (int i = 0; i < components.length; i++) {
            if (components[i] != null) {
                components[i].update();
            }
        }
    }
}
