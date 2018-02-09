package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import java.util.LinkedList;
import java.util.List;

/**
 * This automaton is responsible for doing the daily updates of the various earth type blocks.
 * EARTH -> GRASSY_EARTH
 * EARTH_PLOWED -> EARTH
 * etc.
 * 
 * @author R
 */
public class EarthBlockTypesDailyUpdateCellularAutomaton extends BlockCellularAutomatonImpl {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<BlockPosition> updatedPositionsSinceLastTick;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public EarthBlockTypesDailyUpdateCellularAutomaton() {
        updatedPositionsSinceLastTick = new LinkedList<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public List<BlockPosition> getUpdatedPositionsSinceLastTick() {
        return updatedPositionsSinceLastTick; 
    }

    @Override
    protected void updateIslandState(long timeSinceLastTickInMS) {
        updatedPositionsSinceLastTick.clear();
        
        List<BlockPosition> copyOfBlockPositionsToCheck = new LinkedList<>(blockPositionsToCheck);
            
        for (BlockPosition positionToCheck : copyOfBlockPositionsToCheck) {
            updateBlockIfNecessary(positionToCheck);
        }        
    }

    @Override
    protected String getAutomatonName() {
        return "earthBlocksDailyUpdate";
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBlockIfNecessary(BlockPosition positionToCheck) {
        BlockPosition positionAbove = positionToCheck.getPositionOneAbove();
        Block blockToCheck = islandToUpdate.getBlockAt(positionToCheck);
        Block blockAbove = islandToUpdate.getBlockAt(positionAbove);
        
        if (null == blockToCheck) {
            return;
        } 
        
        if (blockToCheck.isAffectedByAutomatonType(getAutomatonName()) 
                && (null == blockAbove || !blockAbove.bottomFaceIsCovering())) {
            double randomNumber = Math.random();
            boolean updatePlowedBlock = randomNumber >= 0.75f;
            
            switch(blockToCheck.getBlockType()) {
                case EARTH:
                    blockToCheck.resetBlockToType(BlockTypes.GRASSY_EARTH);
                    break;
                case EARTH_PLOWED:
                    if (updatePlowedBlock) {
                        blockToCheck.resetBlockToType(BlockTypes.EARTH);
                    }
                    break;
                case EARTH_WATERED:
                    blockToCheck.resetBlockToType(BlockTypes.EARTH);
                    break;
                case EARTH_PLOWED_WATERED:
                    blockToCheck.resetBlockToType(BlockTypes.EARTH_PLOWED);
                    break;
                case EARTH_SEEDED_WATERED:
                    blockToCheck.resetBlockToType(BlockTypes.EARTH_SEEDED);
                    break;
            }
            
            islandManipulationFacade.copyBlockTo(blockToCheck, positionToCheck);
            updatedPositionsSinceLastTick.add(positionToCheck);
        }
    }
    
    //</editor-fold>
}
