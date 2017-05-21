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
    
    public void placeBlock(Block block, int[] position) {
        if (3 == position.length) {
            throwExceptionIfPositionIsOutisdeBounds(position);
            
            if (blocks[position[0]][position[1]][position[2]].getBlockType() == BlockTypes.AIR) {
                blocks[position[0]][position[1]][position[2]] = block;
                
                hasChangedSinceLastRender = true;
            }
        }
    }
    
    public void removeBlock(int[] position) {
        if (3 == position.length) {
            throwExceptionIfPositionIsOutisdeBounds(position);
            
            if (blocks[position[0]][position[1]][position[2]].getBlockType() != BlockTypes.AIR) {
                Block airBlock = new Block();
                airBlock.setBlockType(BlockTypes.AIR);                
                
                blocks[position[0]][position[1]][position[2]] = airBlock;
                
                hasChangedSinceLastRender = true;
            }
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void throwExceptionIfPositionIsOutisdeBounds(int[] position) {
        if (position[0] < 0 || position[0] >= 16) {
            throw new IllegalArgumentException("Cannot place a block outside the chunk dimensions.");
        }
        
        if (position[1] < 0 || position[1] >= 256) {
            throw new IllegalArgumentException("Cannot place a block outside the chunk dimensions.");
        }    
        
        if (position[2] < 0 || position[2] >= 16) {
            throw new IllegalArgumentException("Cannot place a block outside the chunk dimensions.");
        }         
    }
    
    //</editor-fold>
}
