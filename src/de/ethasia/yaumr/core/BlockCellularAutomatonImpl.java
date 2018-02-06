package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import java.util.LinkedList;
import java.util.List;
import de.ethasia.yaumr.core.interfaces.BlockCellularAutomaton;

/**
 * Represents a cellular automaton which updates an Island based on the Blocks in it.
 * 
 * @author R
 */
public abstract class BlockCellularAutomatonImpl implements BlockCellularAutomaton {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    protected Island islandToUpdate;
    protected IslandManipulationFacade islandManipulationFacade;
    protected List<BlockPosition> blockPositionsToCheck;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    @Override
    public BlockCellularAutomatonImpl setIslandToUpdate(Island value) {
        islandToUpdate = value;
        blockPositionsToCheck = new LinkedList<>();
        return this;
    }
    
    @Override
    public void setIslandManipulationFacade(IslandManipulationFacade value) {
        islandManipulationFacade = value;
    }
    
    public abstract List<BlockPosition> getUpdatedPositionsSinceLastTick();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Updates the automaton's state based on the ellapsed time since the last tick.
     * 
     * @param timeSinceLastTickInMS The time in milliseconds since the last tick.
     */  
    @Override
    public void tick(long timeSinceLastTickInMS) {
        if (null != islandToUpdate) {
            if (blockPositionsToCheck.size() > 0) {
                updateIslandState(timeSinceLastTickInMS);
            }            
        }
    }  
    
    @Override
    public void setChangedPosition(BlockPosition changedPosition) {
        if (null != islandToUpdate) {
            Block changedBlock = islandToUpdate.getBlockAt(changedPosition);
            
            if (null == changedBlock) {
                if (blockPositionsToCheck.contains(changedPosition)) {
                    blockPositionsToCheck.remove(changedPosition);
                }                
                
                return;
            }
            
            if (changedBlock.isAffectedByAutomatonType(getAutomatonName())) {
                if (!blockPositionsToCheck.contains(changedPosition)) {
                    blockPositionsToCheck.add(changedPosition);
                }
            } else {
                if (blockPositionsToCheck.contains(changedPosition)) {
                    blockPositionsToCheck.remove(changedPosition);
                }
            }
        }
    }
    
    /**
     * Implements the concrete Island updating logic. Called by the tick method.
     * 
     * @param timeSinceLastTickInMS The time in milliseconds since this method was last called.
     */
    protected abstract void updateIslandState(long timeSinceLastTickInMS);
    
    /**
     * The unique name of this automaton. This is referenced by BlockTypes to
     * determine whether a BlockType is affected by a certain automaton instance.
     * 
     * @return 
     */
    protected abstract String getAutomatonName();
    
    //</editor-fold>
}
