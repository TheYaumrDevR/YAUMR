package de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces;

import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a bar where spells or items can be quickly selected from.
 * 
 * @author Drawig
 */
public interface QuickSelectionBar extends NiftyControl {
    
    public void setItemDisplayData(ItemDisplayData[] displayData);
    public int getItemIndexForKeyActionName(String keyActionName);    
    public void highlightSelectionAtIndex(int itemIndex);    
    public boolean isButtonSelected(int position);
}
