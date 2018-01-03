package de.ethasia.yaumr.outsidedependencies.niftyguiextensions;

import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.InventoryGrid;
import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.DroppableDropFilter;

/**
 * This DropFilter rejects the standard nifty gui drag and drop operation.
 * It then tells the owning InventoryGridControl that a user attempted a drag
 * and drop operation. The InventoryGridControl can then decide how to handle
 * the drag and drop attempt.
 * 
 * @author R
 */
public class InventoryGridQuickbarDropFilter implements DroppableDropFilter {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private InventoryGrid owner;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setOwner(InventoryGrid value) {
        owner = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public boolean accept(Droppable dropSource, Draggable drgbl, Droppable droppedAt) {
        if (null != owner) {
            owner.onDragAndDropOperationCompleted(dropSource, drgbl, droppedAt);
        }
        
        return false;
    }    
    
    //</editor-fold>    
}
