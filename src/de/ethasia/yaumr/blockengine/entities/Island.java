package de.ethasia.yaumr.blockengine.entities;

/**
 * An island is a subdivision of a game world. Players can move from one island
 * to another through portals.
 * 
 * @author R
 */
public class Island {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Chunk chunks[][];
    private final int dimensions;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates an empty island of size dimensions * dimensions.
     * 
     * @param dimensions 
     */
    public Island(int dimensions) {
        chunks = new Chunk[dimensions][dimensions];
        this.dimensions = dimensions;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public int getDimensions() {
        return dimensions;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeBlock(int[] chunkPosition, int[] blockPosition, Block block) {
        if (2 == chunkPosition.length) {
            throwExceptionIfPositionIsOutsideIslandBounds(chunkPosition);
            
            if (null == chunks[chunkPosition[0]][chunkPosition[1]]) {
                chunks[chunkPosition[0]][chunkPosition[1]] = new Chunk();
            }
            
            chunks[chunkPosition[0]][chunkPosition[1]].placeBlock(block, blockPosition);
        }
    }
    
    public void removeBLock(int[] chunkPosition, int[] blockPosition) {
        if (2 == chunkPosition.length) {
            throwExceptionIfPositionIsOutsideIslandBounds(chunkPosition);
            
            if (null != chunks[chunkPosition[0]][chunkPosition[1]]) {
                chunks[chunkPosition[0]][chunkPosition[1]].removeBlock(blockPosition);                
            }
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void throwExceptionIfPositionIsOutsideIslandBounds(int[] position) {
            if (position[0] < 0 || position[0] >= dimensions) {
                throw new IllegalArgumentException("Cannot place a block outside the island bounds.");
            }
            
            if (position[1] < 0 || position[1] >= dimensions) {
                throw new IllegalArgumentException("Cannot place a block outside the island bounds.");
            }   
    }
    
    //</editor-fold>
}
