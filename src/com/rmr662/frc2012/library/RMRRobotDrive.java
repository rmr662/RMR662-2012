/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.library;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author mcoffin
 */
public class RMRRobotDrive {
    
    private PIDController leftPID;
    private PIDController rightPID;
    
    public RMRRobotDrive(PIDController leftPID, PIDController rightPID) {
        this.leftPID = leftPID;
        this.rightPID = rightPID;
    }
    
    public void tankDrive(double leftValue, double rightValue) {
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);
        if (rightValue >= 0d) {
            rightValue *= rightValue;
        } else {
            rightValue = -(rightValue * rightValue);
        }
        if (leftValue >= 0d) {
            leftValue *= leftValue;
        } else {
            leftValue = -(leftValue * leftValue);
        }
        setPIDSetpoints(leftValue, rightValue);
    }
    
    private void setPIDSetpoints(double leftValue, double rightValue) {
        leftPID.setSetpoint(leftValue);
        rightPID.setSetpoint(rightValue);
    }
    
    public static double limit(double num) {
        if (num < -1d) {
            return -1d;
        }
        if (num > 1d) {
            return 1d;
        }
        return num;
    }
    
}
