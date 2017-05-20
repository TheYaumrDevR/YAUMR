package de.ethasia.yaumr.gamestates.interfaces;

/**
 * Represents a state of the game which can be displayed.
 * 
 * @author R
 */
public interface DisplayableGameState {
    
    /**
     * Makes this game state the active one, showing it on the game screen.
     */
    public void startDisplaying();
}
