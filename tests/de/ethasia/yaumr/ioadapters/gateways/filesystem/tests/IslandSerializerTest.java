package de.ethasia.yaumr.ioadapters.gateways.filesystem.tests;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.AxisRotationValues;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandMetaData;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandSerializer;
import java.util.UUID;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author R
 */
public class IslandSerializerTest {
    
    @Test
    public void testAddByteBlocksForIslandBlockData_islandHasBlocks_correctByteDataIsCreated() {
        Island island = new Island(4);
        Block rockBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        Block earthBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        BlockPosition basePlacementPosition = new BlockPosition(1, 0, 1);
        
        island.copyBlockTo(rockBlock, basePlacementPosition);
        island.copyBlockTo(rockBlock, basePlacementPosition.getPositionOneAbove());
        island.copyBlockTo(rockBlock, basePlacementPosition.getPositionOneAbove().getPositionOneAbove());
        island.copyBlockTo(earthBlock, basePlacementPosition.getPositionOneAbove().getPositionOneAbove().getPositionOneAbove());
        
        IslandSerializer testCandidate = new IslandSerializer();
        testCandidate.addByteBlocksForIslandBlockData(island);
        byte[] result = testCandidate.getByteBufferForAddedData();
        
        assertEquals(42, result.length);
        assertEquals(2, result[0]);
        assertEquals(4, result[1]);
        assertEquals(0, result[2]);
        assertEquals(result[3], (byte)(result[3] & 0xC4));
        assertEquals(result[4], (byte)(result[4] & 0x32));
    }
    
    @Test
    public void testAddByteBlocksForIslandBlockData_islandHasRepeatedBlocksWithRotatedBlockBetween_correctByteDataIsCreated() {
        Island island = new Island(4);
        Block woodBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_TRUNK);
        Block earthBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        BlockPosition basePlacementPosition = new BlockPosition(2, 0, 1);
        BlockPosition positionFourAbove = new BlockPosition(2, 4, 1);
        
        island.copyBlockTo(woodBlock, basePlacementPosition);
        island.copyBlockTo(woodBlock, basePlacementPosition.getPositionOneAbove());
        island.copyBlockTo(woodBlock, basePlacementPosition.getPositionOneAbove().getPositionOneAbove());        
        island.copyBlockTo(woodBlock, positionFourAbove);
        island.copyBlockTo(woodBlock, positionFourAbove.getPositionOneAbove());
        woodBlock.rotateOnAxisY(AxisRotationValues.NINETY);
        island.copyBlockTo(woodBlock, basePlacementPosition.getPositionOneAbove().getPositionOneAbove().getPositionOneAbove());        
        island.copyBlockTo(earthBlock, positionFourAbove.getPositionOneAbove().getPositionOneAbove());
        
        IslandSerializer testCandidate = new IslandSerializer();
        testCandidate.addByteBlocksForIslandBlockData(island);
        byte[] result = testCandidate.getByteBufferForAddedData();
        
        assertEquals(84, result.length);
        assertEquals(4, result[0]);
        assertEquals(4, result[1]);
        assertEquals(0, result[2]);
        assertEquals(result[3], (byte)(result[3] & 0xC7));
        assertEquals(result[4], (byte)(result[4] & 0x32));
    }
    
    @Test
    public void testAddByteBlocksForIslandBlockData_islandHasNoBlocks_emptyByteDataIsCreated() {
        Island island = new Island(4);
        
        IslandSerializer testCandidate = new IslandSerializer();
        testCandidate.addByteBlocksForIslandBlockData(island);
        byte[] result = testCandidate.getByteBufferForAddedData();
        
        assertEquals(0, result.length);
    }    
    
    @Test
    public void testAddByteBlocksForIslandMetadata_metaDataIsPassed_correctBytesAreReturned() {
        IslandSerializer testCandidate = new IslandSerializer();
        IslandMetaData islandMetaData = new IslandMetaData();
        islandMetaData.setIslandEdgeLengthInBlocks(4);
        islandMetaData.setIslandName("Test");
        islandMetaData.setIslandGUID(UUID.randomUUID());
        
        testCandidate.addByteBlocksForIslandMetadata(islandMetaData);

        byte[] result = testCandidate.getByteBufferForAddedData();  
        int islandNameByteLength = islandMetaData.getIslandName().getBytes().length;
        
        assertEquals(17 + islandNameByteLength, result.length);
        assertEquals(islandMetaData.getIslandName().getBytes()[0], result[0]);
    }
}
