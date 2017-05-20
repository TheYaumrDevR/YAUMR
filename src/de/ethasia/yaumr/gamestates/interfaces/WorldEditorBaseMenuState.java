package de.ethasia.yaumr.gamestates.interfaces;

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
