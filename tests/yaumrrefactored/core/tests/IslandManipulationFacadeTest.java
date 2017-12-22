package yaumrrefactored.core.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.FallingSandyBlockCellularAutomatonImpl;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;
import yaumrrefactored.core.Island;
import yaumrrefactored.core.IslandManipulationFacadeImpl;
import yaumrrefactored.core.blocks.SimpleBlockFactory;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;
import yaumrrefactored.core.tests.mocks.FallingSandyBlockCellularAutomatonMock;
import yaumrrefactored.core.tests.mocks.GrassToEarthCellularAutomatonMock;
import yaumrrefactored.interactors.InteractionVector;

public class IslandManipulationFacadeTest {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Island islandToManipulate;
    
    //</editor-fold>

    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.registerImplementation(FallingSandyBlockCellularAutomatonImpl.class, FallingSandyBlockCellularAutomatonMock.class);
        dependencyResolver.registerImplementation(GrassToEarthCellularAutomatonImpl.class, GrassToEarthCellularAutomatonMock.class);
        
        islandToManipulate = new Island(256);
    }
  
    @Test
    public void testPlaceBlockAt_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(91, 119, 223);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);
        testCandidate.placeBlockAt(blockToPlace, position);
        
        assertEquals(BlockTypes.BEDROCK, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();
    }
  
    @Test
    public void testRemoveBlockAt_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(152, 9, 169);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_PLANKS);
        testCandidate.placeBlockAt(blockToPlace, position);
        testCandidate.removeBlockAt(position);
        
        assertEquals(BlockTypes.AIR, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(2, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(2, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();    
    }
  
    @Test
    public void testCopyBlockTo_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        BlockPosition position = new BlockPosition(167, 122, 179);
        Block blockToPlace = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_ROOF);
        testCandidate.copyBlockTo(blockToPlace, position);
        
        assertEquals(BlockTypes.BIRCH_ROOF, islandToManipulate.getBlockAt(position).getBlockType());
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("setChangedPosition"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();    
    }
  
    @Test
    public void testTick_allSubInterfacesAreCalled() {
        IslandManipulationFacade testCandidate = new IslandManipulationFacadeImpl();
        testCandidate.setIsland(islandToManipulate);
        
        testCandidate.tick(1000);
        
        assertEquals(1, FallingSandyBlockCellularAutomatonMock.getCallCounterForMethodName("tick"));
        assertEquals(1, GrassToEarthCellularAutomatonMock.getCallCounterForMethodName("tick"));
        
        FallingSandyBlockCellularAutomatonMock.resetMethodCallCounts();
        GrassToEarthCellularAutomatonMock.resetMethodCallCounts();      
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
}
