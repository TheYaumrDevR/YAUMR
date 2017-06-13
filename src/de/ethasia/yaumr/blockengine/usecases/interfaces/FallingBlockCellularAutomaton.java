package de.ethasia.yaumr.blockengine.usecases.interfaces;

import de.ethasia.yaumr.blockengine.entities.Chunk;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;

/**
 *
 * @author R
 */
public interface FallingBlockCellularAutomaton {
    
    public void markPositionInChunkAsUnstable(GlobalBlockPosition position, Chunk chunk);
    public void tick(float tpf);
}
