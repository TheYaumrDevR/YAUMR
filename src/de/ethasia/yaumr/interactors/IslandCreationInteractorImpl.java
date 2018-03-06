package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.IslandManipulationFacadeImpl;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.IslandCreationInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;

/**
 *
 * @author R
 */
public class IslandCreationInteractorImpl implements IslandCreationInteractor {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">

    @Override
    public boolean createNewIslandWithRegisteredSingletonFacadeInstance(String userInput) {
        Integer edgeLengthInBlocks = getEdgeLengthInBlocksFromUserInput(userInput);
        
        if (null != edgeLengthInBlocks) {
            if (edgeLengthInBlocks < 1) {
                ErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ErrorMessagePresenter.class);
                errorMessagePresenter.showErrorMessage("Cannot create an island with no dimensions.");
                return false;
            } else if (edgeLengthInBlocks < 8) {
                IslandInitializationStateWarningMessagesPresenter warningMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandInitializationStateWarningMessagesPresenter.class);
                warningMessagePresenter.showConfirmationWarning("You are about to create a very small island with less than 8 blocks in length and width. Are you sure?");
                return false;
            } else if (edgeLengthInBlocks > 512) {
                ErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ErrorMessagePresenter.class);
                errorMessagePresenter.showErrorMessage("You cannot create Islands larger than 512 blocks wide.");
                return false;                
            }  
            
            createNewIslandWithFacadeAndRegisterFacadeInstance(edgeLengthInBlocks);  
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean createNewIslandWithFacadeInstanceWithoutUserConfirmationChecks(String userInput) {
        Integer edgeLengthInBlocks = getEdgeLengthInBlocksFromUserInput(userInput);
        
        if (null != edgeLengthInBlocks) {
            if (edgeLengthInBlocks < 1) {
                ErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ErrorMessagePresenter.class);
                errorMessagePresenter.showErrorMessage("Cannot create an island with no dimensions.");
                return false;
            } else if (edgeLengthInBlocks > 512) {
                ErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ErrorMessagePresenter.class);
                errorMessagePresenter.showErrorMessage("You cannot create Islands larger than 512 blocks wide."); 
                return false;
            }
                
            createNewIslandWithFacadeAndRegisterFacadeInstance(edgeLengthInBlocks);     
            return true;
        }         
        
        return false;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private Integer getEdgeLengthInBlocksFromUserInput(String userInput) {
        int edgeLengthInBlocks;
        
        try {
            edgeLengthInBlocks = Integer.parseInt(userInput);            
        } catch (NumberFormatException ex) {
            ErrorMessagePresenter errorMessagePresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ErrorMessagePresenter.class);
            errorMessagePresenter.showErrorMessage("Invalid input for Island size. Please enter a positive integer.");
            
            return null;
        }
        
        return edgeLengthInBlocks;
    }
    
    private void createNewIslandWithFacadeAndRegisterFacadeInstance(int islandEdgeLengthInBlocks) {
        Island island = new Island(islandEdgeLengthInBlocks);
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setNewlyCreatedIsland(island);

        for (int i = 0; i < islandEdgeLengthInBlocks; i++) {
            for (int j = 0; j < islandEdgeLengthInBlocks; j++) {
                for (int k = 0; k < 256; k++) {
                    BlockTypes blockType = k < 256 - 2 ? BlockTypes.ROCK : BlockTypes.EARTH;
                    
                    islandManipulationFacade.placeBlockAt(SimpleBlockFactory.createConcreteBlockFromBlockType(blockType), new BlockPosition(i, k, j));
                }            
            }            
        }
        
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(IslandManipulationFacade.class);
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(IslandManipulationFacade.class, islandManipulationFacade);        
    }
    
    //</editor-fold>
}
