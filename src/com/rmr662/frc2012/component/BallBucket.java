/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;
import com.rmr662.frc2012.physical.RMRSolenoidSystem;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author RMR Programming
 */
public class BallBucket extends Component {
    
    private static BallBucket instance;
    
    private static final int NORMAL_CHANNEL = 0;
    private static final int INVERTED_CHANNEL = 1;
    private static final boolean DEFAULT_STATE = false;
    
    private RMRSolenoidSystem valve;
    
    private boolean targetValueIsDown;
    
    public BallBucket() {
        valve = new RMRSolenoidSystem(new Solenoid(NORMAL_CHANNEL), new Solenoid(INVERTED_CHANNEL));
        valve.set(DEFAULT_STATE);
    }
    
    public void update() {
        boolean localTargetValueIsDown;
        
        synchronized(this) {
            localTargetValueIsDown = targetValueIsDown;
        }
        valve.set(localTargetValueIsDown);
    }
    
    public void reset() {
        valve.set(DEFAULT_STATE);
        synchronized(this) {
            targetValueIsDown = DEFAULT_STATE;
        }
    }
    
    public String getRMRName() {
        return "Ball Bucket";
    }
    
    public static BallBucket getInstance() {
        if (instance == null) {
            instance = new BallBucket();
        }
        return instance;
    }
    
    public synchronized void setTarget(boolean down){
        targetValueIsDown = down;
    }
    
}
