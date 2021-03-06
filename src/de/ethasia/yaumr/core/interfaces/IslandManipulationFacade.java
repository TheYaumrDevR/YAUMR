package de.ethasia.yaumr.core.interfaces;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.InteractionVector;
import java.util.List;
import java.util.UUID;

/**
 * Represents a facade which provides interface methods to place and remove blocks from the current Island. 
 * All manipulation should be done exclusively through his class.
 * 
 * @author R
 */
public interface IslandManipulationFacade {
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setNewlyCreatedIsland(Island islandToChange);
    
    public void setLoadedIsland(Island islandToChange, String name, UUID islandGUID);
    
    /**
     * Retrieves the Island this Facade works on. Do not use it to edit the Island
     * directly. The Island is retrieved so that its data can be passed to renderers.
     * The cleaner way would be to create copies of the necessary Block data and pass it to the renderers.
     * But object creation is a runtime costly operation compared to primitive operations.
     * Especially with the amount of Blocks present on an Island.
     * 
     * @return 
     */
    public Island getIsland();
    
    public boolean isCreatingNewIsland();
    
    public UUID getIslandGUID();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Facade Methods">
    
    public boolean placeBlockAt(Block block, BlockPosition position);
    
    public boolean removeBlockAt(BlockPosition position);
    
    public boolean copyBlockTo(Block blockToCopy, BlockPosition position);  
        
    public List<BlockPosition> tick(long timeSinceLastTickInMS);
    
    public List<BlockPosition> performDailyUpdates();
    
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(InteractionVector vector);
    
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(float pointingPositionX, float pointingPositionY, float pointingPositionZ);
    
    public int getIslandEdgeLengthInBlocks();
    
    //</editor-fold>    
}
