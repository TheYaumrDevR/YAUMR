package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.IslandManipulationFacadeImpl;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.IslandCreationInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;

/**
 *
 * @author R
 */
public class IslandCreationInteractorImpl implements IslandCreationInteractor {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">

    @Override
    public void createNewIslandWithRegisteredSingletonFacadeInstance(int edgeLengthInBlocks) {
        if (edgeLengthInBlocks < 1) {
            IslandInitializationStateErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandInitializationStateErrorMessagePresenter.class);
            errorMessagePresenter.showErrorMessage("Cannot create an island with no dimensions.");
            return;
        } else if (edgeLengthInBlocks < 8) {
            IslandInitializationStateWarningMessagesPresenter warningMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandInitializationStateWarningMessagesPresenter.class);
            warningMessagePresenter.showConfirmationWarning("You are about to create a very small island with less than 8 blocks in length and width. Are you sure?");
            return;
        }
        
        createNewIslandWithFacadeAndRegisterFacadeInstance(edgeLengthInBlocks);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void createNewIslandWithFacadeAndRegisterFacadeInstance(int islandEdgeLengthInBlocks) {
        Island island = new Island(islandEdgeLengthInBlocks);
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setIsland(island);
        
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(IslandManipulationFacade.class);
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(IslandManipulationFacade.class, islandManipulationFacade);        
    }
    
    //</editor-fold>
}
