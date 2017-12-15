package yaumrrefactored.core.tests;

import yaumrrefactored.core.blocks.BlockFaceTypes;
import yaumrrefactored.core.blocks.BlockFacesOfBlock;
import org.junit.Test;

public class BlockFacesOfBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateFaces_throwsException() {
        BlockFacesOfBlock testCandidate = new BlockFacesOfBlock();
    
        testCandidate.setRightFace(BlockFaceTypes.BACK);
    }
}