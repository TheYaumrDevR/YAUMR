package yaumrrefactored.core;

import yaumrrefactored.core.blocks.DoorBlock;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.Block;

/**
 * Handles the placement and removal of door blocks which are 2 in height.
 * 
 * @author R
 */
public class DoorPlacementStrategy {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Block blockToPlace;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    /**
     * 
     * @param value The door block to place. 
     */
    public void setBlockToPlace(Block value) {
        blockToPlace = (DoorBlock)value;    
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean placeBlockOnIslandAt(Island island, BlockPosition blockPosition) {
        if (null != blockToPlace) {
            BlockPosition oneAbovePlacement = blockPosition.getPositionOneAbove();
            if (island.checkBlockCanBeSetToPosition(blockToPlace, oneAbovePlacement)) {
                if (island.placeBlockAt(blockToPlace, blockPosition)) {
                    
                }
            }
        }
        
        return false;
    }
    
    //</editor-fold>
}
