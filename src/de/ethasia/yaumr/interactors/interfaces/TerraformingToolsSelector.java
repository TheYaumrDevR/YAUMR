package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;

/**
 *
 * @author R
 */
public interface TerraformingToolsSelector {
    
    public void resetToDimensions(int pageCount, int itemsPerPage);
    public void setIslandManipulationFacade(IslandManipulationFacade islandManipulationFacade);
    public void gotoNextPage();
    public void gotoPreviousPage();
    public void setSelectedToolIndex(int index);
    public void setToolToOtherPosition(TerraformingTool otherItem, int position);
    public void swapToolsOnSelectionPositions(int firstPosition, int secondPosition);
    public void setToolToSelectionPosition(TerraformingTool tool, int position);
    public TerraformingTool[] getOtherItemsOnCurrentPage();
    public TerraformingTool[] getSelectableTools();
    public void executeActionOfCurrentlySelectedTool(InteractionVector pointingPoint);
    public void rotateCurrentlySelectedToolOnX();
    public void rotateCurrentlySelectedToolOnY();
    public void rotateCurrentlySelectedToolOnZ();  
}
