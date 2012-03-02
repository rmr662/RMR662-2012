/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller;

import com.rmr662.frc2012.RMRRobot;
import com.rmr662.frc2012.component.BallBucket;
import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.component.NetworkComms;
import com.rmr662.frc2012.controller.function.*;
import com.rmr662.frc2012.generic.Controller;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class TeleopController implements Controller {

    private static final int[] JOYSTICKS = {1, 2, 3};
    private Joystick[] joysticks = new Joystick[3];
    private Function[] functions;
    private static TeleopController instance = null;

    public TeleopController() {
        for (int i = 0; i < joysticks.length; i++) {
            joysticks[i] = new Joystick(JOYSTICKS[i]);
        }
        functions = new Function[7];
        functions[0] = new DriveFunction(joysticks);
        //functions[1] = new DrivePIDTuningFunction(joysticks);
        functions[1] = new TransmissionFunction(joysticks[Drive.LEFT]);
        functions[2] = new CameraFunction(joysticks[2]); //third joystick
        functions[3] = new TurretFunction(joysticks[2]);
        functions[4] = new BallShooterFunction(joysticks[2]);
        functions[5] = new BallLoadFunction(joysticks[2]);
        functions[6] = new PickupFunction(joysticks[2]);
    }

    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while (RMRRobot.robot.isOperatorControl() && !RMRRobot.robot.isDisabled()) {
            updateFunctions();
        }
    }

    public void updateFunctions() {
        for (int i = 0; i < functions.length; i++) {
            if (functions[i] != null) {
//                if (functions[i].isEndGameFunction() || !NetworkComms.getInstance().isEndGame()) {
//                    functions[i].setEnabled(true);
//                }
//                else{
//                    functions[i].setEnabled(false);
//                }
                functions[i].run();
            }
        }
    }

    public static TeleopController getInstance() {
        if (instance == null) {
            instance = new TeleopController();
        }
        return instance;
    }

    public String getRMRName() {
        return "Teleop Controller";
    }
}