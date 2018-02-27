package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateErrorMessagePresenter;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;

/**
 *
 * @author R
 */
public class IslandInitializationStateErrorMessagePresenterImpl implements IslandInitializationStateErrorMessagePresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showErrorMessage(String errorMessage) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        ManageIslandsState islandInitializationState = dependencyResolver.getSingletonInstance(ManageIslandsState.class);
        
        if (null != islandInitializationState) {
            islandInitializationState.showErrorMessage(errorMessage);
        }
    }    
    
    //</editor-fold>
}
