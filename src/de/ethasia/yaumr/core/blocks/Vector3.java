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
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final Vector3 UNIT_X = new Vector3(1, 0, 0);
    public static final Vector3 UNIT_Y = new Vector3(0, 1, 0);
    public static final Vector3 UNIT_Z = new Vector3(0, 0, 1);
    
    //</editor-fold>
    
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
    
    public Vector3 set(Vector3 other) {
        x = other.x;
        y = other.y;
        z = other.z;
        
        return this;
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
    
    /**
     * Scales this vector by the given scalar. This vector is changed in the process.
     * 
     * @param scalar
     * @return A reference to this.
     */
    public Vector3 scale(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        
        return this;
    }
    
    /**
     * The immutable variant of the scale method. Instead of scaling this object,
     * this method creates a new Vector which is equal to this Vector scaled by
     * the given scalar.
     * 
     * @param scalar
     * @return 
     */
    public Vector3 scaleImmutable(float scalar) {
        float newX = x * scalar;
        float newY = y * scalar;
        float newZ = z * scalar;
        
        return new Vector3(newX, newY, newZ);
    }
    
    public Vector3 add(final Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        
        return this;
    }
    
    public Vector3 addImmutable(final Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }
    
    public Vector3 subtract(final Vector3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        
        return this;
    }
    
    public Vector3 subtractImmutable(final Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }
    
    public float dot(final Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }
    
    public Vector3 cross(final Vector3 other) {
        float newX = y * other.z - z * other.y;
        float newY = z * other.x - x * other.z;
        float newZ = x * other.y - y * other.x;
        
        x = newX;
        y = newY;
        z = newZ;
        
        return this;
    }
    
    //</editor-fold>
}
