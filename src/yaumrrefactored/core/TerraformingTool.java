package yaumrrefactored.core;

import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;

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
    public abstract void interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position);
    
    //</editor-fold>
}
