package de.ethasia.yaumr.blockengine.entities;

import com.jme3.math.Vector3f;

/**
 * Represents a BlockPlacementStrategy which places a door into the world.
 * For this the block on the pointed position and one block above must be free.
 * 
 * @author R
 */
public class DoorBlockPlacementStrategy implements BlockPlacementStrategy {
    
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
        return false;
    }
    
    //</editor-fold>    
}
