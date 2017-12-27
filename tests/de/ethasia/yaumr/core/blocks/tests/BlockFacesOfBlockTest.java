package de.ethasia.yaumr.core.blocks.tests;

import de.ethasia.yaumr.core.blocks.BlockFaceTypes;
import de.ethasia.yaumr.core.blocks.BlockFacesOfBlock;
import org.junit.Test;

public class BlockFacesOfBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateFaces_throwsException() {
        BlockFacesOfBlock testCandidate = new BlockFacesOfBlock();
    
        testCandidate.setRightFace(BlockFaceTypes.BACK);
    }
}