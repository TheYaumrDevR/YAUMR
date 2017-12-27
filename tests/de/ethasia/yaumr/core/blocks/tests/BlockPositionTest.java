package de.ethasia.yaumr.core.blocks.tests;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import org.junit.Assert;
import org.junit.Test;



public class BlockPositionTest {
  
    @Test(expected=IllegalArgumentException.class)
    public void createNewBlockPosition_yBelowZero_throwsException() {
        BlockPosition testCandidate = new BlockPosition(13, -87, 87);
        Assert.assertEquals(13, testCandidate.x);
    }
  
    @Test(expected=IllegalArgumentException.class)
        public void createNewBlockPosition_yAboveIslandHeight_throwsException() {
        BlockPosition testCandidate = new BlockPosition(13, 256, 87);
        Assert.assertEquals(13, testCandidate.x);
    }
  
    @Test
    public void getPositionOneBelow_oneBelowIsSpace_returnsPositionOneBelow() {
        BlockPosition testCandidate = new BlockPosition(13, 174, 87);
        BlockPosition result = testCandidate.getPositionOneBelow();
    
        Assert.assertEquals(173, result.y);
    }
  
    @Test
    public void getPositionOneBelow_oneBelowIsBorder_returnsNull() {
        BlockPosition testCandidate = new BlockPosition(54, 0, 190);
        BlockPosition result = testCandidate.getPositionOneBelow();
    
        Assert.assertNull(result);
    }
  
    @Test
    public void getPositionOneAbove_oneAboveIsSpace_returnsPositionOneAbove() {
        BlockPosition testCandidate = new BlockPosition(63, 0, 29);
        BlockPosition result = testCandidate.getPositionOneAbove();
    
        Assert.assertEquals(1, result.y);
    }
  
    @Test
    public void getPositionOneAbove_oneAboveIsBorder_returnsNull() {
        BlockPosition testCandidate = new BlockPosition(23, 255, 93);
        BlockPosition result = testCandidate.getPositionOneAbove();
    
        Assert.assertNull(result);
    }
}
