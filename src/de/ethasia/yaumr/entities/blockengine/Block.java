package de.ethasia.yaumr.entities.blockengine;

/**
 * Represents a block in the game world. Players can place them, remove them
 * and interact with them.
 * 
 * @author R
 */
public class Block {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockTypes blockType;
    private boolean isVisible;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public BlockTypes getBlockType() {
        return blockType;
    }
    
    public void setBlockType(BlockTypes blockType) {
        this.blockType = blockType;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    
    //</editor-fold>
}
