package de.ethasia.yaumr.core.shardgenerators.tests;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.shardgenerators.CoastlineGenerator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoastlineGeneratorTest {
    
    @Test
    public void testGenerateCoastline_for1x1Island_amountOfAirBlocksArePlacedBasedOnSeed() {
        Island islandToGenerateFor = new Island(1);
        CoastlineGenerator testCandidate = new CoastlineGenerator(islandToGenerateFor);
        
        int height = 127;
        
        int amountOfAirBlocks = 0;
        for (int i = 0; i < 5000; i++) {
            testCandidate.generateCoastline(height, i);  
            
            Block blockAtTestPosition = islandToGenerateFor.getBlockAt(new int[] {0, 127, 0});
            if (blockAtTestPosition.getBlockType() == BlockTypes.AIR) {
                amountOfAirBlocks++;
            }
        }
        
        assertEquals(904, amountOfAirBlocks);
    }
    
    @Test
    public void testGenerateCoastline_for1x1Island_amountOfRockBlocksArePlacedBasedOnSeed() {
        Island islandToGenerateFor = new Island(1);
        CoastlineGenerator testCandidate = new CoastlineGenerator(islandToGenerateFor);
        
        int height = 127;
        
        int amountOfRockBlocks = 0;
        for (int i = 0; i < 5000; i++) {
            testCandidate.generateCoastline(height, i);  
            
            Block blockAtTestPosition = islandToGenerateFor.getBlockAt(new int[] {0, 127, 0});
            if (blockAtTestPosition.getBlockType() == BlockTypes.ROCK) {
                amountOfRockBlocks++;
            }
        }
        
        assertEquals(4096, amountOfRockBlocks);
    }     
    
    @Test
    public void testGenerateCoastline_for2x2Island_rockBlocksArePlacedBasedOnSeed() {
        Island islandToGenerateFor = new Island(2);
        CoastlineGenerator testCandidate = new CoastlineGenerator(islandToGenerateFor);
        
        int height = 127;
        
        testCandidate.generateCoastline(height, 0); 
        
        Block topLeftBlock = islandToGenerateFor.getBlockAt(new int[] {0, 127, 0});
        Block bottomLeftBlock = islandToGenerateFor.getBlockAt(new int[] {0, 127, 1});
        Block bottomRightBlock = islandToGenerateFor.getBlockAt(new int[] {1, 127, 1});
        Block topRightBlock = islandToGenerateFor.getBlockAt(new int[] {1, 127, 0});
        
        assertEquals(BlockTypes.ROCK, topLeftBlock.getBlockType());
        assertEquals(BlockTypes.AIR, bottomLeftBlock.getBlockType());
        assertEquals(BlockTypes.AIR, bottomRightBlock.getBlockType());
        assertEquals(BlockTypes.ROCK, topRightBlock.getBlockType());
    }
}
