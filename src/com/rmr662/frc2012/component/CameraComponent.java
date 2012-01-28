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
    
    /**
     * creates instances of the servos for the camera
     */
    
    public CameraComponent(){
        servoPan = new Servo(PAN);
        servoTilt = new Servo(TILT);
    }
    
    /**
     * updates the target state of the pan and tilt
     */
    
    public void update() {
        servoPan.set(targetPanState);  
        servoTilt.set(targetTiltState);
    }
    
    public void reset() {
    }
    
    /**
     * gives the value Camera Component value for getRMRName
     * @return 
     */
    public String getRMRName() {
        return "Camera Component";
    }
    
    /**
     * Gets a value for Pan State. (its a getter for the pan state)
     * @return 
     */
    public double getPanState(){
        currentPanState = servoPan.get();
        return currentPanState;
    }
    
    /**
     * sets the target Pan State
     * @param goalPanState 
     */
    public void setPanTargetState(double goalPanState){
        targetPanState = goalPanState;
    }
    /**
     * gets the current state of tilt
     */
    public double getTiltState(){
        currentTiltState = servoTilt.get();        
        return currentTiltState;
    }
    /**
     * sets the target Tilt State
     * @param goalTiltState 
     */
    public void setTiltTargetState(double goalTiltState){
        targetTiltState = goalTiltState;
    }
}
