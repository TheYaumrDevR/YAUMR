package de.ethasia.yaumr.ioadapters.interfaces;

/**
 * This presenter displays the Block interaction indicator.
 * This helps the player know which block he will interact with when executing an action.
 * 
 * @author R
 */
public interface BlockInteractionIndicatorPresenter {
    
    public void presentPointingIndicator(float pointingPositionX, float pointingPositionY, float pointingPositionZ);
}
