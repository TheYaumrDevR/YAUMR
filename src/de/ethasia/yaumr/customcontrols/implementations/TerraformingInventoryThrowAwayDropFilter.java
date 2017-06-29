package de.ethasia.yaumr.customcontrols.implementations;

import de.ethasia.yaumr.customcontrols.interfaces.InventoryGrid;
import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.DroppableDropFilter;

/**
 * This dropfilter is rejects drop operations and notifies the owner that the 
 * player moved an item in the throw away area.
 * 
 * @author R
 */
public class TerraformingInventoryThrowAwayDropFilter implements DroppableDropFilter {

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
    public boolean accept(Droppable dropSource, Draggable draggable, Droppable dropTarget) {
        if (null != owner) {
            owner.onDragAndDropItemRemovedFromGrid(dropSource, draggable, dropTarget);
        }
        
        return false;
    }    
    
    //</editor-fold>    
}
