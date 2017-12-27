package yaumrrefactored.interactors.interfaces;

/**
 * Represents a presenter which is responsible for showing and hiding the major 
 * UI controls in the IslandEditorState.
 * 
 * @author R
 */
public interface IslandEditorStateMainWindowsPresenter {
    
    public void hideAllChildren();
    public void showHelpPanel();
    public void showMainMenu();
    public void showToolbox();
    public void hideHelpPanel();
    public void hideMainMenu();
    public void hideToolBox();
}
