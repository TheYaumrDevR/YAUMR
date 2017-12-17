package yaumrrefactored.core.blocks;

import yaumrrefactored.core.Island;

/**
 * Represents a strategy which places and removes Blocks.
 * 
 * @author R
 */
public interface BlockPlacementStrategy {
    
    public void setBlockToPlace(Block value);
    public boolean placeBlockOnIslandAt(Island island, BlockPosition blockPosition);
    public boolean removeBlockFromIslandAt(Island island, BlockPosition blockPosition);
    public boolean copyBlockToPositionOnIsland(Island island, BlockPosition blockPosition);
}
