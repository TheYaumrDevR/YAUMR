package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;

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
    public void interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position) {
        islandManipulationFacade.removeBlockAt(position);
    }  
    
    @Override
    public String getTypeName() {
        return "REMOVE_BLOCK";
    }    
    
    //</editor-fold>
}
