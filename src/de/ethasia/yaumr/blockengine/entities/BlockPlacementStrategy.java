package de.ethasia.yaumr.blockengine.entities;

import com.jme3.math.Vector3f;

/**
 * Instances of this interface encapsulate logic to place blocks. The most common
 * BlockPlacementStrategy simply places a 1*1 block in the world, if it can. 
 * Other block placement strategies place multiple blocks around the original block
 * or do some other additional logic.
 * 
 * @author R
 */
public interface BlockPlacementStrategy {
    
    public void setBlockType(BlockTypes value);
    public boolean placeBlockInWorld(Vector3f interactionPoint, Island islandToInteractWith);
}
