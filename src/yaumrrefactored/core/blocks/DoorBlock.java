package yaumrrefactored.core.blocks;

import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.blocks.Block;

/**
 * Represents a Block which can be opened or closed.
 * 
 * @author R
 */
public class DoorBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private boolean isClosed;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public DoorBlock(BlockTypes blockType) {
        super(blockType);
        isClosed = false;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setIsClosed(boolean value) {
        isClosed = value;
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void openOrClose() {
        isClosed = !isClosed;
    }
    
    //</editor-fold>
}
