package de.ethasia.yaumr.blockengine.entities;

/**
 * A chunk is the smalles unit which can be rendered. It consists of blocks.
 * 
 * @author R
 */
public class Chunk {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Block[][][] blocks;
    private final int chunkPositionX;
    private final int chunkPositionY;
    private Island parentIsland;
    private int amountOfVisibleBlocks;
    private boolean isVisible;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Chunk(int chunkPositionX, int chunkPositionY) {
        blocks = new Block[16][256][16];
        this.chunkPositionX = chunkPositionX;
        this.chunkPositionY = chunkPositionY;
        
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
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public Block[][][] getBlocks() {
        return blocks;
    }
    
    public int getChunkPositionX() {
        return chunkPositionX;
    }
    
    public int getChunkPositionY() {
        return chunkPositionY;
    }
    
    public boolean hasVisibleBlocks() {
        return amountOfVisibleBlocks != 0;
    }
    
    public void setParentIsland(Island parentIsland) {
        this.parentIsland = parentIsland;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeBlock(BlockTypes blockType, GlobalBlockPosition blockPosition) {
        Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];
        
        if (block.getBlockType() == BlockTypes.AIR) {
            block.setBlockType(blockType);
            block.setVisible(true);
            amountOfVisibleBlocks++;
            updateBlockVisibilityForNeighborsOfPlacedBlock(blockPosition);
        }
    }
    
    public void removeBlock(GlobalBlockPosition blockPosition) {
        Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];
        
        if (block.getBlockType() != BlockTypes.AIR) {
            block.setBlockType(BlockTypes.AIR);                
            amountOfVisibleBlocks--;    

            updateBlockVisibilityForNeighborsOfPlacedBlock(blockPosition);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBlockVisibilityForNeighborsOfPlacedBlock(GlobalBlockPosition placedBlockPosition) {
        if (placedBlockPosition.getBlockPositionX() - 1 > -1) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX() - 1, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            blocks[placedBlockPosition.getBlockPositionX() - 1][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ()].setVisible(blockIsVisible);
        } else {
            if (getChunkPositionX() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() - 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{15, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            }
        }
        
        if (placedBlockPosition.getBlockPositionX() + 1 < 16) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX() + 1, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            blocks[placedBlockPosition.getBlockPositionX() + 1][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ()].setVisible(blockIsVisible);            
        } else {
            if (getChunkPositionX() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() + 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{0, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            }
        }

        if (placedBlockPosition.getBlockPositionZ() - 1 > -1) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ() - 1});
            blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ() - 1].setVisible(blockIsVisible);
        } else {
            if (getChunkPositionY() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() - 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), 15});
            }
        }
        
        if (placedBlockPosition.getBlockPositionZ() + 1 < 16) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ() + 1});
            blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ() + 1].setVisible(blockIsVisible);
        } else {
            if (getChunkPositionY() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() + 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), 0});
            }
        }     
        
        if (placedBlockPosition.getBlockPositionY() - 1 > -1) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY() - 1, placedBlockPosition.getBlockPositionZ()});
            blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY() - 1][placedBlockPosition.getBlockPositionZ()].setVisible(blockIsVisible);
        }
        
        if (placedBlockPosition.getBlockPositionY() + 1 < 256) {
            boolean blockIsVisible = isBlockAtPositionVisible(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY() + 1, placedBlockPosition.getBlockPositionZ()});
            blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY() + 1][placedBlockPosition.getBlockPositionZ()].setVisible(blockIsVisible);
        }          
    }
    
    private void updateBlockVisibilityForBorderBlock(int[] blockPosition) {
        boolean blockIsVisible = isBlockAtPositionVisible(blockPosition);
        blocks[blockPosition[0]][blockPosition[1]][blockPosition[2]].setVisible(blockIsVisible);
    }
    
    private boolean isBlockAtPositionVisible(int[] placedBlockPosition) {
        int amountOfHiddenFaces = 0;
        
        if (placedBlockPosition[0] - 1 > -1) {
            if (blocks[placedBlockPosition[0] - 1][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        } else {
            if (getChunkPositionX() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() - 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[15][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                }                
            }
        }
        
        if (placedBlockPosition[0] + 1 < 16) {
            if (blocks[placedBlockPosition[0] + 1][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        } else {
            if (getChunkPositionX() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() + 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[0][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                }                
            }
        }

        if (placedBlockPosition[2] - 1 > -1) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1]][placedBlockPosition[2] - 1].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        } else {
            if (getChunkPositionY() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() - 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[placedBlockPosition[0]][placedBlockPosition[1]][15].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                }                
            }
        }
        
        if (placedBlockPosition[2] + 1 < 16) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1]][placedBlockPosition[2] + 1].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        } else {
            if (getChunkPositionY() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() + 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[placedBlockPosition[0]][placedBlockPosition[1]][0].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                }                
            }
        }     
        
        if (placedBlockPosition[1] - 1 > -1) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1] - 1][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        }
        
        if (placedBlockPosition[1] + 1 < 256) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1] + 1][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
            }
        }    
        
        return amountOfHiddenFaces != 6;
    }
    
    //</editor-fold>
}
