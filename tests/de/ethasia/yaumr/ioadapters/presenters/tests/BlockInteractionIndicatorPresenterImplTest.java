/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ethasia.yaumr.ioadapters.presenters.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import de.ethasia.yaumr.ioadapters.interfaces.BlockInteractionIndicatorPresenter;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.ioadapters.presenters.BlockInteractionIndicatorPresenterImpl;
import de.ethasia.yaumr.outsidedependencies.tests.mocks.IslandEditorStateMock;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author R
 */
public class BlockInteractionIndicatorPresenterImplTest {
    
    public BlockInteractionIndicatorPresenterImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        dependencyResolver.removeSingletonInstance(IslandManipulationFacade.class);
        dependencyResolver.removeSingletonInstance(IslandEditorState.class);  
        dependencyResolver.registerSingletonInstance(IslandManipulationFacade.class, new IslandManipulationFacadeMock());
        dependencyResolver.registerSingletonInstance(IslandEditorState.class, new IslandEditorStateMock());
    }

    @Test
    public void testPresentPointingIndicator_pointsOnValidBlockPosition_allMethodsAreCalled() {
        IslandEditorStateMock.resetMethodCallCounts();
        BlockInteractionIndicatorPresenter testCandidate = new BlockInteractionIndicatorPresenterImpl();

        testCandidate.presentPointingIndicator(1.2f, 3.f, 4.1f);
        
        int actualDelegateMethodCallCount = IslandEditorStateMock.getCallCounterForMethodName("displayBlockPointingIndicator");
        int removeInteractionIndicatorCallCount = IslandEditorStateMock.getCallCounterForMethodName("removeBlockPointingIndicator");
        assertEquals(1, actualDelegateMethodCallCount);
        assertEquals(0, removeInteractionIndicatorCallCount);
    }
    
    @Test
    public void testPresentPointingIndicator_pointsOnValidBlockPoitionThenOnInvalidPosition_removeMethodIsCalled() {
        IslandEditorStateMock.resetMethodCallCounts();
        BlockInteractionIndicatorPresenter testCandidate = new BlockInteractionIndicatorPresenterImpl();

        testCandidate.presentPointingIndicator(1.2f, 3.f, 4.1f);
        testCandidate.presentPointingIndicator(0, -0.1f, 0);
                
        int actualDelegateMethodCallCount = IslandEditorStateMock.getCallCounterForMethodName("removeBlockPointingIndicator");
        assertEquals(1, actualDelegateMethodCallCount);    
    }
    
    @Test
    public void testPresentPointingIndicator_pointsOnSamePositionTwice_methodsAreOnlyCalledOnce() {
        IslandEditorStateMock.resetMethodCallCounts();
        BlockInteractionIndicatorPresenter testCandidate = new BlockInteractionIndicatorPresenterImpl();

        testCandidate.presentPointingIndicator(1.2f, 3.f, 4.1f);
        testCandidate.presentPointingIndicator(1.2f, 3.f, 4.1f);
                
        int actualDelegateMethodCallCount = IslandEditorStateMock.getCallCounterForMethodName("displayBlockPointingIndicator");
        assertEquals(1, actualDelegateMethodCallCount);       
    }

    @Test
    public void testPresentPointingIndicator_islandEditorStateIsNotPresent_throwsNoExceptions() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        IslandEditorState registeredIslandEditorState = dependencyResolver.getSingletonInstance(IslandEditorState.class);
        dependencyResolver.removeSingletonInstance(IslandEditorState.class);
        
        IslandEditorStateMock.resetMethodCallCounts();
        BlockInteractionIndicatorPresenter testCandidate = new BlockInteractionIndicatorPresenterImpl();

        testCandidate.presentPointingIndicator(1.2f, 3.f, 4.1f);
        
        int actualDelegateMethodCallCount = IslandEditorStateMock.getCallCounterForMethodName("displayBlockPointingIndicator");
        int removeInteractionIndicatorCallCount = IslandEditorStateMock.getCallCounterForMethodName("removeBlockPointingIndicator");
        assertEquals(0, actualDelegateMethodCallCount);
        assertEquals(0, removeInteractionIndicatorCallCount);
        
        dependencyResolver.registerSingletonInstance(IslandEditorState.class, registeredIslandEditorState);
    }        
}
