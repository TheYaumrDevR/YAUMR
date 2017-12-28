package de.ethasia.yaumr.interactors.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.IslandCreationInteractorImpl;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.interactors.tests.mocks.IslandInitializationStateErrorMessagePresenterMock;
import de.ethasia.yaumr.interactors.tests.mocks.IslandInitializationStateWarningMessagesPresenterMock;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noro
 */
public class IslandCreationInteractorTest {
    
    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.removeRegisteredImplementation(IslandInitializationStateErrorMessagePresenter.class);
        dependencyResolver.removeRegisteredImplementation(IslandInitializationStateWarningMessagesPresenter.class);
        dependencyResolver.registerImplementation(IslandInitializationStateErrorMessagePresenter.class, IslandInitializationStateErrorMessagePresenterMock.class);
        dependencyResolver.registerImplementation(IslandInitializationStateWarningMessagesPresenter.class, IslandInitializationStateWarningMessagesPresenterMock.class);
    }

    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsInvalid_showsError() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance(0);
        
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");
        IslandInitializationStateErrorMessagePresenterMock.resetMethodCallCounts();
        assertEquals(1, calledShowErrorMethodCount);
    }
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsSmall_showsWarning() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance(7);
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showConfirmationWarning");
        IslandInitializationStateWarningMessagesPresenterMock.resetMethodCallCounts();
        assertEquals(1, calledShowConfirmationWarningMethodCount);    
    } 
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsValid_IslandManipulationFacadeIsCreated() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance(8);   
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showNonConfirmationWarning");
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        IslandManipulationFacade islandManipulationFacade = dependencyResolver.getSingletonInstance(IslandManipulationFacade.class);
        
        assertEquals(0, calledShowConfirmationWarningMethodCount);
        assertEquals(0, calledShowErrorMethodCount);
        assertNotNull(islandManipulationFacade);
    }      
}
