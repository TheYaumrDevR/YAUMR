package de.ethasia.yaumr.blockengine.usecases.implementations;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.Chunk;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.usecases.interfaces.FallingBlockCellularAutomaton;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author R
 */
public class FallingBlockCellularAutomatonImpl implements FallingBlockCellularAutomaton {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Map<GlobalBlockPosition, Chunk> unstablePositionsInChunks;
    private float tickTimerInSeconds;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public FallingBlockCellularAutomatonImpl() {
        unstablePositionsInChunks = new HashMap<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void markPositionInChunkAsUnstable(GlobalBlockPosition position, Chunk chunk) {
        unstablePositionsInChunks.put(position, chunk);
    }
    
    @Override
    public void tick(float tpf) {
        tickTimerInSeconds += tpf;
        
        while (tickTimerInSeconds >= 0.1f) {
            advanceUnstableBlocks();
            tickTimerInSeconds -= 0.1f;
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void advanceUnstableBlocks() {
        Map<GlobalBlockPosition, Chunk> newUnstablePositionsInChunks = new HashMap<>();
        Set<Chunk> updatedChunks = new HashSet<>();
        
        for (GlobalBlockPosition unstablePosition : unstablePositionsInChunks.keySet()) {
            if (unstablePosition.getBlockPositionY() != 0) {
                Block[][][] blocksInChunk = unstablePositionsInChunks.get(unstablePosition).getBlocks();
            
                if (blocksInChunk[unstablePosition.getBlockPositionX()][unstablePosition.getBlockPositionY() - 1][unstablePosition.getBlockPositionZ()].getBlockType() == BlockTypes.AIR) {
                    newUnstablePositionsInChunks.put(unstablePosition.decrementY(), unstablePositionsInChunks.get(unstablePosition));
                    unstablePositionsInChunks.get(unstablePosition).moveBlockDown(unstablePosition);
                    updatedChunks.add(unstablePositionsInChunks.get(unstablePosition));
                    
                    if (unstablePosition.getBlockPositionY() != 255) {
                        Block blockAbove = blocksInChunk[unstablePosition.getBlockPositionX()][unstablePosition.getBlockPositionY() + 1][unstablePosition.getBlockPositionZ()];         
                
                        if (blockAbove.getBlockType().fallsDownWhenAirBelow()) {
                            newUnstablePositionsInChunks.put(unstablePosition.incrementY(), unstablePositionsInChunks.get(unstablePosition));
                        }                
                    }
                }                
            }
        }
        
        unstablePositionsInChunks = newUnstablePositionsInChunks;
        
        for (Chunk updatedChunk : updatedChunks) {
            YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandRenderer.class).updateModifiedChunk(updatedChunk.getParentIsland(), updatedChunk);        
        }
    }    
    
    //</editor-fold>
}
