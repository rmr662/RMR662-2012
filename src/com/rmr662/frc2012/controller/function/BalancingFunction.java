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
    
    public BalancingFunction (Joystick joystick){
        this.joystick = joystick;
        acceler = RMRAccelerometer.getInstance();
        //setEnabled(false); Need to disable and wait for endgame
    }
    
    protected void defaultState() {
        Drive.getInstance().reset();
    }

    protected void update() {
        System.out.println(acceler.getAdjYAccel());

        if(joystick.getRawButton(1)){
          
          if(acceler.getAdjYAccel() < -0.06){
              System.out.println("Forward");
              Drive.getInstance().setTargetValues(0.38, 0.38);
          }
          else if(acceler.getAdjYAccel() > 0.06){
              System.out.println("Backward");
              Drive.getInstance().setTargetValues(-0.38, -0.38);
          }
          else{
              Drive.getInstance().reset();
          }
        }
        else{
            Drive.getInstance().reset();
        }
    }
    
}
