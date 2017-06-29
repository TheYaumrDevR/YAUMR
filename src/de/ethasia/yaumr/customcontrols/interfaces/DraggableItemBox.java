package de.ethasia.yaumr.customcontrols.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * This UI element displays items which can be dragged out of the box and dropped
 * somewhere else.
 * 
 * @author R
 */
public interface DraggableItemBox extends NiftyControl {
    
    public void setDisplayedItem(QuickSelectableEntity item);
    public QuickSelectableEntity getDisplayedItem();
}
