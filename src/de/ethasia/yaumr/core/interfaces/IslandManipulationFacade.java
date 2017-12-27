package de.ethasia.yaumr.core.interfaces;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.InteractionVector;

/**
 * Represents a facade which provides interface methods to place and remove blocks from the current Island. 
 * All manipulation should be done exclusively through his class.
 * 
 * @author R
 */
public interface IslandManipulationFacade {
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setIsland(Island islandToChange);
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Facade Methods">
    
    public void placeBlockAt(Block block, BlockPosition position);
    
    public void removeBlockAt(BlockPosition position);
    
    public void copyBlockTo(Block blockToCopy, BlockPosition position);  
    
    public void tick(long timeSinceLastTickInMS);
    
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(InteractionVector vector);
    
    //</editor-fold>    
}
