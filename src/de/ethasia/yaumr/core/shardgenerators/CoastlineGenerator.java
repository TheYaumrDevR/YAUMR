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
    private final Random rng;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CoastlineGenerator(Island islandToGenerateCoastlineFor) {
        this.islandToGenerateCoastlineFor = islandToGenerateCoastlineFor;
        rng = new Random(0);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void generateCoastline(int height, long seed) {
        BlockPosition position = new BlockPosition(0, height, 0);
        int randomZeroOrOne = getRandomNumberZeroOrOne();
        
        if (randomZeroOrOne == 0) {
            replaceAirBlockAt(position);
        } else if (randomZeroOrOne == 1) {
            replaceRockBlockAt(position);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
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