package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.Block;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a BlockCellularAutomaton which turns grassy earth blocks into 
 * plain earth blocks if certain blocks are above them.
 * 
 * @author R
 */
public class GrassToEarthCellularAutomatonImpl extends BlockCellularAutomatonImpl {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int TIME_PER_UPDATE_IN_MILLIS = 200;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private long accumulatedTimeSinceLastUpdateInMS;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
     
    @Override
    protected void updateIslandState(long timeSinceLastTickInMS) {
        accumulatedTimeSinceLastUpdateInMS += timeSinceLastTickInMS;
        
        while (accumulatedTimeSinceLastUpdateInMS >= TIME_PER_UPDATE_IN_MILLIS) {
            List<BlockPosition> copyOfBlockPositionsToCheck = new LinkedList<>(blockPositionsToCheck);
            
            for (BlockPosition positionToCheck : copyOfBlockPositionsToCheck) {
                handleBlockUpdate(positionToCheck);
                accumulatedTimeSinceLastUpdateInMS -= TIME_PER_UPDATE_IN_MILLIS;                
            }
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void handleBlockUpdate(BlockPosition affectedBlockPosition) {
        BlockPosition positionOneAbove = affectedBlockPosition.getPositionOneAbove();
        if (null != positionOneAbove) {
            Block blockAbove = islandToUpdate.getBlockAt(positionOneAbove);
            if (null != blockAbove) {
                Block blockBelow = islandToUpdate.getBlockAt(affectedBlockPosition);
                
                if (null != blockBelow) {
                    updateBlockStateIfNecessary(blockAbove, blockBelow);
                }
            }
        }                   
    }
    
    private void updateBlockStateIfNecessary(Block blockAbove, Block blockBelow) {
        if (blockAbove.bottomFaceIsCovering()) {
            if (blockBelow.isAffectedByAutomatonType(getAutomatonName())) {
                if (blockBelow.getBlockType() != BlockTypes.EARTH_PLOWED_WATERED) {
                    blockBelow.resetBlockToType(BlockTypes.EARTH);
                } else {
                    blockBelow.resetBlockToType(BlockTypes.EARTH_WATERED);
                }
            }            
        }
    }
    
    @Override
    protected String getAutomatonName() {
        return "grassPlowedEarthToEarth";
    }    
    
    //</editor-fold>
}
