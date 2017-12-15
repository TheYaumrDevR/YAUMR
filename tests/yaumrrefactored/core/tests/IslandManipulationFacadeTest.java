package yaumrrefactored.core.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.FallingSandyBlockCellularAutomatonImpl;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;
import yaumrrefactored.core.Island;
import yaumrrefactored.core.IslandManipulationFacadeImpl;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;
import yaumrrefactored.core.tests.mock.FallingSandyBlockCellularAutomatonMock;
import yaumrrefactored.core.tests.mock.GrassToEarthCellularAutomatonMock;

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
        Block blockToPlace = new Block(BlockTypes.BEDROCK);
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
        Block blockToPlace = new Block(BlockTypes.BIRCH_PLANKS);
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
        Block blockToPlace = new Block(BlockTypes.BIRCH_ROOF);
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
}
