package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author R
 */
public class BlockRemovalTerraformingTool extends TerraformingTool {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void rotateOnX() {
    }

    @Override
    public void rotateOnY() {
    }

    @Override
    public void rotateOnZ() {
    }

    @Override
    public List<BlockPosition> interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position) {
        List<BlockPosition> changedBlockPositions = new LinkedList<>();
        
        if (islandManipulationFacade.removeBlockAt(position)) {
            changedBlockPositions.add(position);
        }
        
        return changedBlockPositions;
    }  
    
    @Override
    public String getTypeName() {
        return "REMOVE_BLOCK";
    }    
    
    //</editor-fold>
}
