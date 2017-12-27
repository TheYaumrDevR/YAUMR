package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.core.TerraformingTool;
import java.util.Arrays;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;

/**
 * Provides methods to select and set the properties of tools for terraforming.
 * 
 * @author 
 */
public class TerraformingToolsSelector {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final TerraformingTool[] otherTools;
    private final TerraformingTool[] selectableTools;
    private int currentlySelectedToolIndex;
    private int currentPageNumber;
    private final int pageCount;
    private final int itemsPerPage;
    
    private IslandManipulationFacade islandManipulationFacade;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TerraformingToolsSelector(int pageCount, int itemsPerPage) {
        otherTools = new TerraformingTool[pageCount * itemsPerPage];
        selectableTools = new TerraformingTool[10];
        this.pageCount = pageCount;
        this.itemsPerPage = itemsPerPage;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setIslandManipulationFacade(IslandManipulationFacade islandManipulationFacade) {
        this.islandManipulationFacade = islandManipulationFacade;
    }
    
    public void gotoNextPage() {
        if (currentPageNumber < pageCount - 1) {
            currentPageNumber += 1;
        }
    }
    
    public void gotoPreviousPage() {
        if (currentPageNumber > 0) {
            currentPageNumber -= 1;
        }
    }
    
    public void setSelectedToolIndex(int index) {
        if (index < 0 || index > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid selected tool index set.");
        }
        
        currentlySelectedToolIndex = index;
    }
    
    public void setToolToOtherPosition(TerraformingTool otherItem, int position) {
        if (position < 0 || position > otherTools.length - 1) {
            throw new IllegalArgumentException("Invalid position when trying to access a tool.");
        }
            
        otherTools[position] = otherItem;
    }
    
    public void swapToolsOnSelectionPositions(int firstPosition, int secondPosition) {
        if (firstPosition < 0 || firstPosition > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid first position set when swapping selectable tools.");
        }    
        
        if (secondPosition < 0 || secondPosition > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid second position set when swapping selectable tools.");
        } 
        
        TerraformingTool firstTool = selectableTools[firstPosition];
        TerraformingTool secondTool = selectableTools[secondPosition];
        
        selectableTools[firstPosition] = secondTool;
        selectableTools[secondPosition] = firstTool;
    }
    
    public void setToolToSelectionPosition(TerraformingTool tool, int position) {
        if (position < 0 || position > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid position when setting a selectable tool.");
        }
        
        selectableTools[position] = tool;
    }
    
    public TerraformingTool[] getOtherItemsOnCurrentPage() {
        TerraformingTool[] toolsOnCurrentPage = new TerraformingTool[itemsPerPage];
        int j = 0;
        
        for (int i = currentPageNumber * itemsPerPage; i < (currentPageNumber + 1) * itemsPerPage; i++) {
            toolsOnCurrentPage[j] = otherTools[i];
            j++;
        }
        
        return toolsOnCurrentPage;
    }
    
    public TerraformingTool[] getSelectableTools() {
        return Arrays.copyOf(selectableTools, selectableTools.length);
    }
    
    public void executeActionOfCurrentlySelectedTool(InteractionVector pointingPoint) {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            if (null != islandManipulationFacade) {
                BlockPosition interactionPosition = islandManipulationFacade.getBlockPositionOnCurrentIslandForInteractionVector(pointingPoint);
                if (null != interactionPosition) {
                    selectableTools[currentlySelectedToolIndex].interactWithIsland(islandManipulationFacade, interactionPosition);                                    
                }                
            }
        }
    }
    
    public void rotateCurrentlySelectedToolOnX() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnX();
        }
    }
    
    public void rotateCurrentlySelectedToolOnY() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnY();
        }        
    }
    
    public void rotateCurrentlySelectedToolOnZ() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnZ();
        }        
    }
    
    //</editor-fold>
}
