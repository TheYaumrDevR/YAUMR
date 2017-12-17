package yaumrrefactored.core;

import yaumrrefactored.core.blocks.BlockTypes;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.SimpleBlockFactory;

/**
 * Represents the highest level division of the game world. 
 * An island has a dimension and consists of blocks.
 * It has a fixed dimension which is square. 
 * The player can change between islands through warping mechanisms.
 * 
 * @author R
 */
public class Island {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int HEIGHT_IN_BLOCKS = 256;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final int edgeLengthOfHorizontalPlaneInBlocks;
    private Block[][][] blocks;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Island(int edgeLengthOfHorizontalPlaneInBlocks) {
        this.edgeLengthOfHorizontalPlaneInBlocks = edgeLengthOfHorizontalPlaneInBlocks;
        initializeEmptyIsland();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean placeBlockAt(Block block, BlockPosition position) {
        throwExceptionForInvalidBlockPosition(position);
        
        if (blockOnPositionIsDisplacedByBlock(position, block.getBlockType())) {
            blocks[position.x][position.y][position.z] = block;
            return true;
        }
        
        return false;
    } 
    
    public boolean removeBlockAt(BlockPosition position) {
        throwExceptionForInvalidBlockPosition(position);
        
        if (getBlockAt(position).getBlockType() != BlockTypes.AIR) {
            Block blockToRemove = blocks[position.x][position.y][position.z];
            if (!SimpleBlockFactory.blockTypesAreOfSameKind(blockToRemove.getBlockType(), BlockTypes.AIR)) {
                blocks[position.x][position.y][position.z] = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
                return true;
            }
            
            blocks[position.x][position.y][position.z].resetBlockToType(BlockTypes.AIR);            
            return true;
        }
        
        return false;
    }
    
    public boolean copyBlockTo(Block blockToCopy, BlockPosition position) {
        throwExceptionForInvalidBlockPosition(position);
        
        if (blockOnPositionIsDisplacedByBlock(position, blockToCopy.getBlockType())) {
            Block currentBlock = blocks[position.x][position.y][position.z];
            if (!SimpleBlockFactory.blockTypesAreOfSameKind(currentBlock.getBlockType(), blockToCopy.getBlockType())) {
                currentBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(blockToCopy.getBlockType());
                placeBlockAt(currentBlock, position);
            }
            
            currentBlock.setBlockTo(blockToCopy);
            return true;
        }
        
        return false;
    }
    
    public Block getBlockAt(BlockPosition position) {
        if (blockPositionIsOutsideMapBounds(position)) {
            return null;
        }
        
        return blocks[position.x][position.y][position.z];
    }
    
    public boolean checkBlockCanBeSetToPosition(Block toCheck, BlockPosition position) {
        if (blockPositionIsOutsideMapBounds(position)) {
            return false;
        }
        
        return blockOnPositionIsDisplacedByBlock(position, toCheck.getBlockType());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initializeEmptyIsland() {
        int xz = edgeLengthOfHorizontalPlaneInBlocks;
        blocks = new Block[xz][HEIGHT_IN_BLOCKS][xz];
        
        for (int i = 0; i < xz; i++) {
            for (int j = 0; j < HEIGHT_IN_BLOCKS; j++) {
                for (int k = 0; k < xz; k++) {
                    blocks[i][j][k] = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.AIR);
                }
            }
        }
    }
    
    private void throwExceptionForInvalidBlockPosition(BlockPosition position) {
        if (blockPositionIsOutsideMapBounds(position)) {
            throw new IllegalArgumentException("Cannot place block outside of island bounds.");
        }
    }
    
    private boolean blockPositionIsOutsideMapBounds(BlockPosition position) {
        return null == position 
                || position.x >= edgeLengthOfHorizontalPlaneInBlocks 
                || position.z >= edgeLengthOfHorizontalPlaneInBlocks 
                || position.y >= HEIGHT_IN_BLOCKS;
    }
    
    private boolean blockOnPositionIsDisplacedByBlock(BlockPosition position, BlockTypes blockType) {
        return blocks[position.x][position.y][position.z].getBlockType() == BlockTypes.AIR 
                || blocks[position.x][position.y][position.z].getBlockType().isDisplaced()
                && blockType.displacesDisplacableBlock();
    }
    
    //</editor-fold>
}
