package de.ethasia.yaumr.gamestates.interfaces;

/**
 * Represents the state where a user can create and modify islands.
 * 
 * @author R
 */
public interface IslandEditorState extends DisplayableGameState {
    
    public void showMainMenu();
    public void showHelpPanel();
    public void showTerraformingToolbox();
    public void hideAllVisibleGUIItems();
    public void activateMovementControls();
    public void deactivateMovementControls();
}
