package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.BlockRemovalTerraformingTool;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author R
 */
public class BlockRemovalTerraformingToolTest {
    
    @Test
    public void testInteractWithIsland_everythingIsPresent_removeBlockMethodIsCalledOnFacade() {
        BlockRemovalTerraformingTool testCandidate = new BlockRemovalTerraformingTool();
        IslandManipulationFacadeMock.resetMethodCallCounts();
        
        testCandidate.interactWithIsland(new IslandManipulationFacadeMock(), new BlockPosition(0, 0, 0));
        
        assertEquals(1, IslandManipulationFacadeMock.getCallCounterForMethodName("removeBlockAt"));
    }    
}
