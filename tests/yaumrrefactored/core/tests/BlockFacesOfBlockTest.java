package yaumrrefactored.core.tests;

import yaumrrefactored.core.BlockFaceTypes;
import yaumrrefactored.core.BlockFacesOfBlock;
import org.junit.Test;

public class BlockFacesOfBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateFaces_throwsException() {
        BlockFacesOfBlock testCandidate = new BlockFacesOfBlock();
    
        testCandidate.setRightFace(BlockFaceTypes.BACK);
    }
}