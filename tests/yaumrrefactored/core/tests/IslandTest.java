package yaumrrefactored.core.tests;

import yaumrrefactored.core.blocks.AxisRotationValues;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.Island;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IslandTest {
    
    private static Island testCandidate;
  
    @BeforeClass
    public static void setUpClass() {
        testCandidate = new Island(256);
    }
  
    @Test
    public void testPlaceBlock_placeBlockOnValidPosition_blockIsPresent() {
        BlockPosition placementPosition = new BlockPosition(157, 109, 66);
        Assert.assertEquals(testCandidate.getBlockAt(placementPosition).getBlockType(), BlockTypes.AIR);
    
        Block block = new Block(BlockTypes.CASSITERITE);
        testCandidate.placeBlockAt(block, placementPosition);
    
        Assert.assertEquals(testCandidate.getBlockAt(placementPosition), block);
    }
  
    @Test(expected=IllegalArgumentException.class)
    public void testPlaceBlock_placeBlockOnInValidPosition_throwsException() {
        BlockPosition placementPosition = new BlockPosition(355, 70, 14);
        Block block = new Block(BlockTypes.ASH_ROOF);
        testCandidate.placeBlockAt(block, placementPosition);
    }
  
    @Test
    public void testPlaceBlock_positionHasAlreadyBlockPresent_blockIsNotPlaced() {
        BlockPosition placementPosition = new BlockPosition(32, 187, 181);
    
        Block firstBlock = new Block(BlockTypes.GOLD_VEIN);
        Block secondBlock = new Block(BlockTypes.ASH_DOOR);
        testCandidate.placeBlockAt(firstBlock, placementPosition);
        testCandidate.placeBlockAt(secondBlock, placementPosition);
    
        Assert.assertEquals(testCandidate.getBlockAt(placementPosition), firstBlock);
    }
  
    @Test
    public void testPlaceBlock_displacableBlockIsPresent_blockIsDisplaced() {
        BlockPosition position = new BlockPosition(164, 232, 75);
        Block block = new Block(BlockTypes.INLAND_WATER);
        testCandidate.placeBlockAt(block, position);
        block = new Block(BlockTypes.GOLD_VEIN);
    
        testCandidate.placeBlockAt(block, position);
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.GOLD_VEIN);
    }
  
    @Test
    public void testRemoveBlock_positionIsValid_blockIsRemoved() {
        BlockPosition position = new BlockPosition(203, 91, 242);
        Block block = new Block(BlockTypes.EARTH);
        testCandidate.placeBlockAt(block, position);
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.EARTH);
    
        testCandidate.removeBlockAt(position);
    
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.AIR);
    }
  
    @Test(expected=IllegalArgumentException.class)
    public void testRemoveBlock_positionIsInvalid_throwsException() {
        BlockPosition position = new BlockPosition(6, 269, 65);
        testCandidate.removeBlockAt(position);
    }
  
    @Test
    public void testCopyBlockTo_positionIsValid_blockIsCopied() {
        BlockPosition position = new BlockPosition(135, 104, 180);
        Block block = new Block(BlockTypes.OCEAN_WATER);
        testCandidate.placeBlockAt(block, position);
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.OCEAN_WATER);
    
        block = new Block(BlockTypes.BIRCH_STAIRS);
        block.rotateOnAxisX(AxisRotationValues.NINETY);
        block.rotateOnAxisX(AxisRotationValues.NINETY);
        block.rotateOnAxisY(AxisRotationValues.NINETY);
        testCandidate.copyBlockTo(block, position);
    
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.BIRCH_STAIRS);
        Assert.assertEquals(testCandidate.getBlockAt(position).getFrontFace(), block.getFrontFace());
        Assert.assertTrue(testCandidate.getBlockAt(position).topFaceIsCovering());
        Assert.assertTrue(testCandidate.getBlockAt(position).rightFaceIsCovering());
        Assert.assertFalse(testCandidate.getBlockAt(position).leftFaceIsCovering());
        Assert.assertFalse(testCandidate.getBlockAt(position).bottomFaceIsCovering());
        Assert.assertFalse(testCandidate.getBlockAt(position).frontFaceIsCovering());
        Assert.assertFalse(testCandidate.getBlockAt(position).backFaceIsCovering());
    }
  
    @Test
    public void testCopyBlockTo_blockOnPositionIsNotDisplaced_blockIsNotCopied() {
        BlockPosition position = new BlockPosition(219, 246, 84);
        Block block = new Block(BlockTypes.BEDROCK);
        testCandidate.placeBlockAt(block, position);
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.BEDROCK);
    
        block = new Block(BlockTypes.ASH_ROOF);
        testCandidate.copyBlockTo(block, position);
    
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.BEDROCK);
    }
  
    @Test
    public void testCopyBlockTo_copiedBlockDoesNotDisplace_blockIsNotCopied() {
        BlockPosition position = new BlockPosition(147, 106, 156);
        Block block = new Block(BlockTypes.INLAND_WATER);
        testCandidate.placeBlockAt(block, position);
        block = new Block(BlockTypes.ASH_DOOR);
    
        testCandidate.copyBlockTo(block, position);
        Assert.assertEquals(testCandidate.getBlockAt(position).getBlockType(), BlockTypes.INLAND_WATER);
    }
  
    @Test(expected=IllegalArgumentException.class)
    public void testCopyBlockTo_positionIsInvalid_throwsException() {
        BlockPosition position = new BlockPosition(91, 119, 323);
        Block block = new Block(BlockTypes.STRAW_ROOF);
        testCandidate.copyBlockTo(block, position);
    }
}