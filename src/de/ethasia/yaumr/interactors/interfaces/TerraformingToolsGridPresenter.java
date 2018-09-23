package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.core.TerraformingTool;

/**
 * Presents the grid showing available terraforming tools in the Island creation state.
 * 
 * @author R
 */
public interface TerraformingToolsGridPresenter {
    
    public void showInventoryGrid(TerraformingTool[] toolsInGrid);
}
