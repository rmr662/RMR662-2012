/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author RMR Programming
 */
public class CameraComponent extends Component{
    
    private static final int PAN = 10;
    private static final int TILT = 9;

    Servo servoPan;
    double currentPanState;
    double targetPanState;
    Servo servoTilt;
    double currentTiltState;
    double targetTiltState;
    
    public CameraComponent(){
        servoPan = new Servo(PAN);
        servoTilt = new Servo(TILT);
    }
    
    public void update() {
        servoPan.set(targetPanState);  
        servoTilt.set(targetTiltState);
    }

    public void reset() {
    }

    public String getRMRName() {
        return "Camera Compenent";
    }
    
    public double getPanState(){
        currentPanState = servoPan.get();
        return currentPanState;
    }
    
    public void setPanTargetState(double goalPanState){
        targetPanState = goalPanState;
    }
    
    public double getTiltState(){
        currentTiltState = servoTilt.get();        
        return currentTiltState;
    }
    
    public void setTiltTargetState(double goalTiltState){
        targetTiltState = goalTiltState;
    }
}
