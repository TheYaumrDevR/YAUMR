package de.ethasia.yaumr.customcontrols.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a bar where spells or items can be quickly selected from.
 * 
 * @author Drawig
 */
public interface QuickSelectionBar extends NiftyControl {
    
    public QuickSelectableEntity reactToKeyInput(String keyActionName);
    public void addItemToPosition(QuickSelectableEntity item, int position);
    public boolean isButtonSelected(int position);
}
