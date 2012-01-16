/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.controller;

import com.rmr662.frc2012.generic.Controller;

/**
 * A controller that can be used for testing components during development.
 * @author RMR2012
 */
public class TestController implements Controller {
    
    private static TestController singletonInstance;
    
    public void run() {
        
    }
    
    public String getRMRName() {
        return "Test Controller";
    }
    
    public static TestController getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new TestController();
        }
        return singletonInstance;
    }
    
}
