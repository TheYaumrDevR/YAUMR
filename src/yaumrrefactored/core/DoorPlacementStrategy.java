package yaumrrefactored.core;

import yaumrrefactored.core.blocks.DoorBlock;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPlacementStrategy;
import yaumrrefactored.core.blocks.SimpleBlockFactory;

/**
 * Handles the placement and removal of door blocks which are 2 in height.
 * 
 * @author R
 */
public class DoorPlacementStrategy implements BlockPlacementStrategy {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Block blockToPlace;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    /**
     * 
     * @param value The door block to place. 
     */
    @Override
    public void setBlockToPlace(Block value) {
        blockToPlace = (DoorBlock)value;    
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public boolean placeBlockOnIslandAt(Island island, BlockPosition blockPosition) {
        if (null != blockToPlace && (blockToPlace instanceof DoorBlock)) {
            BlockPosition oneAbovePlacement = blockPosition.getPositionOneAbove();
            if (island.checkBlockCanBeSetToPosition(blockToPlace, oneAbovePlacement)) {
                if (island.placeBlockAt(blockToPlace, blockPosition)) {
                    DoorBlock upperDoorBlock = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(blockToPlace.getBlockType());
                    upperDoorBlock.setBlockTo(blockToPlace);
                    upperDoorBlock.setIsUpperPartOfDoor(true);
                    island.placeBlockAt(upperDoorBlock, oneAbovePlacement);
                    return true;
                }
            }
        }
        
        return false;
    }  
    
    @Override
    public boolean removeBlockFromIslandAt(Island island, BlockPosition blockPosition) {
        Block blockAtPosition = island.getBlockAt(blockPosition);
        if (blockAtPosition instanceof DoorBlock) {
            DoorBlock blockToRemove = (DoorBlock)island.getBlockAt(blockPosition);
            if (blockToRemove.getIsUpperPartOfDoor()) {
                BlockPosition positionOneBelow = blockPosition.getPositionOneBelow();
                island.removeBlockAt(blockPosition);
                island.removeBlockAt(positionOneBelow);
            } else {
                BlockPosition positionOneAbove = blockPosition.getPositionOneAbove();
                island.removeBlockAt(blockPosition);
                island.removeBlockAt(positionOneAbove);
            }
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean copyBlockToPositionOnIsland(Island island, BlockPosition blockPosition) {
        if (null != blockToPlace && (blockToPlace instanceof DoorBlock)) {
            BlockPosition oneAbovePlacement = blockPosition.getPositionOneAbove();
            if (island.checkBlockCanBeSetToPosition(blockToPlace, oneAbovePlacement)) {
                if (island.copyBlockTo(blockToPlace, blockPosition)) {
                    DoorBlock upperDoorBlock = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(blockToPlace.getBlockType());
                    upperDoorBlock.setBlockTo(blockToPlace);
                    upperDoorBlock.setIsUpperPartOfDoor(true);
                    island.copyBlockTo(upperDoorBlock, oneAbovePlacement);
                    return true;
                }
            }
        }
        
        return false;
    }    
    
    //</editor-fold>
}
