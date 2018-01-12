package de.ethasia.yaumr.core.blocks;

/**
 * A simplified vector class representing a vector with 3 floating point elements.
 * Only necessary operations are provided. This math class is defined here for usage
 * in the inner architectural layers so that no outside library dependencies are
 * created in the core classes.
 * 
 * This is a mutable class for performance reasons.
 * 
 * @author R
 */
public class Vector3 {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private float x, y, z;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Initializers">
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getZ() {
        return z;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Normalizes this vector if necessary and returns a reference to itself.
     * This vector is changed in the process. It does not create a new one.
     * 
     * @return 
     */
    public Vector3 normalize() {
        float squaredLength = lengthSquared();
        if (0.f == squaredLength || 1.f == squaredLength) {
            return this;
        }
        
        return scale(1.f / (float)Math.sqrt(squaredLength));
    }
    
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }
    
    public Vector3 scale(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        
        return this;
    }
    
    //</editor-fold>
}
