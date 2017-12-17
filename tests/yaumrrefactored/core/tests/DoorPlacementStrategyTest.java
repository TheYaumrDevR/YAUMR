package yaumrrefactored.core.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yaumrrefactored.core.DoorPlacementStrategy;
import yaumrrefactored.core.Island;
import yaumrrefactored.core.blocks.AxisRotationValues;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.blocks.DoorBlock;
import yaumrrefactored.core.blocks.SimpleBlockFactory;

/**
 *
 * @author R
 */
public class DoorPlacementStrategyTest {
    
    private static Island islandToWorkOn;
    
    @BeforeClass
    public static void setUpClass() {
        // We do not need large islands for unit tests.
        islandToWorkOn = new Island(32);
    }

    @Test
    public void testPlaceBlockAt_positionIsValid_lowerAndUpperDoorBlockArePlaced() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_DOOR);
        BlockPosition placementPosition = new BlockPosition(12, 136, 3);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());
        assertEquals(BlockTypes.ASH_DOOR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.ASH_DOOR, upperDoorBlock.getBlockType());
    }
    
    @Test
    public void testPlaceBlockAt_upperDoorPartIsObstructed_lowerAndUpperDoorBlockAreNotPlaced() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.WALNUT_DOOR);
        Block obstructingBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        BlockPosition placementPosition = new BlockPosition(23, 144, 7);
        islandToWorkOn.placeBlockAt(obstructingBlock, placementPosition);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition.getPositionOneBelow());
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneBelow());
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.CLAY, upperDoorBlock.getBlockType());        
    }
    
    @Test
    public void testPlaceBlockAt_doorIsObstructedByDisplacables_lowerAndUpperDoorBlockAreNotPlaced() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_DOOR);
        Block obstructingBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.INLAND_WATER);
        BlockPosition placementPosition = new BlockPosition(31, 15, 16);
        islandToWorkOn.placeBlockAt(obstructingBlock, placementPosition);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());
        assertEquals(BlockTypes.INLAND_WATER, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.AIR, upperDoorBlock.getBlockType());         
    }
    
    @Test
    public void testPlaceBlockAt_doorIsPlacedAtUpperMapLimit_doorIsNotPlaced() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
        BlockPosition placementPosition = new BlockPosition(5, 255, 10);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());
    }
    
    @Test
    public void testRemoveBlock_lowerPartIsRemoved_upperPartIsRemovedAsWell() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_DOOR);
        BlockPosition placementPosition = new BlockPosition(23, 11, 15);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition);
        
        islandToWorkOn.getBlockAt(placementPosition).getBlockPlacementStrategy().removeBlockFromIslandAt(islandToWorkOn, placementPosition);
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());        
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.AIR, upperDoorBlock.getBlockType());        
    }
    
    @Test
    public void testRemoveBlock_upperPartIsRemoved_lowerPartIsRemovedAsWell() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_DOOR);
        BlockPosition placementPosition = new BlockPosition(17, 17, 17);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.placeBlockOnIslandAt(islandToWorkOn, placementPosition);
        
        islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove()).getBlockPlacementStrategy().removeBlockFromIslandAt(islandToWorkOn, placementPosition.getPositionOneAbove());
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());        
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.AIR, upperDoorBlock.getBlockType());         
    }    
    
    @Test
    public void testCopyBlockTo_positionIsValid_lowerAndUpperDoorBlockAreCopied() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
        blockToPlace.rotateOnAxisX(AxisRotationValues.NINETY);
        blockToPlace.setIsClosed(false);
        BlockPosition placementPosition = new BlockPosition(0, 237, 30);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.copyBlockToPositionOnIsland(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());
        assertEquals(BlockTypes.OAK_DOOR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.OAK_DOOR, upperDoorBlock.getBlockType()); 
        assertEquals(lowerDoorBlock.getFrontFace(), blockToPlace.getFrontFace());
        assertEquals(lowerDoorBlock.getRightFace(), blockToPlace.getRightFace());
        assertEquals(lowerDoorBlock.getLeftFace(), blockToPlace.getLeftFace());
        assertEquals(lowerDoorBlock.getBackFace(), blockToPlace.getBackFace());
        assertFalse(((DoorBlock)lowerDoorBlock).isClosed());
        assertEquals(upperDoorBlock.getFrontFace(), blockToPlace.getFrontFace());
        assertEquals(upperDoorBlock.getRightFace(), blockToPlace.getRightFace());
        assertEquals(upperDoorBlock.getLeftFace(), blockToPlace.getLeftFace());
        assertEquals(upperDoorBlock.getBackFace(), blockToPlace.getBackFace());
        assertFalse(((DoorBlock)upperDoorBlock).isClosed());
    }
    
    @Test
    public void testCopyBlockTo_upperPartIsObstructed_blockIsNotCopied() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_DOOR);
        Block obstructingBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.LEAVES);
        BlockPosition placementPosition = new BlockPosition(29, 73, 1);
        islandToWorkOn.placeBlockAt(obstructingBlock, placementPosition);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.copyBlockToPositionOnIsland(islandToWorkOn, placementPosition.getPositionOneBelow());
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneBelow());
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.LEAVES, upperDoorBlock.getBlockType());          
    }
    
    @Test
    public void testCopyBlockTo_doorIsObsturctedByWater_blockIsNotCopied() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_DOOR);
        Block obstructingBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OCEAN_WATER);
        BlockPosition placementPosition = new BlockPosition(20, 107, 12);
        islandToWorkOn.placeBlockAt(obstructingBlock, placementPosition);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.copyBlockToPositionOnIsland(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        Block upperDoorBlock = islandToWorkOn.getBlockAt(placementPosition.getPositionOneAbove());
        assertEquals(BlockTypes.OCEAN_WATER, lowerDoorBlock.getBlockType());
        assertEquals(BlockTypes.AIR, upperDoorBlock.getBlockType());         
    }
    
    @Test
    public void testCopyBlockTo_doorIsCopiedAtUpperMapLimit_doorIsNotCopied() {
        DoorBlock blockToPlace = (DoorBlock)SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
        BlockPosition placementPosition = new BlockPosition(23, 255, 2);
        DoorPlacementStrategy testCandidate = new DoorPlacementStrategy();
        testCandidate.setBlockToPlace(blockToPlace);
        
        testCandidate.copyBlockToPositionOnIsland(islandToWorkOn, placementPosition);
        
        Block lowerDoorBlock = islandToWorkOn.getBlockAt(placementPosition);
        assertEquals(BlockTypes.AIR, lowerDoorBlock.getBlockType());        
    }    
}
