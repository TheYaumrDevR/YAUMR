package de.ethasia.yaumr.core.blocks;

/**
 * A simplified Quaternion implementation which only includes necessary methods.
 * An own Quaternion class is provided here instead of using the one from the engine
 * or any other external library. This is done to prevent the core logic from
 * having outside dependencies.
 * 
 * @author R
 */
public final class Quaternion {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private float x, y, z, w;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Quaternion() {
        x = 0;
        y = 0;
        z = 0;
        w = 1;
    }
    
    /**
     * Creates a deep copy of the given Quaternion.
     * 
     * @param other 
     */
    public Quaternion(Quaternion other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }
    
    /**
     * Sets this Quaternion's components so that it represent the rotation by the
     * given amount around the axis represented by the given vector.
     * 
     * @param angleRadians The angle of the rotation this Quaternion should represent.
     * @param axis The axis of the rotation this Quaternion should represent.
     * @return The modified this instance.
     */
    public Quaternion fromAngleAxis(float angleRadians, Vector3 axis) {
        Vector3 normalizedAxis = axis.normalize();
        return fromAngleNormalizedAxis(angleRadians, normalizedAxis);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public Quaternion multiply(Quaternion factor) {
        Quaternion result = new Quaternion();
        float factorW = factor.w, factorX = factor.x, factorY = factor.y, factorZ = factor.z;
        result.x = x * factorW + y * factorZ - z * factorY + w * factorX;
        result.y = -x * factorZ + y * factorW + z * factorX + w * factorY;
        result.z = x * factorY - y * factorX + z * factorW + w * factorZ;
        result.w = -x * factorX - y * factorY - z * factorZ + w * factorW;
        
        return result;
    }  
    
    public Vector3 getVectorFromImaginaryPart() {
        return new Vector3(x, y, z);
    }
    
    public float getRealPart() {
        return w;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Quaternion fromAngleNormalizedAxis(float angleRadians, Vector3 normalizedAxis) {
        if (0 == angleRadians) {
            return loadIdentity();
        }
        
        if (normalizedAxis.getX() == 0 && normalizedAxis.getY() == 0 && normalizedAxis.getZ() == 0) {
            return loadIdentity();
        }
        
        float halfAngle = 0.5f * angleRadians;
        float sin = (float)Math.sin(halfAngle);
        w = (float)Math.cos(halfAngle);
        x = sin * normalizedAxis.getX();
        y = sin * normalizedAxis.getY();
        z = sin * normalizedAxis.getZ();

        return this;            
    }    
    
    private Quaternion loadIdentity() {
        x = y = z = 0;
        w = 1;
        
        return this;
    }     
    
    //</editor-fold>
}
