package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.AxisRotationValues;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a TerraformingTool with which the user places Blocks.
 * 
 * @author R
 */
public class BlockPlacementTerraformingTool extends TerraformingTool {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Block blockToPlace;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BlockPlacementTerraformingTool(Block block) {
        blockToPlace = block;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">

    @Override
    public void rotateOnX() {
        if (null != blockToPlace) {
            blockToPlace.rotateOnAxisX(AxisRotationValues.NINETY);
        }
    }

    @Override
    public void rotateOnY() {
        if (null != blockToPlace) {
            blockToPlace.rotateOnAxisY(AxisRotationValues.NINETY);
        }
    }

    @Override
    public void rotateOnZ() {
        if (null != blockToPlace) {
            blockToPlace.rotateOnAxisZ(AxisRotationValues.NINETY);
        }
    }

    @Override
    public List<BlockPosition> interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position) {
        List<BlockPosition> changedBlocks = new LinkedList<>();
        
        if (null != blockToPlace) {
            if (islandManipulationFacade.copyBlockTo(blockToPlace, position)) {
                islandManipulationFacade.performDailyUpdates();
                changedBlocks.add(position);
            }
        }
        
        return changedBlocks;
    }
    
    @Override
    public String getTypeName() {
        return blockToPlace.getBlockType().toString();
    }
    
    //</editor-fold>    
}
