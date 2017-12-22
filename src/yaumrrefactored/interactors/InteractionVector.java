package yaumrrefactored.interactors;

/**
 * Data structure to pass on position / direction data.
 * 
 * @author 
 */
public class InteractionVector {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private float x;
    private float y;
    private float z;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public InteractionVector(float x, float y, float z) {
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
}
