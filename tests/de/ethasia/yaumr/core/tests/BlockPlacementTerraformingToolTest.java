package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.BlockPlacementTerraformingTool;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockFaceTypes;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author R
 */
public class BlockPlacementTerraformingToolTest {
    
    @Test
    public void testRotateOnX_blockIsPresent_blockIsRotatedCorrectly() {  
        Block blockToRotate = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.WALNUT_STAIRS);
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(blockToRotate);
        
        testCandidate.rotateOnX();
        
        assertEquals(BlockFaceTypes.BACK, blockToRotate.getTopFace());
    }
    
    @Test
    public void testRotateOnY_blockIsPresent_blockIsRotatedCorrectly() {
        Block blockToRotate = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_STAIRS);
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(blockToRotate);
        
        testCandidate.rotateOnY();
        
        assertEquals(BlockFaceTypes.BACK, blockToRotate.getLeftFace());        
    }
    
    @Test
    public void testRotateOnZ_blockIsPresent_blockIsRotatedCorrectly() {
        Block blockToRotate = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_STAIRS);
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(blockToRotate);
        
        testCandidate.rotateOnZ();
        
        assertEquals(BlockFaceTypes.BOTTOM, blockToRotate.getRightFace());        
    }
    
    @Test
    public void testInteractWithIsland_blockIsPresent_copyBlockMethodIsCalledOnFacade() {
        Block blockToRotate = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_STAIRS);
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(blockToRotate);
        IslandManipulationFacadeMock.resetMethodCallCounts();
        
        testCandidate.interactWithIsland(new IslandManipulationFacadeMock(), new BlockPosition(0, 0, 0));
        
        assertEquals(1, IslandManipulationFacadeMock.getCallCounterForMethodName("copyBlockTo"));
    }
    
    @Test
    public void testRotateOnX_blockIsNotPresent_noExceptionIsThrown() {
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(null);
        testCandidate.rotateOnX();        
    }
    
    @Test
    public void testRotateOnY_blockIsNotPresent_noExceptionIsThrown() {
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(null);
        testCandidate.rotateOnY();         
    }
    
    @Test
    public void testRotateOnZ_blockIsNotPresent_noExceptionIsThrown() {
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(null);
        testCandidate.rotateOnZ();         
    }
    
    @Test
    public void testInteractWithIsland_blockIsNotPresent_copyBlockMethodIsNotCalledOnFacade() {
        BlockPlacementTerraformingTool testCandidate = new BlockPlacementTerraformingTool(null);
        IslandManipulationFacadeMock.resetMethodCallCounts();
        
        testCandidate.interactWithIsland(new IslandManipulationFacadeMock(), new BlockPosition(0, 0, 0));
        
        assertEquals(0, IslandManipulationFacadeMock.getCallCounterForMethodName("copyBlockTo"));        
    }    
}
