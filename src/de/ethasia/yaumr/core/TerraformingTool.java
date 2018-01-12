package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import java.util.List;

/**
 * Represents a tool which helps the user terraform the current scene in the
 * Island editor.
 * 
 * @author R
 */
public abstract class TerraformingTool {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public abstract void rotateOnX();
    public abstract void rotateOnY();
    public abstract void rotateOnZ();
    public abstract List<BlockPosition> interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position);
    public abstract String getTypeName();
    
    //</editor-fold>
}
