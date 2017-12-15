package yaumrrefactored.core;

import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.Block;
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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected void updateIslandState(long timeSinceLastTickInMS) {
        accumulatedTimeSinceLastUpdateInMS += timeSinceLastTickInMS;
        
        while (accumulatedTimeSinceLastUpdateInMS >= TIME_PER_UPDATE_IN_MILLIS) {
            List<BlockPosition> copyOfBlockPositionsToCheck = new LinkedList<>(blockPositionsToCheck);
            
            for (BlockPosition positionToCheck : copyOfBlockPositionsToCheck) {
                advanceUnstableBlocks(positionToCheck);
                accumulatedTimeSinceLastUpdateInMS -= TIME_PER_UPDATE_IN_MILLIS;                
            }
        }
    }
    
    private void advanceUnstableBlocks(BlockPosition positionToCheck) {
        BlockPosition positionOneBelow = positionToCheck.getPositionOneBelow();
        if (null != positionOneBelow) {
            updateBlockBelowIfNecessary(positionToCheck, positionOneBelow);
        } 
    }

    @Override
    protected String getAutomatonName() {
        return "fallingSandyBlock";
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBlockBelowIfNecessary(BlockPosition positionToCheck, BlockPosition positionBelow) {
        if (islandToUpdate.getBlockAt(positionToCheck).isAffectedByAutomatonType(getAutomatonName())) {
            if (islandToUpdate.getBlockAt(positionBelow).getBlockType().isDisplaced()) {
                if (islandToUpdate.getBlockAt(positionToCheck).getBlockType().displacesDisplacableBlock()) {
                    Block fallingBlock = islandToUpdate.getBlockAt(positionToCheck);
                    islandManipulationFacade.copyBlockTo(fallingBlock, positionBelow);
                    islandManipulationFacade.removeBlockAt(positionToCheck);
                }
            }
        }
    }
    
    //</editor-fold>
}
