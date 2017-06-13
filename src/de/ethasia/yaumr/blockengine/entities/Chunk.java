package de.ethasia.yaumr.blockengine.entities;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.usecases.interfaces.FallingBlockCellularAutomaton;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;

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
    
    public Island getParentIsland() {
        return parentIsland;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean placeBlock(BlockTypes blockType, GlobalBlockPosition blockPosition) {
        if (placeBlockWithoutMarkingDynamicBlocks(blockType, blockPosition)) {
            Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];            
            
            if (block.getBlockType().fallsDownWhenAirBelow()) {
                if (blockPosition.getBlockPositionY() != 0) {
                    if (blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY() - 1][blockPosition.getBlockPositionZ()].getBlockType() == BlockTypes.AIR) {
                        YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(FallingBlockCellularAutomaton.class).markPositionInChunkAsUnstable(blockPosition, this);
                    }
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    public void removeBlock(GlobalBlockPosition blockPosition) {
        if (removeWithoutMarkingDynamicBlocks(blockPosition)) {
            if (blockPosition.getBlockPositionY() != 255) {
                Block blockAbove = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY() + 1][blockPosition.getBlockPositionZ()];         
                
                if (blockAbove.getBlockType().fallsDownWhenAirBelow()) {
                    YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(FallingBlockCellularAutomaton.class).markPositionInChunkAsUnstable(blockPosition.incrementY(), this);
                }                
            }
        }
    }
    
    public void moveBlockDown(GlobalBlockPosition blockPosition) {
        Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];
        BlockTypes blockType = block.getBlockType();
        
        if (blockPosition.getBlockPositionY() != 0) {
            removeWithoutMarkingDynamicBlocks(blockPosition);
            placeBlockWithoutMarkingDynamicBlocks(blockType, blockPosition.decrementY());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    public boolean placeBlockWithoutMarkingDynamicBlocks(BlockTypes blockType, GlobalBlockPosition blockPosition) {
        Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];
        
        if (block.getBlockType() == BlockTypes.AIR) {
            block.setBlockType(blockType);
            updateBlockFaceVisibility(new int[]{blockPosition.getBlockPositionX(), blockPosition.getBlockPositionY(), blockPosition.getBlockPositionZ()}, block);
            amountOfVisibleBlocks++;
            
            updateBlockVisibilityForNeighborsOfPlacedBlock(blockPosition);
            
            return true;
        }
        
        return false;
    }
    
    public boolean removeWithoutMarkingDynamicBlocks(GlobalBlockPosition blockPosition) {
        Block block = blocks[blockPosition.getBlockPositionX()][blockPosition.getBlockPositionY()][blockPosition.getBlockPositionZ()];
        
        if (block.getBlockType() != BlockTypes.AIR) {
            block.setBlockType(BlockTypes.AIR);                
            amountOfVisibleBlocks--;    

            updateBlockVisibilityForNeighborsOfPlacedBlock(blockPosition);
            
            return true;
        }
        
        return false;
    }
    
    private void updateBlockVisibilityForNeighborsOfPlacedBlock(GlobalBlockPosition placedBlockPosition) {
        if (placedBlockPosition.getBlockPositionX() - 1 > -1) {
            Block block = blocks[placedBlockPosition.getBlockPositionX() - 1][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ()];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX() - 1, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()}, block);
        } else {
            if (getChunkPositionX() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() - 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{15, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            }
        }
        
        if (placedBlockPosition.getBlockPositionX() + 1 < 16) {
            Block block = blocks[placedBlockPosition.getBlockPositionX() + 1][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ()];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX() + 1, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()}, block);          
        } else {
            if (getChunkPositionX() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() + 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{0, placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ()});
            }
        }

        if (placedBlockPosition.getBlockPositionZ() - 1 > -1) {
            Block block = blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ() - 1];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ() - 1}, block);
        } else {
            if (getChunkPositionY() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() - 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), 15});
            }
        }
        
        if (placedBlockPosition.getBlockPositionZ() + 1 < 16) {
            Block block = blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY()][placedBlockPosition.getBlockPositionZ() + 1];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), placedBlockPosition.getBlockPositionZ() + 1}, block);
        } else {
            if (getChunkPositionY() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() + 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                neighborChunk.updateBlockVisibilityForBorderBlock(new int[]{placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY(), 0});
            }
        }     
        
        if (placedBlockPosition.getBlockPositionY() - 1 > -1) {
            Block block = blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY() - 1][placedBlockPosition.getBlockPositionZ()];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY() - 1, placedBlockPosition.getBlockPositionZ()}, block);
        }
        
        if (placedBlockPosition.getBlockPositionY() + 1 < 256) {
            Block block = blocks[placedBlockPosition.getBlockPositionX()][placedBlockPosition.getBlockPositionY() + 1][placedBlockPosition.getBlockPositionZ()];
            updateBlockFaceVisibility(new int[] {placedBlockPosition.getBlockPositionX(), placedBlockPosition.getBlockPositionY() + 1, placedBlockPosition.getBlockPositionZ()}, block);
        }          
    }
    
    private void updateBlockVisibilityForBorderBlock(int[] blockPosition) {
        Block block = blocks[blockPosition[0]][blockPosition[1]][blockPosition[2]];
        
        if (block.getBlockType() != BlockTypes.AIR) {
            updateBlockFaceVisibility(blockPosition, block);
            
            ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
            classInstanceContainer.getSingletonInstance(IslandRenderer.class).updateModifiedChunk(parentIsland, this);            
        }        
    }
    
    private void updateBlockFaceVisibility(int[] placedBlockPosition, Block block) {
        int amountOfHiddenFaces = 0;
        block.clearNonObstructedDirections();
        
        if (placedBlockPosition[0] - 1 > -1) {
            if (blocks[placedBlockPosition[0] - 1][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.LEFT);
            }
        } else {
            if (getChunkPositionX() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() - 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[15][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                    block.setObstructedDirection(FacingDirection.LEFT);
                }                
            }
        }
        
        if (placedBlockPosition[0] + 1 < 16) {
            if (blocks[placedBlockPosition[0] + 1][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.RIGHT);
            }
        } else {
            if (getChunkPositionX() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * (getChunkPositionX() + 1) + getChunkPositionY();
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[0][placedBlockPosition[1]][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                    block.setObstructedDirection(FacingDirection.RIGHT);
                }                
            }
        }

        if (placedBlockPosition[2] - 1 > -1) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1]][placedBlockPosition[2] - 1].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.BACK);
            }
        } else {
            if (getChunkPositionY() > 0) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() - 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[placedBlockPosition[0]][placedBlockPosition[1]][15].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                    block.setObstructedDirection(FacingDirection.BACK);
                }                
            }
        }
        
        if (placedBlockPosition[2] + 1 < 16) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1]][placedBlockPosition[2] + 1].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.FRONT);
            }
        } else {
            if (getChunkPositionY() < parentIsland.getDimensions() - 1) {
                int neighborChunkIndex = parentIsland.getDimensions() * getChunkPositionX() + getChunkPositionY() + 1;
                Chunk neighborChunk = parentIsland.getChunks().get(neighborChunkIndex);
                
                if (neighborChunk.getBlocks()[placedBlockPosition[0]][placedBlockPosition[1]][0].getBlockType().hidesNeighborBlocks()) {
                    amountOfHiddenFaces++;
                    block.setObstructedDirection(FacingDirection.FRONT);
                }                
            }
        }     
        
        if (placedBlockPosition[1] - 1 > -1) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1] - 1][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.BOTTOM);
            }
        }
        
        if (placedBlockPosition[1] + 1 < 256) {
            if (blocks[placedBlockPosition[0]][placedBlockPosition[1] + 1][placedBlockPosition[2]].getBlockType().hidesNeighborBlocks()) {
                amountOfHiddenFaces++;
                block.setObstructedDirection(FacingDirection.TOP);
            }
        }    
        
        block.setVisible(amountOfHiddenFaces != 6);
    }
    
    //</editor-fold>
}
