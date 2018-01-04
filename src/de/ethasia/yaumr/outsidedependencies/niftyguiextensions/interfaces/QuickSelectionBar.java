package de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.lessvoid.nifty.controls.NiftyControl;

/**
 * Represents a bar where spells or items can be quickly selected from.
 * 
 * @author Drawig
 */
public interface QuickSelectionBar extends NiftyControl {
    
    public void reactToKeyInput(String keyActionName);
    public void addItemToPosition(QuickSelectableEntity item, int position);
    public boolean isButtonSelected(int position);
    public QuickSelectableEntity getEntityContainedInSelection();
}