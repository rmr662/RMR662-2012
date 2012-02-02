/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class DrivePIDTuningFunction extends Function {
    
    private Joystick[] joysticks;
    private boolean isPressed[] = new boolean[6];
    
    private double relativePTuning = 0.01;
    private double relativeITuning = 0.01;
    private double relativeDTuning = 0.01;
    
    public DrivePIDTuningFunction(Joystick[] joysticks) {
        this.joysticks = joysticks;
        for(int i = 0; i < isPressed.length; ++i) {
            isPressed[i] = false;
        }
    }
    
    protected void update() {
        // TODO: differentiate between right and left
        //Check for changes in joysick buttons and update PID tunings
        for(int i = 0; i < isPressed.length; ++i) {
            //if currently pressed, but wasn't last loop
            if(joysticks[Drive.LEFT].getRawButton(i + 1) && !isPressed[i]) {
                //Increment or decrement the value for the PID
                //Probably a bad way to go about this, but whatever
                switch(i + 1) {
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
            isPressed[i] = joysticks[Drive.LEFT].getRawButton(i + 1);
        }
//        System.out.println("PID: " + Drive.getInstance().getP() + ", " + Drive.getInstance().getI() + ", " + Drive.getInstance().getD());
    }
    
    protected void defaultState() {
        for (int i=0; i<isPressed.length; ++i){
            isPressed[i] = false;
        }
        
    }
}
