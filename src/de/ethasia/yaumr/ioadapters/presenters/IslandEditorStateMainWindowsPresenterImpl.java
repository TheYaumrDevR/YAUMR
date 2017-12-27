package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainWindowsPresenter;

/**
 * Presents and hides the island editor's main controls. 
 * 
 * @author R
 */
public class IslandEditorStateMainWindowsPresenterImpl implements IslandEditorStateMainWindowsPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IslandEditorState islandEditorState;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public IslandEditorStateMainWindowsPresenterImpl() {
        islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Interface Overrides">
    
    @Override
    public void hideAllChildren() {
        islandEditorState.hideAllVisibleGUIItems();
        islandEditorState.activateMovementControls();
    }

    @Override
    public void showHelpPanel() {
        islandEditorState.showHelpPanel();
        islandEditorState.deactivateMovementControls();
    }

    @Override
    public void showMainMenu() {
        islandEditorState.showMainMenu();
        islandEditorState.deactivateMovementControls();
    }

    @Override
    public void showToolbox() {
        islandEditorState.showTerraformingToolbox();
        islandEditorState.deactivateMovementControls();
    }

    @Override
    public void hideHelpPanel() {
        islandEditorState.hideAllVisibleGUIItems();
        islandEditorState.activateMovementControls();
    }

    @Override
    public void hideMainMenu() {
        islandEditorState.hideAllVisibleGUIItems();
        islandEditorState.activateMovementControls();
    }

    @Override
    public void hideToolBox() {
        islandEditorState.hideAllVisibleGUIItems();
        islandEditorState.activateMovementControls();
    }    
    
    //</editor-fold>
}
