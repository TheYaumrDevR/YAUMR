package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.TerraformingTool;
import java.util.Arrays;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsInteractor;
import java.util.List;

/**
 * Provides methods to select and set the properties of tools for terraforming.
 * 
 * @author 
 */
public class TerraformingToolsInteractorImpl implements TerraformingToolsInteractor {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private TerraformingTool[] otherTools;
    private TerraformingTool[] selectableTools;
    private int currentlySelectedToolIndex;
    private int currentPageNumber;
    private int pageCount;
    private int itemsPerPage;
    
    private IslandManipulationFacade islandManipulationFacade;
    private final TerraformingToolsQuickbarPresenter quickbarPresenter;
    private final ChunkPresenter chunkPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TerraformingToolsInteractorImpl() {
        quickbarPresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(TerraformingToolsQuickbarPresenter.class);
        chunkPresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ChunkPresenter.class);
        currentlySelectedToolIndex = -1;
    }
    
    @Override
    public void resetToDimensions(int pageCount, int itemsPerPage) {
        otherTools = new TerraformingTool[pageCount * itemsPerPage];
        selectableTools = new TerraformingTool[10];
        this.pageCount = pageCount;
        this.itemsPerPage = itemsPerPage;        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void setIslandManipulationFacade(IslandManipulationFacade islandManipulationFacade) {
        this.islandManipulationFacade = islandManipulationFacade;
    }
    
    @Override
    public void gotoNextPage() {
        if (currentPageNumber < pageCount - 1) {
            currentPageNumber += 1;
        }
    }
    
    @Override
    public void gotoPreviousPage() {
        if (currentPageNumber > 0) {
            currentPageNumber -= 1;
        }
    }
    
    @Override
    public void setSelectedToolIndex(int index) {
        if (index < 0 || index > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid selected tool index set.");
        }
        
        currentlySelectedToolIndex = index;
        quickbarPresenter.highlightQuickbarPosition(index);
    }
    
    @Override
    public void setToolToOtherPosition(TerraformingTool otherItem, int position) {
        if (position < 0 || position > otherTools.length - 1) {
            throw new IllegalArgumentException("Invalid position when trying to access a tool.");
        }
            
        otherTools[position] = otherItem;
    }
    
    @Override
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
        
        quickbarPresenter.showItemsOnQuickbar(getSelectableTools());
    }
    
    @Override
    public void setToolToSelectionPosition(TerraformingTool tool, int position) {
        if (position < 0 || position > selectableTools.length - 1) {
            throw new IllegalArgumentException("Invalid position when setting a selectable tool.");
        }
        
        selectableTools[position] = tool;
        
        quickbarPresenter.showItemsOnQuickbar(getSelectableTools());
    }
    
    @Override
    public TerraformingTool[] getOtherItemsOnCurrentPage() {
        TerraformingTool[] toolsOnCurrentPage = new TerraformingTool[itemsPerPage];
        int j = 0;
        
        for (int i = currentPageNumber * itemsPerPage; i < (currentPageNumber + 1) * itemsPerPage; i++) {
            toolsOnCurrentPage[j] = otherTools[i];
            j++;
        }
        
        return toolsOnCurrentPage;
    }
    
    @Override
    public TerraformingTool[] getSelectableTools() {
        return Arrays.copyOf(selectableTools, selectableTools.length);
    }
    
    @Override
    public void executeActionOfCurrentlySelectedTool(InteractionVector pointingPoint) {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            if (null != islandManipulationFacade) {
                BlockPosition interactionPosition = islandManipulationFacade.getBlockPositionOnCurrentIslandForInteractionVector(pointingPoint);
                if (null != interactionPosition) {
                    List<BlockPosition> changedBlockPositions = selectableTools[currentlySelectedToolIndex].interactWithIsland(islandManipulationFacade, interactionPosition);  
                    requestChunkRenderingForPositionsIfNecessary(changedBlockPositions);
                }                
            }
        }
    }
    
    @Override
    public void rotateCurrentlySelectedToolOnX() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnX();
        }
    }
    
    @Override
    public void rotateCurrentlySelectedToolOnY() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnY();
        }        
    }
    
    @Override
    public void rotateCurrentlySelectedToolOnZ() {
        if (selectableTools[currentlySelectedToolIndex] != null) {
            selectableTools[currentlySelectedToolIndex].rotateOnZ();
        }        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void requestChunkRenderingForPositionsIfNecessary(List<BlockPosition> changedBlockPositions) {
        if (changedBlockPositions.size() > 0) {
            for (BlockPosition changedPosition : changedBlockPositions) {
                int[] changedCoordinates = {changedPosition.x, changedPosition.y, changedPosition.z};
                chunkPresenter.setChangedPosition(changedCoordinates);
            }
            
            chunkPresenter.presentChunksForChangedPositions(islandManipulationFacade.getIsland());
        }
    }
    
    //</editor-fold>
}
