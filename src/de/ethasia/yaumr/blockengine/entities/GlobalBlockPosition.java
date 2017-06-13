package de.ethasia.yaumr.blockengine.entities;

/**
 * Represents the position of a block in an island. This consists of the
 * block's position in a chunk and the position of that chunk.
 * 
 * @author R
 */
public class GlobalBlockPosition {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int chunkPositionX;
    private int chunkPositionY;
    
    private int blockPositionX;
    private int blockPositionY;
    private int blockPositionZ;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public int getChunkPositionX() {
        return chunkPositionX;
    }
    
    public void setChunkPositionX(int value) {
        chunkPositionX = value;
    }
    
    public int getChunkPositionY() {
        return chunkPositionY;
    }    
    
    public void setChunkPositionY(int value) {
        chunkPositionY = value;
    }
    
    public int getBlockPositionX() {
        return blockPositionX;
    }
    
    public void setBlockPositionX(int value) {
        blockPositionX = value;
    }
    
    public int getBlockPositionY() {
        return blockPositionY;
    }
    
    public void setBlockPositionY(int value) {
        blockPositionY = value;
    }
    
    public int getBlockPositionZ() {
        return blockPositionZ;
    }
    
    public void setBlockPositionZ(int value) {
        blockPositionZ = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public GlobalBlockPosition decrementY() {
        if (blockPositionY != 0) {
            GlobalBlockPosition decrementedPosition = new GlobalBlockPosition();
        
            decrementedPosition.blockPositionX = blockPositionX;
            decrementedPosition.blockPositionY = blockPositionY - 1;
            decrementedPosition.blockPositionZ = blockPositionZ;
            decrementedPosition.chunkPositionX = chunkPositionX;
            decrementedPosition.chunkPositionY = chunkPositionY;
            
            return decrementedPosition;
        }
        
        throw new IllegalStateException("Cannot decrement position when position is 0.");
    }
    
    public GlobalBlockPosition incrementY() {
        if (blockPositionY != 255) {
            GlobalBlockPosition incrementedPosition = new GlobalBlockPosition();
        
            incrementedPosition.blockPositionX = blockPositionX;
            incrementedPosition.blockPositionY = blockPositionY + 1;
            incrementedPosition.blockPositionZ = blockPositionZ;
            incrementedPosition.chunkPositionX = chunkPositionX;
            incrementedPosition.chunkPositionY = chunkPositionY;
            
            return incrementedPosition;
        }
        
        throw new IllegalStateException("Cannot decrement position when position is 0.");
    }    
    
    @Override
    public boolean equals(Object otherGlobalBlockPosition) {
        if (!(otherGlobalBlockPosition instanceof GlobalBlockPosition)) {
            return false;
        }
        
        return equals((GlobalBlockPosition)otherGlobalBlockPosition);
    }
    
    @Override
    public int hashCode() {
        return this.chunkPositionX
                + this.chunkPositionY
                + this.blockPositionX
                + this.blockPositionY
                + this.blockPositionZ;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private boolean equals(GlobalBlockPosition otherPosition) {
        return otherPosition.chunkPositionX == this.chunkPositionX
                && otherPosition.chunkPositionY == this.chunkPositionY
                && otherPosition.blockPositionX == this.blockPositionX
                && otherPosition.blockPositionY == this.blockPositionY
                && otherPosition.blockPositionZ == this.blockPositionZ;
    }
    
    //</editor-fold>
}
