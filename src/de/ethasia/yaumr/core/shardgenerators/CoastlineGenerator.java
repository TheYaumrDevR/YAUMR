package de.ethasia.yaumr.core.shardgenerators;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;

public class CoastlineGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Island islandToGenerateCoastlineFor;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CoastlineGenerator(Island islandToGenerateCoastlineFor) {
        this.islandToGenerateCoastlineFor = islandToGenerateCoastlineFor;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void generateCoastline(int height, long seed) {
        Block block = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
        BlockPosition position = new BlockPosition(0, height, 0);
        
        islandToGenerateCoastlineFor.placeBlockAt(block, position);
    }
    
    //</editor-fold>
}