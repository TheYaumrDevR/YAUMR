package de.ethasia.yaumr.interactors.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.EarthBlockTypesDailyUpdateCellularAutomaton;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.core.tests.mocks.EarthBlockTypesDailyUpdateCellularAutomatonMock;
import de.ethasia.yaumr.interactors.IslandCreationInteractorImpl;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.interactors.tests.mocks.IslandInitializationStateErrorMessagePresenterMock;
import de.ethasia.yaumr.interactors.tests.mocks.IslandInitializationStateWarningMessagesPresenterMock;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;

/**
 *
 * @author Noro
 */
public class IslandCreationInteractorTest {
    
    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.removeRegisteredImplementation(ErrorMessagePresenter.class);
        dependencyResolver.removeRegisteredImplementation(IslandInitializationStateWarningMessagesPresenter.class);
        dependencyResolver.removeRegisteredImplementation(EarthBlockTypesDailyUpdateCellularAutomaton.class);
        dependencyResolver.registerImplementation(ErrorMessagePresenter.class, IslandInitializationStateErrorMessagePresenterMock.class);
        dependencyResolver.registerImplementation(IslandInitializationStateWarningMessagesPresenter.class, IslandInitializationStateWarningMessagesPresenterMock.class);
        dependencyResolver.registerImplementation(EarthBlockTypesDailyUpdateCellularAutomaton.class, EarthBlockTypesDailyUpdateCellularAutomatonMock.class);        
    }

    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsInvalid_showsError() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        boolean result = testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance("0");
        
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");
        IslandInitializationStateErrorMessagePresenterMock.resetMethodCallCounts();
        assertEquals(1, calledShowErrorMethodCount);
        assertFalse(result);
    }
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsSmall_showsWarning() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        boolean result = testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance("7");
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showConfirmationWarning");
        IslandInitializationStateWarningMessagesPresenterMock.resetMethodCallCounts();
        assertEquals(1, calledShowConfirmationWarningMethodCount);    
        assertFalse(result);
    } 
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_dimensionIsValid_IslandManipulationFacadeIsCreated() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        
        boolean result = testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance("8");   
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showNonConfirmationWarning");
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        IslandManipulationFacade islandManipulationFacade = dependencyResolver.getSingletonInstance(IslandManipulationFacade.class);
        
        assertEquals(0, calledShowConfirmationWarningMethodCount);
        assertEquals(0, calledShowErrorMethodCount);
        assertNotNull(islandManipulationFacade);
        assertTrue(result);
    } 
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_userInputIsInvalid_showsError() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        boolean result = testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance("abc");
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showNonConfirmationWarning");
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");

        IslandInitializationStateErrorMessagePresenterMock.resetMethodCallCounts();
        IslandInitializationStateWarningMessagesPresenterMock.resetMethodCallCounts();
        
        assertEquals(0, calledShowConfirmationWarningMethodCount);
        assertEquals(1, calledShowErrorMethodCount);
        assertFalse(result);
    }
    
    @Test
    public void testCreateNewIslandWithRegisteredSingletonFacadeInstance_islandIsTooLarge_showsError() {
        IslandCreationInteractorImpl testCandidate = new IslandCreationInteractorImpl();
        boolean result = testCandidate.createNewIslandWithRegisteredSingletonFacadeInstance("645");
        
        int calledShowConfirmationWarningMethodCount = IslandInitializationStateWarningMessagesPresenterMock.getCallCounterForMethodName("showNonConfirmationWarning");
        int calledShowErrorMethodCount = IslandInitializationStateErrorMessagePresenterMock.getCallCounterForMethodName("showErrorMessage");

        IslandInitializationStateErrorMessagePresenterMock.resetMethodCallCounts();
        IslandInitializationStateWarningMessagesPresenterMock.resetMethodCallCounts();
        
        assertEquals(0, calledShowConfirmationWarningMethodCount);
        assertEquals(1, calledShowErrorMethodCount);
        assertFalse(result);
    }    
}
