/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller;

import com.rmr662.frc2012.component.BallBucket;
import com.rmr662.frc2012.component.BallLoader;
import com.rmr662.frc2012.component.ShooterArm;
import com.rmr662.frc2012.generic.Controller;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author ROBOTics
 */
public class AutonomousController implements Controller {

    private static final int ITERATIONS = 2;
    private DigitalInput enableSwitch;
    private static AutonomousController instance;

    public AutonomousController() {
        enableSwitch = new DigitalInput(13);
    }

    public void run() {
        if (enableSwitch.get()) {
            try {
                for (int q = 0; q < ITERATIONS; q++) {
                    shoot();
                    if (q < (ITERATIONS - 1)) {
                        reload();
                    }
                }
            } catch(InterruptedException e) {
                ShooterArm.getInstance().setShooting(false);
                BallBucket.getInstance().setElbowTarget(false);
            }
        }
    }

    private void shoot() throws InterruptedException {
        ShooterArm.getInstance().setShooting(true);
        Thread.sleep(450);
        ShooterArm.getInstance().setShooting(false);
    }

    private void reload() throws InterruptedException {
        BallLoader.getInstance().setTargetState(!false);
        Thread.sleep(5000);
        BallLoader.getInstance().setTargetState(false);
        Thread.sleep(1500);
    }

    public String getRMRName() {
        return "Autonomous Controller";
    }

    public static AutonomousController getInstance() {
        if (instance == null) {
            instance = new AutonomousController();
        }
        return instance;
    }
}