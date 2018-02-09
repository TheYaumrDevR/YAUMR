package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.EarthBlockTypesDailyUpdateCellularAutomaton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.FallingSandyBlockCellularAutomatonImpl;
import de.ethasia.yaumr.core.GrassToEarthCellularAutomatonImpl;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.IslandManipulationFacadeImpl;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.core.tests.mocks.EarthBlockTypesDailyUpdateCellularAutomatonMock;
import de.ethasia.yaumr.core.tests.mocks.FallingSandyBlockCellularAutomatonMock;
import de.ethasia.yaumr.core.tests.mocks.GrassToEarthCellularAutomatonMock;
import de.ethasia.yaumr.interactors.InteractionVector;
import static org.junit.Assert.assertTrue;

public class IslandManipulationFacadeTest {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Island islandToManipulate;
    
    //</editor-fold>

    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.removeRegisteredImplementation(FallingSandyBlockCellularAutomatonImpl.class);
        dependencyResolver.removeRegisteredImplementation(GrassToEarthCellularAutomatonImpl.class);
        dependencyResolver.removeRegisteredImplementation(EarthBlockTypesDailyUpdateCellularAutomaton.class);
        dependencyResolver.registerImplementation(FallingSandyBlockCellularAutomatonImpl.class, FallingSandyBlockCellularAutomatonMock.class);
        dependencyResolver.registerImplementation(GrassToEarthCellularAutomatonImpl.class, GrassToEarthCellularAutomatonMock.class);
        dependencyResolver.registerImplementation(EarthBlockTypesDailyUpdateCellularAutomaton.class, EarthBlockTypesDailyUpdateCellularAutomatonMock.class);
        
        islandToManipulate = new Island(256);
    }
  
    @Test
    public void testPlaceBlockAt_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(91, 119, 223);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);
        boolean blockPlaced = testCandidate.placeBlockAt(blockToPlace, position);
        
        assertEquals(BlockTypes.BEDROCK, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertTrue(blockPlaced);
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();
    }
  
    @Test
    public void testRemoveBlockAt_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(152, 9, 169);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_PLANKS);
        testCandidate.placeBlockAt(blockToPlace, position);
        boolean blockRemoved = testCandidate.removeBlockAt(position);
        
        assertEquals(BlockTypes.AIR, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(2, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(2, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(2, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertTrue(blockRemoved);
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts(); 
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();
    }
  
    @Test
    public void testCopyBlockTo_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(167, 122, 179);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_ROOF);
        boolean blockCopied = testCandidate.copyBlockTo(blockToPlace, position);
        
        assertEquals(BlockTypes.BIRCH_ROOF, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));        
        assertTrue(blockCopied);
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();    
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();
    }
  
    @Test
    public void testTick_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        testCandidate.tick(1000);
        
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(0, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("tick"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();     
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();
    }
    
    @Test
    public void testGetBlockPositionOnCurrentIslandForInteractionVector_facadeIsSetup_validPositionIsReturned() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        InteractionVector interactionVector = new InteractionVector(5.6f, 3.2f, 7.9f);
        
        BlockPosition result = testCandidate.getBlockPositionOnCurrentIslandForInteractionVector(interactionVector);
        
        assertEquals(11, result.x);
        assertEquals(6, result.y);
        assertEquals(15, result.z);
    }
    
    @Test
    public void testGetBlockPositionOnCurrentIslandForInteractionVector_noIslandPresent_noPositionIsReturned() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        InteractionVector interactionVector = new InteractionVector(5.6f, 3.2f, 7.9f);
        
        BlockPosition result = testCandidate.getBlockPositionOnCurrentIslandForInteractionVector(interactionVector);
        
        assertNull(result);
    }   
    
    @Test
    public void testPerformDailyUpdates_islandIsPresent_tickIsCalledOnDailyUpdaters() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);

        testCandidate.performDailyUpdates();
        
        assertEquals(0, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(0, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(1, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("tick"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();     
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();        
    }
    
    @Test
    public void testPerformDailyUpdates_noIslandIsPresent_noTickIsCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();

        testCandidate.performDailyUpdates();
        
        assertEquals(0, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(0, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(0, EarthBlockTypesDailyUpdateCellularAutomatonMock.getCallCounterForMethodName("tick"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();     
        EarthBlockTypesDailyUpdateCellularAutomatonMock.resetMethodCallCounts();          
    }
}
