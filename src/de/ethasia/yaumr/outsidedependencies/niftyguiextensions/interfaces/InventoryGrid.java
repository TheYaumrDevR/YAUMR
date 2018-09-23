package de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces;

import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
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
    
    public void setItemDisplayDataOnPosition(ItemDisplayData displayData, int position);
    public void onDragAndDropOperationCompleted(Droppable dropSource, Draggable drgbl, Droppable droppedAt);
    public void onDragAndDropItemRemovedFromGrid(Droppable dropSource, Draggable draggable, Droppable droppedAt);
    
    public boolean isVisible();
    public void hide();
    public void show();
}
