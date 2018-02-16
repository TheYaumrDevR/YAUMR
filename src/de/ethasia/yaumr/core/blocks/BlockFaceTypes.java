package de.ethasia.yaumr.core.blocks;

/**
 * Lists all block faces. All blocks have six faces, regardless of block shape.
 * 
 * @author R
 */
public enum BlockFaceTypes {
    LEFT(0),       // Negative x
    RIGHT(1),      // Positive x
    FRONT(2),      // Positive z
    BACK(3),       // Negative z
    TOP(4),        // Positive y  
    BOTTOM(5);     // Negative y
    
    private final int value;
    private BlockFaceTypes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
