package de.ethasia.yaumr.interactors.interfaces;

/**
 * Represents an interactor which is responsible for creating a new island 
 * (for the island creation editor).
 * 
 * @author R
 */
public interface IslandCreationInteractor {

    /**
     * Creates a new island with the corresponding IslandManipulationFacade instance to manipulate it.
     * If edgeLengthInBlocks is small a warning is displayed.
     * 
     * @param userInput The input the user set when creating an Island. 
     * @return True if the creation is successful.
     */
    public boolean createNewIslandWithRegisteredSingletonFacadeInstance(String userInput);
    
    public boolean createNewIslandWithFacadeInstanceWithoutUserConfirmationChecks(String userInput);
}
