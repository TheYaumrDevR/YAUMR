package de.ethasia.yaumr.core.blocks;

import de.ethasia.yaumr.core.DoorPlacementStrategy;

/**
 * Represents a Block which can be opened or closed.
 * 
 * @author R
 */
public class DoorBlock extends Block {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private boolean isClosed;
    private boolean isUpperPartOfDoor;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    DoorBlock(BlockTypes blockType) {
        super(blockType);
        isClosed = true;
        initialize();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setIsClosed(boolean value) {
        isClosed = value;
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void setIsUpperPartOfDoor(boolean value) {
        isUpperPartOfDoor = value;
    }
    
    public boolean getIsUpperPartOfDoor() {
        return isUpperPartOfDoor;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void executePrimaryInteraction() {
        openOrClose();
    }
    
    public void openOrClose() {
        isClosed = !isClosed;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void setBlockTo(Block otherBlock) {
        super.setBlockTo(otherBlock);
        if (otherBlock instanceof DoorBlock) {
            DoorBlock otherDoorBlock = (DoorBlock)otherBlock;
            isUpperPartOfDoor = otherDoorBlock.isUpperPartOfDoor;
            isClosed = otherDoorBlock.isClosed;
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initialize() {
        isClosed = false;
        isUpperPartOfDoor = false;
        blockPlacementStrategy = new DoorPlacementStrategy();
        blockPlacementStrategy.setBlockToPlace(this);        
    }
    
    //</editor-fold>
}
