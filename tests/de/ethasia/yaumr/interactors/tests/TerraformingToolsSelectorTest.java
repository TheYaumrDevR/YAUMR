package de.ethasia.yaumr.interactors.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import org.junit.Test;
import static org.junit.Assert.*;
import de.ethasia.yaumr.core.BlockPlacementTerraformingTool;
import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import de.ethasia.yaumr.core.tests.mocks.TerraformingToolMock;
import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.interactors.TerraformingToolsSelectorImpl;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.interactors.tests.mocks.TerraformingToolsQuickbarPresenterMock;
import org.junit.BeforeClass;

/**
 *
 * @author R
 */
public class TerraformingToolsSelectorTest {
    
    @BeforeClass
    public static void setupClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        dependencyResolver.removeRegisteredImplementation(TerraformingToolsQuickbarPresenter.class);
        dependencyResolver.registerImplementation(TerraformingToolsQuickbarPresenter.class, TerraformingToolsQuickbarPresenterMock.class);
    }
    
    @Test
    public void testGotoPreviousPage_previousPageIsPresent_pageSwitchesToPreviousPage() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(3, 4);
        testCandidate.setToolToOtherPosition(new BlockPlacementTerraformingTool(null), 8);
        testCandidate.setToolToOtherPosition(new BlockPlacementTerraformingTool(null), 2);
        
        testCandidate.gotoNextPage();
        testCandidate.gotoNextPage();
        
        assertNotNull(testCandidate.getOtherItemsOnCurrentPage()[0]);
        assertNull(testCandidate.getOtherItemsOnCurrentPage()[1]);
        
        testCandidate.gotoPreviousPage();
        assertNull(testCandidate.getOtherItemsOnCurrentPage()[0]);
        
        testCandidate.gotoPreviousPage();
        assertNotNull(testCandidate.getOtherItemsOnCurrentPage()[2]);
        assertNull(testCandidate.getOtherItemsOnCurrentPage()[3]);
    }
    
    @Test
    public void testGotoPreviousPage_previousPageIsNotPresent_staysOnCurrentPage() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(3, 4);
        testCandidate.setToolToOtherPosition(new BlockPlacementTerraformingTool(null), 2);
        
        testCandidate.gotoPreviousPage();
        
        assertNotNull(testCandidate.getOtherItemsOnCurrentPage()[2]);
        assertNull(testCandidate.getOtherItemsOnCurrentPage()[3]);
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetSelectedToolIndex_indexIsInvalid_throwsException() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(1, 4);
        
        testCandidate.setSelectedToolIndex(14);
    }
    
    @Test
    public void testSetSelectedToolIndex_indexIsValid_highLightingOnUIIsCalled() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(1, 16);    
        
        testCandidate.setSelectedToolIndex(5);
        
        assertEquals(1, TerraformingToolsQuickbarPresenterMock.getCallCounterForMethodName("highlightQuickbarPosition"));
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetToolToOtherPosition_positionIsInvalid_throwsException() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 40);
        
        testCandidate.setToolToOtherPosition(null, 200);
    }
    
    @Test
    public void testSetToolToOtherPosition_positionIsValid_toolIsPresent() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToOtherPosition(tool, 0);
        
        assertEquals(tool, testCandidate.getOtherItemsOnCurrentPage()[0]);
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSwapToolsOnSelectionPositions_positionIsInvalid_throwsException() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool1 = new BlockPlacementTerraformingTool(null);
        TerraformingTool tool2 = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool1, 0);
        testCandidate.setToolToSelectionPosition(tool2, 1);
        testCandidate.swapToolsOnSelectionPositions(0, 12);
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test
    public void testSwapToolsOnSelectionPositions_positionsAreValid_toolsAreSwapped() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool1 = new BlockPlacementTerraformingTool(null);
        TerraformingTool tool2 = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool1, 4);
        testCandidate.setToolToSelectionPosition(tool2, 7);
        testCandidate.swapToolsOnSelectionPositions(4, 7);
        
        assertEquals(tool1, testCandidate.getSelectableTools()[7]);
        assertEquals(tool2, testCandidate.getSelectableTools()[4]);
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test
    public void testSetToolToSelectionPosition_positionIsValid_toolIsSet() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool, 8);
        
        assertEquals(tool, testCandidate.getSelectableTools()[8]);
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetToolToSelectionPosition_positionIsInvalid_throwsException() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool, 12);
    }    
    
    @Test
    public void testGetOtherItemsOnCurrentPage_previouslySetItemsAreRetrieved() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToOtherPosition(tool, 3);
        
        assertEquals(tool, testCandidate.getOtherItemsOnCurrentPage()[3]);
    }
    
    @Test
    public void testExecuteActionOfCurrentlySelectedTool_toolIsSelected_actionIsExecuted() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new TerraformingToolMock();
        testCandidate.setToolToSelectionPosition(tool, 5);
        testCandidate.setSelectedToolIndex(5);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        
        testCandidate.executeActionOfCurrentlySelectedTool(new InteractionVector(0.4f, 0.4f, 0.4f));
        
        assertEquals(1, TerraformingToolMock.getMethodCallCount("interactWithIsland"));
        TerraformingToolMock.resetCalledMethodCounters();
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test
    public void testExecuteActionOfCurrentlySelectedTool_noToolSelected_actionIsNotExecuted() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        testCandidate.setSelectedToolIndex(5);
        
        testCandidate.executeActionOfCurrentlySelectedTool(new InteractionVector(0.4f, 0.4f, 0.4f));
        
        assertEquals(0, TerraformingToolMock.getMethodCallCount("interactWithIsland"));
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }    
    
    @Test
    public void testRotateCurrentlySelectedToolOnX_toolIsSelected_rotationMethodIsCalled() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        TerraformingTool tool = new TerraformingToolMock();
        testCandidate.setToolToSelectionPosition(tool, 5);
        testCandidate.setSelectedToolIndex(5);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        
        testCandidate.rotateCurrentlySelectedToolOnX();
        
        assertEquals(1, TerraformingToolMock.getMethodCallCount("rotateOnX"));
        TerraformingToolMock.resetCalledMethodCounters();
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }
    
    @Test
    public void testRotateCurrentlySelectedToolOnX_noToolIsSelected_rotationMethodIsNotCalled() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        testCandidate.setSelectedToolIndex(5);
        
        testCandidate.rotateCurrentlySelectedToolOnX();
        
        assertEquals(0, TerraformingToolMock.getMethodCallCount("rotateOnX"));
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }    
    
    @Test
    public void testRotateCurrentlySelectedToolOnY_toolIsSelected_rotationMethodIsCalled() {     
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        TerraformingTool tool = new TerraformingToolMock();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setToolToSelectionPosition(tool, 5);
        testCandidate.setSelectedToolIndex(5);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        
        testCandidate.rotateCurrentlySelectedToolOnY();
        
        assertEquals(1, TerraformingToolMock.getMethodCallCount("rotateOnY"));
        TerraformingToolMock.resetCalledMethodCounters();        
    }
    
    @Test
    public void testRotateCurrentlySelectedToolOnY_noToolIsSelected_rotationMethodIsNotCalled() {  
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        testCandidate.setSelectedToolIndex(5);
        
        testCandidate.rotateCurrentlySelectedToolOnY();
        
        assertEquals(0, TerraformingToolMock.getMethodCallCount("rotateOnY"));        
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }    
    
    @Test
    public void testRotateCurrentlySelectedToolOnZ_toolIsSelected_rotationMethodIsCalled() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        TerraformingTool tool = new TerraformingToolMock();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setToolToSelectionPosition(tool, 5);
        testCandidate.setSelectedToolIndex(5);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        
        testCandidate.rotateCurrentlySelectedToolOnZ();
        
        assertEquals(1, TerraformingToolMock.getMethodCallCount("rotateOnZ"));
        TerraformingToolMock.resetCalledMethodCounters();         
    }
    
    @Test
    public void testRotateCurrentlySelectedToolOnZ_toolIsNotSelected_rotationMethodIsNotCalled() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(5, 4);
        testCandidate.setIslandManipulationFacade(new IslandManipulationFacadeMock());
        testCandidate.setSelectedToolIndex(5);
        
        testCandidate.rotateCurrentlySelectedToolOnZ();
        
        assertEquals(0, TerraformingToolMock.getMethodCallCount("rotateOnZ")); 
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();
    }   
    
    @Test
    public void testSwapToolsOnSelectionPositions_positionsAreValid_quickBarInGuiIsChanged() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(1, 16);    
        TerraformingTool tool1 = new BlockPlacementTerraformingTool(null);
        TerraformingTool tool2 = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool1, 3);
        testCandidate.setToolToSelectionPosition(tool2, 0);
        testCandidate.swapToolsOnSelectionPositions(3, 0);
        
        assertEquals(3, TerraformingToolsQuickbarPresenterMock.getCallCounterForMethodName("showItemsOnQuickbar"));
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();        
    }
    
    @Test
    public void testSetToolToSelectionPosition_positionIsValid_quickBarInGuiIsChanged() {
        TerraformingToolsSelectorImpl testCandidate = new TerraformingToolsSelectorImpl();
        testCandidate.resetToDimensions(1, 16);    
        TerraformingTool tool1 = new BlockPlacementTerraformingTool(null);
        
        testCandidate.setToolToSelectionPosition(tool1, 4);
        
        assertEquals(1, TerraformingToolsQuickbarPresenterMock.getCallCounterForMethodName("showItemsOnQuickbar"));
        TerraformingToolsQuickbarPresenterMock.resetMethodCallCounts();          
    }    
}
