package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.core.TerraformingTool;

/**
 *
 * @author R
 */
public interface TerraformingToolsQuickbarPresenter {
    
    public void highlightQuickbarPosition(int position);
    public void showItemsOnQuickbar(TerraformingTool[] tools);
}
