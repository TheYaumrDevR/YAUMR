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
public class TerraformingToolsQuickbarPresenterImpl implements TerraformingToolsQuickbarPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String QUICK_SELECTION_IMAGES_BASE_PATH = "Interface/ImagesGUI/HUD/QuickSelectionIcons/";            
    
    //</editor-fold>

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
        ItemDisplayData[] displayDataForGUI = new ItemDisplayData[tools.length];                    
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);

        if (null != islandEditorState) {
            for (int i = 0; i < tools.length; i++) {
                TerraformingTool tool = tools[i];
            
                if (null != tool) {
                    String toolImagePath = QUICK_SELECTION_IMAGES_BASE_PATH + tool.getTypeName() + ".png";
                    ItemDisplayData toolDisplayData = new ItemDisplayData();
                    toolDisplayData.setItemImagePath(toolImagePath);
                
                    displayDataForGUI[i] = toolDisplayData;
                }
            } 
            
            islandEditorState.showItemsOnQuickbar(displayDataForGUI);
        }
    }    
    
    //</editor-fold>    
}
