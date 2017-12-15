package yaumrrefactored.core.blocks;

import yaumrrefactored.core.Island;

/**
 * Represents the BlockPosition on an Island. This is just a data structure.
 * 
 * @author R
 */
public class BlockPosition {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    public final int x; 
    public final int y;
    public final int z;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public BlockPosition(int x, int upDown, int z) {
        if (x < 0 || upDown < 0 ||z < 0) {
            throw new IllegalArgumentException("BlockPosition components must not be negative.");
        }
        
        if (upDown >= Island.HEIGHT_IN_BLOCKS) {
            throw new IllegalArgumentException("BlockPosition cannot be higher than Island height.");
        }
        
        this.x = x;
        y = upDown;
        this.z = z;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mehods">
    
    public BlockPosition getPositionOneBelow() {
        if (0 == y) {
            return null;
        }
        
        return new BlockPosition(x, y - 1, z);
    }
    
    public BlockPosition getPositionOneAbove() {
        if (Island.HEIGHT_IN_BLOCKS - 1 == y) {
            return null;
        }
        
        return new BlockPosition(x, y + 1, z);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof BlockPosition) {
            BlockPosition other = (BlockPosition)o;
            return x == other.x
                    && y == other.y
                    && z == other.z;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return x + y + z;
    }
    
    //</editor-fold>
}
