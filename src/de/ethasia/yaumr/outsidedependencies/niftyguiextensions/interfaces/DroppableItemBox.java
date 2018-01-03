package de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.InventoryGridQuickbarDropFilter;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a control where the player can drop a QuickSelectableEntity into.
 * 
 * @author R
 */
public interface DroppableItemBox extends NiftyControl {

    public void setDropFilter(InventoryGridQuickbarDropFilter dropFilter);
    public void setDisplayedItem(QuickSelectableEntity item);
    public QuickSelectableEntity getDisplayedItem();
    public void clearDisplayedItem();
}
