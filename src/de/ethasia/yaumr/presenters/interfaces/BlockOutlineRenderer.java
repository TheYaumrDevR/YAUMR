package de.ethasia.yaumr.presenters.interfaces;

import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.entities.Island;

/**
 * Provides methods to render a Block's outline.
 * 
 * @author R
 */
public interface BlockOutlineRenderer {
    
    public void unrenderCurrentlyRenderedObjects();
    public void renderBlockOutline(Island islandBlockIsOn, GlobalBlockPosition blockPosition);
}
