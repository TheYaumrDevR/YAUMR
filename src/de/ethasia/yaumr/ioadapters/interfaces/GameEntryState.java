package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.outsidedependencies.views.DisplayableGameState;

/**
 * Represents the state the game is in when launched.
 * 
 * @author R
 */
public interface GameEntryState extends DisplayableGameState {
    
    public void gotoSaveGameSelectionState();
    public void gotoWorldEditorBaseState();
    public void gotoOptionsState();
    public void quitGame();
}
