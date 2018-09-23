package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;

/**
 *
 * @author R
 */
public abstract class TerraformingToolsPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final String QUICK_SELECTION_IMAGES_BASE_PATH = "Interface/ImagesGUI/HUD/QuickSelectionIcons/";     
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    protected final ItemDisplayData[] convertTerraformingToolsToDisplayData(TerraformingTool[] terraformingTools) {
        ItemDisplayData[] displayDataForGUI = new ItemDisplayData[terraformingTools.length];                    
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);

        if (null != islandEditorState) {
            for (int i = 0; i < terraformingTools.length; i++) {
                TerraformingTool tool = terraformingTools[i];
            
                if (null != tool) {
                    String toolImagePath = TerraformingToolsPresenter.QUICK_SELECTION_IMAGES_BASE_PATH + tool.getTypeName() + ".png";
                    ItemDisplayData toolDisplayData = new ItemDisplayData();
                    toolDisplayData.setItemImagePath(toolImagePath);
                
                    displayDataForGUI[i] = toolDisplayData;
                }
            } 
        }  
        
        return displayDataForGUI;
    }
    
    //</editor-fold>
}
