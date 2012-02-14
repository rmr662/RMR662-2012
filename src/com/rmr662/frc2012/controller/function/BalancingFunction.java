/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Drive;
import com.rmr662.frc2012.generic.Function;
import com.rmr662.frc2012.physical.RMRAccelerometer;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author RMR Programming
 */
public class BalancingFunction extends Function{

    Joystick joystick;
    RMRAccelerometer acceler;
    
    /**
     * Constructor 
     * @param joystick 
     */
    
    public BalancingFunction (Joystick joystick){
        this.joystick = joystick;
        acceler = RMRAccelerometer.getInstance();
        setEndGameFunction(true);
        //setEnabled(false); Need to disable and wait for endgame
    }
    
    /**
     * resets the state drive 
     */
    
    protected void defaultState() {
        Drive.getInstance().reset();
    }
    
    /**
     * gets and prints Adj Y accelerometer value
     */

    protected void update() {
        System.out.println(acceler.getAdjYAccel());
        
        /**
         * If the button pushed when the robot is past the error margin then it
         * moves toward the corrected position
         */

        if(joystick.getRawButton(1)){
          
          if(acceler.getAdjYAccel() < -0.06){
              System.out.println("Forward");
              Drive.getInstance().setTargetValues(0.38, 0.38);
          }

          else if(acceler.getAdjYAccel() > 0.06){
              System.out.println("Backward");
              Drive.getInstance().setTargetValues(-0.38, -0.38);
          }
          
          /**
           * resets the Drive value
           */
          else{
              Drive.getInstance().reset();
          }
        }
        /**
         * resets the Drive value
         */
        else{
            Drive.getInstance().reset();
        }
    }
    
}
