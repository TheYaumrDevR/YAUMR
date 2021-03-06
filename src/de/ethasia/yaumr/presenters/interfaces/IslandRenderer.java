package de.ethasia.yaumr.presenters.interfaces;

import de.ethasia.yaumr.blockengine.entities.Chunk;
import de.ethasia.yaumr.blockengine.entities.Island;

/**
 * A renderer responsible for rendering islands.
 * 
 * @author R
 */
public interface IslandRenderer {
    
    public void updateModifiedChunk(Island island, Chunk chunk);
    public void removeRenderedData();
}
