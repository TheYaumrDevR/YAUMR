package yaumrrefactored.core.blocks.tests;

import yaumrrefactored.core.blocks.AxisRotationValues;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockFaceTypes;
import yaumrrefactored.core.blocks.BlockTypes;
import org.junit.Assert;
import org.junit.Test;
import yaumrrefactored.core.blocks.SimpleBlockFactory;

public class BlockTest
{
    public BlockTest() {}
  
    @Test
    public void testDefaultConstructor_initializesVariablesCorrectly() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
        Assert.assertEquals(objectToTest.getBlockType(), BlockTypes.AIR);
    }
  
    @Test
    public void testBlockTypeConstructor_initializesVariablesCorrectly() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_TRUNK);
        Assert.assertEquals(objectToTest.getBlockType(), BlockTypes.OAK_TRUNK);
    }
  
    @Test
    public void testResetBlock_allPropertiesAreResetCorrectly() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);
        objectToTest.rotateOnAxisY(AxisRotationValues.NINETY);
    
        objectToTest.resetBlockToType(BlockTypes.EARTH);
        Assert.assertEquals(objectToTest.getBlockType(), BlockTypes.EARTH);
        Assert.assertEquals(objectToTest.getFrontFace(), BlockFaceTypes.FRONT);
    }
  
    @Test
    public void testBlockRotation_blockIsCorrectlyRotated() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_STAIRS);
    
        objectToTest.rotateOnAxisX(AxisRotationValues.NINETY);
        objectToTest.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        objectToTest.rotateOnAxisZ(AxisRotationValues.NINETY);
    
        Assert.assertEquals(objectToTest.getTopFace(), BlockFaceTypes.BOTTOM);
        Assert.assertEquals(objectToTest.getBackFace(), BlockFaceTypes.LEFT);
        Assert.assertEquals(objectToTest.getFrontFace(), BlockFaceTypes.RIGHT);
        Assert.assertEquals(objectToTest.getBottomFace(), BlockFaceTypes.TOP);
        Assert.assertEquals(objectToTest.getRightFace(), BlockFaceTypes.FRONT);
        Assert.assertEquals(objectToTest.getLeftFace(), BlockFaceTypes.BACK);
    }
  
    @Test
    public void testBlockRotation_rotateDoorBlockByXAxis_doesNotRotate() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
    
        objectToTest.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
    
        Assert.assertEquals(objectToTest.getFrontFace(), BlockFaceTypes.FRONT);
        Assert.assertEquals(objectToTest.getBackFace(), BlockFaceTypes.BACK);
        Assert.assertEquals(objectToTest.getLeftFace(), BlockFaceTypes.LEFT);
        Assert.assertEquals(objectToTest.getRightFace(), BlockFaceTypes.RIGHT);
        Assert.assertEquals(objectToTest.getTopFace(), BlockFaceTypes.TOP);
        Assert.assertEquals(objectToTest.getBottomFace(), BlockFaceTypes.BOTTOM);
    }
  
    @Test
    public void testBlockRotation_rotateDoorBlockByZAxis_doesNotRotate() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
    
        objectToTest.rotateOnAxisZ(AxisRotationValues.MINUS_NINETY);
    
        Assert.assertEquals(objectToTest.getFrontFace(), BlockFaceTypes.FRONT);
        Assert.assertEquals(objectToTest.getBackFace(), BlockFaceTypes.BACK);
        Assert.assertEquals(objectToTest.getLeftFace(), BlockFaceTypes.LEFT);
        Assert.assertEquals(objectToTest.getRightFace(), BlockFaceTypes.RIGHT);
        Assert.assertEquals(objectToTest.getTopFace(), BlockFaceTypes.TOP);
        Assert.assertEquals(objectToTest.getBottomFace(), BlockFaceTypes.BOTTOM);
    }
  
    @Test
    public void testBlockRotation_doorBlockIsCorrectlyRotated() {
        Block objectToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_DOOR);
    
        objectToTest.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
        objectToTest.rotateOnAxisY(AxisRotationValues.NINETY);
        objectToTest.rotateOnAxisZ(AxisRotationValues.MINUS_NINETY);
    
        Assert.assertEquals(objectToTest.getFrontFace(), BlockFaceTypes.LEFT);
        Assert.assertEquals(objectToTest.getBackFace(), BlockFaceTypes.RIGHT);
        Assert.assertEquals(objectToTest.getLeftFace(), BlockFaceTypes.BACK);
        Assert.assertEquals(objectToTest.getRightFace(), BlockFaceTypes.FRONT);
        Assert.assertEquals(objectToTest.getTopFace(), BlockFaceTypes.TOP);
        Assert.assertEquals(objectToTest.getBottomFace(), BlockFaceTypes.BOTTOM);
    }
  
    @Test
    public void testCopyBlock_allPropertiesAreCopied() {
        Block copiedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_STAIRS);
    
        copiedBlock.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
        copiedBlock.rotateOnAxisZ(AxisRotationValues.MINUS_NINETY);
    
        Block blockToTest = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
        blockToTest.setBlockTo(copiedBlock);
    
        Assert.assertEquals(blockToTest.getBlockType(), copiedBlock.getBlockType());
        Assert.assertEquals(blockToTest.getBackFace(), copiedBlock.getBackFace());
        Assert.assertEquals(blockToTest.getBottomFace(), copiedBlock.getBottomFace());
        Assert.assertEquals(blockToTest.getFrontFace(), copiedBlock.getFrontFace());
        Assert.assertEquals(blockToTest.getLeftFace(), copiedBlock.getLeftFace());
        Assert.assertEquals(blockToTest.getRightFace(), copiedBlock.getRightFace());
        Assert.assertEquals(blockToTest.getTopFace(), copiedBlock.getTopFace());
    }
}