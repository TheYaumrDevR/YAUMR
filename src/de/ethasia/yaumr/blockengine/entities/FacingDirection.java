package de.ethasia.yaumr.blockengine.entities;

/**
 * Contains the six directions where neighboring blocks can be found.
 *  
 * @author R
 */
public enum FacingDirection {
    
    BACK(1),
    RIGHT(2),
    FRONT(4),
    LEFT(8),
    TOP(16),
    BOTTOM(32);
    
    private final int value;
    
    FacingDirection(final int newValue) {
        value = newValue;
    }
    
    public int getValue() {
        return value;
    }
}
