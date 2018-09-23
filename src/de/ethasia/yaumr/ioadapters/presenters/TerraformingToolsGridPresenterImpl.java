package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsGridPresenter;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;

/**
 *
 * @author R
 */
public class TerraformingToolsGridPresenterImpl extends TerraformingToolsPresenter implements TerraformingToolsGridPresenter {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showInventoryGrid(TerraformingTool[] toolsInGrid) {
        ItemDisplayData[] displayDataForGUI = convertTerraformingToolsToDisplayData(toolsInGrid); 
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);

        if (null != islandEditorState) {
            if (displayDataForGUI.length > 0) {
                
            }   
        }
    }    
    
    //</editor-fold>    
}
