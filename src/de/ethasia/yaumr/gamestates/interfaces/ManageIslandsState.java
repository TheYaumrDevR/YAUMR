package de.ethasia.yaumr.gamestates.interfaces;

/**
 * Represents the state where the player can create and edit islands.
 * 
 * @author R
 */
public interface ManageIslandsState extends DisplayableGameState {
    
    public void createNewIsland();
    public void editSelectedIsland();
    public void deleteSelectedIsland();
    public void goToPreviousState();
}
