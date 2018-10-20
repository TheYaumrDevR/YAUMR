package de.ethasia.yaumr.core.shardgenerators;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import java.util.Random;

public class CoastlineGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Island islandToGenerateCoastlineFor;
    private Random rng;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CoastlineGenerator(Island islandToGenerateCoastlineFor) {
        this.islandToGenerateCoastlineFor = islandToGenerateCoastlineFor;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void generateCoastline(final int height, final long seed) {
        rng = new Random(seed);
        initializeAirBlocksOnHeight(height);
        
        if (islandIsTwoTimesTwo()) {
            generateCoastlineForTwoTimesTwoIsland(height);
        } else {
            generateCoastlineForOneBlockIsland(height);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initializeAirBlocksOnHeight(int height) {
        for (int i = 0; i < islandToGenerateCoastlineFor.getHorizontalEdgeLengthOfIslandInBlocks(); i++) {
            for (int j = 0; j < islandToGenerateCoastlineFor.getHorizontalEdgeLengthOfIslandInBlocks(); j++) {
                BlockPosition positionToPlaceAt = new BlockPosition(i, height, j);
                replaceAirBlockAt(positionToPlaceAt);
            }            
        }   
    }
    
    private boolean islandIsTwoTimesTwo() {
        return islandToGenerateCoastlineFor.getHorizontalEdgeLengthOfIslandInBlocks() == 2;
    }
    
    private void generateCoastlineForTwoTimesTwoIsland(int height) {
        int qudrantToPlaceBlockAt = rng.nextInt(5);
        
        while (4 != qudrantToPlaceBlockAt) {
            placeRockBlockOnQuadrantAndHeight(qudrantToPlaceBlockAt, height);
            qudrantToPlaceBlockAt = rng.nextInt(5);
        }
    }    
    
    private void placeRockBlockOnQuadrantAndHeight(int quadrant, int height) {
        BlockPosition positionToPlaceAt = getBlockPositionForQuadrantAndHeight(quadrant, height);
        replaceRockBlockAt(positionToPlaceAt);
    }
    
    private BlockPosition getBlockPositionForQuadrantAndHeight(int quadrant, int height) {
        switch (quadrant) {
            case 0:
                return new BlockPosition(0, height, 0);
            case 1:
                return new BlockPosition(0, height, 1);
            case 2:
                return new BlockPosition(1, height, 1);
            case 3:
                return new BlockPosition(1, height, 0);
        }

        return null;
    }
    
    private void generateCoastlineForOneBlockIsland(int height) {
        BlockPosition position = new BlockPosition(0, height, 0);
        int randomZeroOrOne = getRandomNumberZeroOrOne();
        
        if (randomZeroOrOne == 0) {
            replaceAirBlockAt(position);
        } else if (randomZeroOrOne == 1) {
            replaceRockBlockAt(position);
        }        
    }    
    
    private void replaceAirBlockAt(BlockPosition position) {
        Block block = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
        replaceBlockAt(block, position);
    }
    
    private void replaceRockBlockAt(BlockPosition position) {
        Block block = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        replaceBlockAt(block, position);
    }
    
    private void replaceBlockAt(Block block, BlockPosition position) {
        islandToGenerateCoastlineFor.removeBlockAt(position);
        islandToGenerateCoastlineFor.copyBlockTo(block, position);
    }
    
    private int getRandomNumberZeroOrOne() {
        return rng.nextInt(2);
    }
    
    //</editor-fold>
}