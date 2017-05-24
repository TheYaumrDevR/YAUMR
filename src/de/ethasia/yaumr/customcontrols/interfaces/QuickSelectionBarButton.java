package de.ethasia.yaumr.customcontrols.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a button on a quick selection bar.
 * 
 * @author R
 */
public interface QuickSelectionBarButton extends NiftyControl {
    
    public void setSelected();
    public void setUnselected();
    public void setItemToSelect(QuickSelectableEntity entityToSelect);
    public QuickSelectableEntity getContainedItem();
}
