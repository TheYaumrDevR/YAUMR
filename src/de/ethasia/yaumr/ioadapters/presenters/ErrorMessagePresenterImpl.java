package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithErrorMessages;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;

/**
 *
 * @author R
 */
public class ErrorMessagePresenterImpl implements ErrorMessagePresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showErrorMessage(String errorMessage) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        AppStateWithErrorMessages errorMessageState = dependencyResolver.getSingletonInstance(AppStateWithErrorMessages.class);
        
        if (null != errorMessageState) {
            errorMessageState.showErrorMessage(errorMessage);
        }
    }    
    
    //</editor-fold>
}
