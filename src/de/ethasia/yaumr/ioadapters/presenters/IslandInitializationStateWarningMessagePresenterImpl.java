package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.ioadapters.interfaces.ConfirmationActionTypes;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;

/**
 *
 * @author R
 */
public class IslandInitializationStateWarningMessagePresenterImpl implements IslandInitializationStateWarningMessagesPresenter {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showNonConfirmationWarning(String message) {
    }

    @Override
    public void showConfirmationWarning(String message) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        ManageIslandsState islandInitializationState = dependencyResolver.getSingletonInstance(ManageIslandsState.class);
        
        if (null != islandInitializationState) {
            islandInitializationState.showConfirmationWarningMessage(message, ConfirmationActionTypes.CREATE_ISLAND_CONFIRMED);
        }        
    }    
    
    //</editor-fold>    
}
