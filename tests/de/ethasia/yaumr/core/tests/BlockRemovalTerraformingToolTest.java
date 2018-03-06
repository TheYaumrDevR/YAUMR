package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.BlockRemovalTerraformingTool;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import java.util.List;
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
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeMock();
        islandManipulationFacade.setNewlyCreatedIsland(new Island(2));
        islandManipulationFacade.placeBlockAt(SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK), new BlockPosition(0, 0, 0));
        
        List<BlockPosition> changedPositions = testCandidate.interactWithIsland(islandManipulationFacade, new BlockPosition(0, 0, 0));
        
        assertEquals(1, IslandManipulationFacadeMock.getCallCounterForMethodName("removeBlockAt"));
        assertEquals(1, changedPositions.size());
        assertEquals(0, changedPositions.get(0).x);
        assertEquals(0, changedPositions.get(0).y);
        assertEquals(0, changedPositions.get(0).z);
    }    
}
