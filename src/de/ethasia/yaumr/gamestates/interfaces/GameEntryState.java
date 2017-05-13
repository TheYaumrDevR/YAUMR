package de.ethasia.yaumr.gamestates.interfaces;

/**
 * Represents the state the game is in when launched.
 * 
 * @author Drawig
 */
public interface GameEntryState extends DisplayableGameState {
    
    public void gotoSaveGameSelectionState();
    public void gotoWorldEditorBaseState();
    public void gotoOptionsState();
    public void quitGame();
}
