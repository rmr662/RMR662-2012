/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.library;

import edu.wpi.first.wpilibj.PIDController;
import com.rmr662.frc2012.physical.RMRJaguar;

/**
 *
 * @author mcoffin
 */
public class RMRRobotDrive {
    
    private PIDController leftPID;
    private PIDController rightPID;
    
    private RMRJaguar leftMotor;
    private RMRJaguar rightMotor;
    
    private boolean PIDEnabled = true;
    
    public RMRRobotDrive(PIDController leftPID, PIDController rightPID, RMRJaguar leftMotor, RMRJaguar rightMotor) {
        this.leftPID = leftPID;
        this.rightPID = rightPID;
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
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
        if(PIDEnabled) {
            setPIDSetpoints(leftValue, rightValue);
        }
        else {
            leftMotor.set(leftValue);
            rightMotor.set(rightValue);
        }

    }
    
    private void setPIDSetpoints(double leftValue, double rightValue) {
        leftPID.setSetpoint(leftValue);
        rightPID.setSetpoint(rightValue);
    }
    
    public void setPID(boolean enabled) {
        PIDEnabled = enabled;
        if(enabled) {
            leftPID.enable();
            rightPID.enable();
        }
        else {
            leftPID.disable();
            rightPID.disable();
        }
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
