package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.outsidedependencies.views.DisplayableGameState;

/**
 * Represents the controller for the view the game is in when the user enters the world editor.
 * 
 * @author R
 */
public interface WorldEditorBaseMenuState extends DisplayableGameState {
    
    public void gotoWorldEditorState();
    public void gotoIslandEditorState();
    public void gotoPreviousState();
}
