package de.ethasia.yaumr.interactors.interfaces;

/**
 * An instance of this is responsible for showing warning messages in the new island initialization state in the world editor.
 * 
 * @author R
 */
public interface IslandInitializationStateWarningMessagesPresenter {
    
    public void showNonConfirmationWarning(String message);
    public void showConfirmationWarning(String message);
}
