package de.ethasia.yaumr.customcontrols.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a grid displaying items in an inventory. They can be dragged out 
 * of the grid and moved somewhere else.
 *  
 * @author R
 */
public interface InventoryGrid extends NiftyControl {
    
    public void setItemOnPosition(QuickSelectableEntity item, int position);
    public void onDragAndDropOperationCompleted(Droppable dropSource, Draggable drgbl, Droppable droppedAt);
    public void onDragAndDropItemRemovedFromGrid(Droppable dropSource, Draggable draggable, Droppable droppedAt);
    
    public boolean inventoryGridIsVisible();
    public void hideInventoryGrid();
    public void showInventoryGrid();
}
