package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.FallingSandyBlockCellularAutomatonImpl;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FallingSandyBlockCellularAutomatonTest {
    
    private static Island updatedIsland;
    private static IslandManipulationFacadeMock islandManipulationFacade;
  
    public FallingSandyBlockCellularAutomatonTest() {}
  
    @BeforeClass
    public static void setUpClass() {
        updatedIsland = new Island(256);
        islandManipulationFacade = new IslandManipulationFacadeMock();
        islandManipulationFacade.setNewlyCreatedIsland(updatedIsland);
    }
  
    @Test
    public void testTick_sandBlockIsPlaced_fallsBelow() {
        FallingSandyBlockCellularAutomatonImpl testCandidate = new FallingSandyBlockCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setFallingBlockHandler(testCandidate);
    
        BlockPosition fallenOnPosition = new BlockPosition(103, 214, 139);
        BlockPosition abovePosition = fallenOnPosition.getPositionOneAbove();
        BlockPosition placementPosition = new BlockPosition(103, 217, 139);
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);
    
        islandManipulationFacade.placeBlockAt(belowBlock, fallenOnPosition);
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(placementPosition).getBlockType(), BlockTypes.AIR);
        Assert.assertEquals(updatedIsland.getBlockAt(abovePosition).getBlockType(), BlockTypes.SAND);
        Assert.assertEquals(updatedIsland.getBlockAt(fallenOnPosition).getBlockType(), BlockTypes.CLAY);
    }
  
    @Test
    public void testTick_sandBlockIsPlaced_displacesWater() {
        FallingSandyBlockCellularAutomatonImpl testCandidate = new FallingSandyBlockCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setFallingBlockHandler(testCandidate);
    
        BlockPosition fallenOnPosition = new BlockPosition(0, 170, 60);
        BlockPosition abovePosition = fallenOnPosition.getPositionOneAbove();
        BlockPosition placementPosition = new BlockPosition(0, 174, 60);
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);
        Block waterBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.INLAND_WATER);
    
        islandManipulationFacade.placeBlockAt(belowBlock, fallenOnPosition);
        islandManipulationFacade.placeBlockAt(waterBlock, abovePosition);
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(placementPosition).getBlockType(), BlockTypes.AIR);
        Assert.assertEquals(updatedIsland.getBlockAt(abovePosition).getBlockType(), BlockTypes.SAND);
    }
  
    @Test
    public void testTick_blockBelowSandBlockIsRemoved_sandBlockFallsDown() {
        FallingSandyBlockCellularAutomatonImpl testCandidate = new FallingSandyBlockCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setFallingBlockHandler(testCandidate);
    
        BlockPosition fallenOnPosition = new BlockPosition(40, 96, 186);
        BlockPosition abovePosition = fallenOnPosition.getPositionOneAbove();
        BlockPosition sandBlockOnePosition = new BlockPosition(40, 98, 186);
        BlockPosition sandBlockTwoPosition = new BlockPosition(40, 99, 186);
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.IRON_ORE);
        Block removedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);
    
        islandManipulationFacade.placeBlockAt(belowBlock, fallenOnPosition);
        islandManipulationFacade.placeBlockAt(removedBlock, abovePosition);
        islandManipulationFacade.placeBlockAt(placedBlock, sandBlockOnePosition);
        islandManipulationFacade.copyBlockTo(placedBlock, sandBlockTwoPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(abovePosition).getBlockType(), BlockTypes.EARTH);
    
        islandManipulationFacade.removeBlockAt(abovePosition);
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(abovePosition).getBlockType(), BlockTypes.SAND);
        Assert.assertEquals(updatedIsland.getBlockAt(sandBlockOnePosition).getBlockType(), BlockTypes.SAND);
        Assert.assertEquals(updatedIsland.getBlockAt(sandBlockTwoPosition).getBlockType(), BlockTypes.AIR);
    }
}