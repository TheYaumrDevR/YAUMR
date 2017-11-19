package de.ethasia.yaumr.blockengine.entities;

import com.jme3.math.Vector3f;

/**
 * Places a 1 x 1 block on the required position.
 * 
 * @author R
 */
public class SimpleBlockPlacementStrategy implements BlockPlacementStrategy {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockTypes blockType;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="BlockPlacementStrategy Implementations">
    
    @Override
    public void setBlockType(BlockTypes value) {
        blockType = value;
    }     
    
    @Override
    public boolean placeBlockInWorld(Vector3f interactionPoint, Island islandToInteractWith) {
        return islandToInteractWith.placeBlock(interactionPoint, blockType);
    }
    
    //</editor-fold>
}
