package yaumrrefactored.core.interfaces;

import yaumrrefactored.core.BlockPosition;
import yaumrrefactored.core.Island;

/**
 * Provides the base methods used with cellular automata manipulating an island's
 * blocks.
 * 
 * @author R
 */
public interface BlockCellularAutomaton {
    
    public BlockCellularAutomaton setIslandToUpdate(Island value);
    
    public void setIslandManipulationFacade(IslandManipulationFacade value);
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Updates the automaton's state based on the ellapsed time since the last tick.
     * 
     * @param timeSinceLastTickInMS The time in milliseconds since the last tick.
     */    
    public void tick(long timeSinceLastTickInMS); 
    
    public void setChangedPosition(BlockPosition changedPosition);    
}
