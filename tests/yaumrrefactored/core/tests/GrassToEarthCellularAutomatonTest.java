package yaumrrefactored.core.tests;

import yaumrrefactored.core.AxisRotationValues;
import yaumrrefactored.core.Block;
import yaumrrefactored.core.BlockPosition;
import yaumrrefactored.core.BlockTypes;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;
import yaumrrefactored.core.Island;
import yaumrrefactored.core.tests.mock.IslandManipulationFacadeMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GrassToEarthCellularAutomatonTest {
    
    private static Island updatedIsland;
    private static IslandManipulationFacadeMock islandManipulationFacade;
  
    @BeforeClass
    public static void setUpClass() {
        updatedIsland = new Island(256);
        islandManipulationFacade = new IslandManipulationFacadeMock();
        islandManipulationFacade.setIsland(updatedIsland);
    }
  
    @Test
    public void testTick_grassBlocksArePlacedBelowCoveringBlocks_grassBlocksAreUpdatedCorrectly() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomGrassPosition = new BlockPosition(91, 119, 223);
        BlockPosition aboveBlockPosition = bottomGrassPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
    
        Block belowGrassBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block rockBlock = new Block(BlockTypes.ROCK);
    
        islandManipulationFacade.placeBlockAt(rockBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowGrassBlock, bottomGrassPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomGrassPosition).getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH);
    }
  
    @Test
    public void testTick_plowedEarthBlocksArePlacedBelowCoveringBlocks_blocksAreUpdatedCorrectly() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomPosition = new BlockPosition(152, 9, 169);
        BlockPosition aboveBlockPosition = bottomPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
    
        Block belowBlock = new Block(BlockTypes.EARTH_PLOWED);
        Block placedGrassBlock = new Block(BlockTypes.EARTH_PLOWED);
        Block topBlock = new Block(BlockTypes.ROCK);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH);
    }
  
    @Test
    public void testTick_wateredPlowedBlocksArePlacedBelowCoveringBlocks_blocksAreUpdatedCorrectly() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomPosition = new BlockPosition(167, 122, 179);
        BlockPosition aboveBlockPosition = bottomPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
    
        Block belowBlock = new Block(BlockTypes.EARTH_PLOWED_WATERED);
        Block placedGrassBlock = new Block(BlockTypes.EARTH_PLOWED_WATERED);
        Block topBlock = new Block(BlockTypes.ROCK);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH_WATERED);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH_WATERED);
    }
  
    @Test
    public void testTick_seededEarthBlocksAreBelowCoveringBlocks_blocksAreNotUpdated() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomPosition = new BlockPosition(225, 174, 110);
        BlockPosition aboveBlockPosition = bottomPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
        BlockPosition belowBottom = bottomPosition.getPositionOneBelow();
    
        Block belowBlock = new Block(BlockTypes.EARTH_SEEDED);
        Block placedGrassBlock = new Block(BlockTypes.EART_SEEDED_WATERED);
        Block topBlock = new Block(BlockTypes.ROCK);
        Block belowBottomBlock = new Block(BlockTypes.EARTH_WATERED);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBottomBlock, belowBottom);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH_SEEDED);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EART_SEEDED_WATERED);
        Assert.assertEquals(updatedIsland.getBlockAt(belowBottom).getBlockType(), BlockTypes.EARTH_WATERED);
    }
  
    @Test
    public void testTick_rotatedStairBlockIsPlacedAboveGrassBlock_grassBlockStateIsUpdatedCorrectly() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomPosition = new BlockPosition(35, 168, 127);
        BlockPosition aboveBlockPosition = bottomPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
    
        Block belowBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block topBlock = new Block(BlockTypes.ASH_STAIRS);
        topBlock.rotateOnAxisX(AxisRotationValues.NINETY);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.GRASSY_EARTH);
    }
  
    @Test
    public void testTick_stairBlockIsPlacedAboveGrassBlock_grassBlockStateIsUpdatedCorrectly() {
        GrassToEarthCellularAutomatonImpl testCandidate = new GrassToEarthCellularAutomatonImpl();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setGrassToEarthCA(testCandidate);
    
        BlockPosition bottomPosition = new BlockPosition(181, 200, 41);
        BlockPosition aboveBlockPosition = bottomPosition.getPositionOneAbove();
        BlockPosition twoAboveBlockPosition = aboveBlockPosition.getPositionOneAbove();
    
        Block belowBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = new Block(BlockTypes.GRASSY_EARTH);
        Block topBlock = new Block(BlockTypes.BIRCH_STAIRS);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH);
    }
}