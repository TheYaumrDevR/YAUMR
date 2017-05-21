package de.ethasia.yaumr.blockengine.usecases.interfaces;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.Island;

/**
 * Provides methods to place a block at the position the mouse is pointing at.
 * 
 * @author R
 */
public interface BlockPlacementUsecase {
    public void placeBlock(Vector3f interactionPoint, Island parentIsland, BlockTypes blocktype);
}
