package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.blocks.AxisRotationValues;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.GrassToEarthCellularAutomatonImpl;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;

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
    
        Block belowGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block rockBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
    
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
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED);
        Block topBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
    
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
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED_WATERED);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED_WATERED);
        Block topBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
    
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
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_SEEDED);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_SEEDED_WATERED);
        Block topBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        Block belowBottomBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_WATERED);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBottomBlock, belowBottom);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH_SEEDED);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH_SEEDED_WATERED);
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
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block topBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_STAIRS);
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
    
        Block belowBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block placedGrassBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block topBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_STAIRS);
    
        islandManipulationFacade.placeBlockAt(topBlock, twoAboveBlockPosition);
        islandManipulationFacade.placeBlockAt(belowBlock, bottomPosition);
        islandManipulationFacade.placeBlockAt(placedGrassBlock, aboveBlockPosition);
    
        testCandidate.tick(1000L);
    
        Assert.assertEquals(updatedIsland.getBlockAt(bottomPosition).getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(updatedIsland.getBlockAt(aboveBlockPosition).getBlockType(), BlockTypes.EARTH);
    }
}