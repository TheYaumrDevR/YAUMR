package yaumrrefactored.core;

/**
 * Lists all block faces. All blocks have six faces, regardless of block shape.
 * 
 * @author R
 */
public enum BlockFaceTypes {
    LEFT,       // Negative x
    RIGHT,      // Positive x
    FRONT,      // Positive z
    BACK,       // Negative z
    TOP,        // Positive y  
    BOTTOM;     // Negative y
}
