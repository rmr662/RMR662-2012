/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.generic;

/**
 *
 * @author RMR Programming
 */
public abstract class Function implements Runnable {
    
    private boolean enabled;
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean getEnabled() {
        return enabled;
    }
    
    public void run() {
        if (enabled) {
            update();
        } else {
            defaultState();
        }
    }
    
    protected abstract void defaultState();
    protected abstract void update();
    
}
