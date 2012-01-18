/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmr662.frc2012.generic;

/**
 * This class represents a physical subsystem of the robot.
 * @author RMR662
 */
public abstract class Component {
    
    /**
     * Represents the enabled or disabled state of the Component
     */
    private boolean enabled;
    
    /**
     * Sets the enabled or disabled state of the component
     * @param enabled whether the component should be enabled or not
     */
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            reset();
        }
        this.enabled = enabled;
    }
    
    /**
     * Retrieves the enabled or disabled state of the component
     * @return whether the component is currently enabled or not
     */
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Updates the physical state of the component based on its current state
     * and its target state.
     */
    public abstract void update();
    
    /**
     * Immediately disables all parts of the component that may be moving when
     * the component is disabled.
     */
    public abstract void reset();
    
    public abstract String getRMRName();
    
}
