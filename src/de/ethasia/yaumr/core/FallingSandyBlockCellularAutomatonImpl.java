package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.Block;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author R
 */
public class FallingSandyBlockCellularAutomatonImpl extends BlockCellularAutomatonImpl {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int TIME_PER_UPDATE_IN_MILLIS = 100;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private float accumulatedTimeSinceLastUpdateInMS;
    private final List<BlockPosition> updatedBlockPositionsSinceLastTick;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public FallingSandyBlockCellularAutomatonImpl() {
        updatedBlockPositionsSinceLastTick = new LinkedList<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected void updateIslandState(long timeSinceLastTickInMS) {
        accumulatedTimeSinceLastUpdateInMS += timeSinceLastTickInMS;
        updatedBlockPositionsSinceLastTick.clear();
        
        while (accumulatedTimeSinceLastUpdateInMS >= TIME_PER_UPDATE_IN_MILLIS) {
            List<BlockPosition> copyOfBlockPositionsToCheck = new LinkedList<>(blockPositionsToCheck);
            
            for (BlockPosition positionToCheck : copyOfBlockPositionsToCheck) {
                advanceUnstableBlocks(positionToCheck);                
            }
            
            accumulatedTimeSinceLastUpdateInMS -= TIME_PER_UPDATE_IN_MILLIS;
        }
    }
    
    private void advanceUnstableBlocks(BlockPosition positionToCheck) {
        BlockPosition positionOneBelow = positionToCheck.getPositionOneBelow();
        if (null != positionOneBelow) {
            updateBlockBelowIfNecessary(positionToCheck, positionOneBelow);
        } 
    }
    
    @Override
    public List<BlockPosition> getUpdatedPositionsSinceLastTick() {
        return updatedBlockPositionsSinceLastTick;
    }

    @Override
    protected String getAutomatonName() {
        return "fallingSandyBlock";
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBlockBelowIfNecessary(BlockPosition positionToCheck, BlockPosition positionBelow) {
        if (null == islandToUpdate.getBlockAt(positionToCheck)) {
            return;
        }
        
        if (islandToUpdate.getBlockAt(positionToCheck).isAffectedByAutomatonType(getAutomatonName())) {
            boolean belowBlockIsDisplaced = null == islandToUpdate.getBlockAt(positionBelow) 
                    || islandToUpdate.getBlockAt(positionBelow).getBlockType().isDisplaced();
            
            if (belowBlockIsDisplaced) {
                if (islandToUpdate.getBlockAt(positionToCheck).getBlockType().displacesDisplacableBlock()) {
                    Block fallingBlock = islandToUpdate.getBlockAt(positionToCheck);
                    if (islandManipulationFacade.copyBlockTo(fallingBlock, positionBelow)) {
                        updatedBlockPositionsSinceLastTick.add(positionBelow);
                    }
                    
                    if (islandManipulationFacade.removeBlockAt(positionToCheck)) {
                        updatedBlockPositionsSinceLastTick.add(positionToCheck);
                    }
                }
            }
        }
    }
    
    //</editor-fold>
}
