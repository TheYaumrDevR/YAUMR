package de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces;

import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a button on a quick selection bar.
 * 
 * @author R
 */
public interface QuickSelectionBarButton extends NiftyControl {
    
    public void setSelected();
    public void setUnselected();
    public void setItemToDisplay(ItemDisplayData itemDisplayData);    
}
