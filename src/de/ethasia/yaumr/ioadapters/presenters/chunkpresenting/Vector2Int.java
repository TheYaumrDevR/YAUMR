package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

/**
 * Represents a vector with 2 integers as a data class.
 * 
 * @author R
 */
public class Vector2Int {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final int x;
    private final int y;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector2Int)) {
            return false;
        }
        
        Vector2Int otherVector = (Vector2Int)o;
        
        return x == otherVector.x && y == otherVector.y;
    } 
    
    @Override 
    public int hashCode() {
        return x + y;
    }
    
    //</editor-fold>
}
