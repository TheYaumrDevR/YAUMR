package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.core.Island;

/**
 *
 * @author R
 */
public interface ChunkPresenter {
    
    public void setChangedPosition(int[] position);
    public void presentChunksForChangedPositions(Island island);
    public void presentAllChunksInIsland(Island island);
}
