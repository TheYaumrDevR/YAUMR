package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandDeletionInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandListPresenter;
import de.ethasia.yaumr.interactors.interfaces.Islands;
import de.ethasia.yaumr.interactors.interfaces.MessageConfirmationAction;
import de.ethasia.yaumr.interactors.interfaces.WarningMessagePresenter;

/**
 *
 * @author 
 */
public class IslandDeletionInteractorImpl implements IslandDeletionInteractor {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void deleteIsland(final IslandMetaData metaData) {
        MessageConfirmationAction confirmationAction = new MessageConfirmationAction() {
            
            @Override
            public void onMessageConfirmed() {
                deleteIslandRightAway(metaData);
            }
        };
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        WarningMessagePresenter warningMessagePresenter = dependencyResolver.getImplementationInstance(WarningMessagePresenter.class);
                
        if (null != warningMessagePresenter) {
            warningMessagePresenter.showConfirmationWarning("Do you wish to delete the selected island?", confirmationAction);
        }
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void deleteIslandRightAway(IslandMetaData metaData) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();        
        
        try {
            Islands islands = dependencyResolver.getImplementationInstance(Islands.class);
            
            if (null != islands) {
                islands.deleteIsland(metaData);
                IslandListPresenter islandListPresenter = dependencyResolver.getImplementationInstance(IslandListPresenter.class);
                
                if (null != islandListPresenter) {
                    islandListPresenter.showAvailableIslandList(islands.getMetadataOfAllAvailableIslands());
                }
            }
        } catch (Exception ex) {
            ErrorMessagePresenter errorMessagePresenter = dependencyResolver.getImplementationInstance(ErrorMessagePresenter.class);
            if (null != errorMessagePresenter) {
                errorMessagePresenter.showErrorMessage("Could not delete island: " + metaData.getIslandName() + ".");
            }
        }
    }
    
    //</editor-fold>
}
