package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;

/**
 *
 * @author R
 */
public class TerraformingToolsQuickbarPresenterImpl extends TerraformingToolsPresenter implements TerraformingToolsQuickbarPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void highlightQuickbarPosition(int position) {
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);

        if (null != islandEditorState) {
            islandEditorState.selectItemOnQuickbar(position);
        }
    }

    @Override
    public void showItemsOnQuickbar(TerraformingTool[] tools) {
        ItemDisplayData[] displayDataForGUI = convertTerraformingToolsToDisplayData(tools); 
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);

        if (null != islandEditorState) {
            if (displayDataForGUI.length > 0) {
                islandEditorState.showItemsOnQuickbar(displayDataForGUI);
            }
        }
    }    
    
    //</editor-fold>    
}
