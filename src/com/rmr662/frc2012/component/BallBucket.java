/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.component;

import com.rmr662.frc2012.generic.Component;

/**
 *
 * @author RMR Programming
 */
public class BallBucket extends Component {
    
    private static BallBucket instance;
    
    public BallBucket() {
        
    }
    
    public void update() {
        
    }
    
    public void reset() {
        
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
    
}
