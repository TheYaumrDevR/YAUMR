package de.ethasia.yaumr.blockengine.entities.base;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.blockengine.entities.Island;

/**
 * Represents an entity which can be placed on the quick selection bar.
 * 
 * @author R
 */
public interface QuickSelectableEntity {
    
    public String getQuickSelectionImagePath();
    public void executePrimaryAction(Vector3f interactionPoint, Island islandToInteractWith);
}
