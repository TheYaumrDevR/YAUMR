package de.ethasia.yaumr.blockengine.entities;

/**
 * A chunk is the smalles unit which can be rendered. It consists of blocks.
 * 
 * @author R
 */
public class Chunk {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Block[][][] blocks;
    private boolean isVisible;
    private boolean hasChangedSinceLastRender;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Chunk() {
        blocks = new Block[16][256][16];
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 16; k++) {
                    Block airBlock = new Block();
                    airBlock.setBlockType(BlockTypes.AIR);
                    
                    blocks[i][j][k] = airBlock;
                }
            }
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeBlock(Block block, GlobalBlockPosition blockPosition) {
        if (blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()].getBlockType() == BlockTypes.AIR) {
            blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()] = block;
                
            hasChangedSinceLastRender = true;
        }
    }
    
    public void removeBlock(GlobalBlockPosition blockPosition) {
        if (blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()].getBlockType() != BlockTypes.AIR) {
            Block airBlock = new Block();
            airBlock.setBlockType(BlockTypes.AIR);                
                
            blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()] = airBlock;
                
            hasChangedSinceLastRender = true;
        }
    }
    
    //</editor-fold>
}
