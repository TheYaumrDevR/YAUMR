package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.MessageConfirmationAction;
import de.ethasia.yaumr.interactors.interfaces.WarningMessagePresenter;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithWarningMessages;

/**
 *
 * @author R
 */
public class WarningMessagePresenterImpl implements WarningMessagePresenter {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showNonConfirmationWarning(String message) {
    }

    @Override
    public void showConfirmationWarning(String message, MessageConfirmationAction confirmationAction) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        AppStateWithWarningMessages warningMessagesAppState = dependencyResolver.getSingletonInstance(AppStateWithWarningMessages.class);
        
        if (null != warningMessagesAppState) {
            warningMessagesAppState.showConfirmationWarningMessage(message, confirmationAction);
        }        
    }    
    
    //</editor-fold>    
}
