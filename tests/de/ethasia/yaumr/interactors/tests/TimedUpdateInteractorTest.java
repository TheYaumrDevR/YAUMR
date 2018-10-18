package de.ethasia.yaumr.interactors.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import de.ethasia.yaumr.interactors.TimedUpdateInteractorImpl;
import de.ethasia.yaumr.interactors.interfaces.TimedUpdateInteractor;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;

public class TimedUpdateInteractorTest {
    
    @BeforeClass
    public static void setupClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        dependencyResolver.removeSingletonInstance(IslandManipulationFacade.class);
        dependencyResolver.registerSingletonInstance(IslandManipulationFacade.class, new IslandManipulationFacadeMock());
    } 
    
    @Before
    public void setUpTest() {
        IslandManipulationFacadeMock.resetMethodCallCounts();
    }
    
    @Test
    public void testTick_ticksAreLowerThanMilliseconds_islandIsUpdatedAfterOneMilliSecondIsReached() {
        TimedUpdateInteractor testCandidate = new TimedUpdateInteractorImpl();
        
        for (int i = 0; i < 1000; i++) {
            testCandidate.tick(0.000001f);
        }
        
        assertEquals(1, IslandManipulationFacadeMock.getCallCounterForMethodName("tick"));
    }
    
    @Test
    public void testTick_ticksAreLowerThanMilliseconds_tickIsNotCalledIfMillisecondNotReached() {
        TimedUpdateInteractor testCandidate = new TimedUpdateInteractorImpl();
        
        for (int i = 0; i < 500; i++) {
            testCandidate.tick(0.000001f);
        }
        
        assertEquals(0, IslandManipulationFacadeMock.getCallCounterForMethodName("tick"));
    } 
    
    @Test
    public void testTick_ticksAreHigherThanMilliseconds_islandIsUpdatedInEachIteration() {
        TimedUpdateInteractor testCandidate = new TimedUpdateInteractorImpl();
        
        for (int i = 0; i < 4; i++) {
            testCandidate.tick(0.002f);
        }
        
        assertEquals(4, IslandManipulationFacadeMock.getCallCounterForMethodName("tick"));
    }
}