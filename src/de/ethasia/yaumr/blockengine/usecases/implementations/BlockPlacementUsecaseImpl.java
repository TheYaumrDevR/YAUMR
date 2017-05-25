package de.ethasia.yaumr.blockengine.usecases.implementations;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.blockengine.usecases.interfaces.BlockPlacementUsecase;

/**
 *
 * @author R
 */
public class BlockPlacementUsecaseImpl implements BlockPlacementUsecase {

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void placeBlock(Vector3f interactionPoint, Island parentIsland, BlockTypes blocktype) {
        Block placedBlock = new Block();
        placedBlock.setBlockType(blocktype);
            
        parentIsland.placeBlock(interactionPoint, placedBlock);
    }    
    
    //</editor-fold>
}
