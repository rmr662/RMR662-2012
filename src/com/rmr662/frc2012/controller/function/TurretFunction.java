/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller.function;

import com.rmr662.frc2012.component.Turret;
import com.rmr662.frc2012.generic.Function;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author ROBOTics
 */
public class TurretFunction extends Function{
    
    private Joystick joystick;
    
    
    public TurretFunction(Joystick joystick){
        this.joystick = joystick;
    }

    protected void defaultState() {
    }

    protected void update() {
        Turret.getInstance().setStopAtCenter(joystick.getRawButton(4) || joystick.getRawButton(5));
        Turret.getInstance().setTarget(joystick.getX()/2);        
    }
    
}
