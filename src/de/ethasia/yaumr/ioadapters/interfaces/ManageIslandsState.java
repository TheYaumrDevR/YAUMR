package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.outsidedependencies.views.DisplayableGameState;

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
    public void showConfirmationWarningMessage(String message, ConfirmationActionTypes actionType);
}
