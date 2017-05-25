package de.ethasia.yaumr.blockengine.entities;

import com.jme3.math.Vector3f;

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
    
    public void placeBlock(Vector3f interactionPoint, Block block) {
        GlobalBlockPosition blockPosition = calculateBlockPositionInIsland(interactionPoint);
        
        if (null != blockPosition) {
            if (null == chunks[blockPosition.getChunkPositionX()][blockPosition.getChunkPositionY()]) {
                chunks[blockPosition.getChunkPositionX()][blockPosition.getChunkPositionY()] = new Chunk();
            }
            
            chunks[blockPosition.getChunkPositionX()][blockPosition.getChunkPositionY()].placeBlock(block, blockPosition);
        }
    }
    
    public void removeBLock(Vector3f interactionPoint) {
        GlobalBlockPosition blockPosition = calculateBlockPositionInIsland(interactionPoint);
        
        if (null != blockPosition) {
            if (null != chunks[blockPosition.getChunkPositionX()][blockPosition.getChunkPositionY()]) {
                chunks[blockPosition.getChunkPositionX()][blockPosition.getChunkPositionY()].removeBlock(blockPosition);                
            }
        }
    }
    
    public GlobalBlockPosition calculateBlockPositionInIsland(Vector3f interactionPoint) {
        float[] origin = calculateOrigin();
        int[] globalBlockPosition = getGlobalBlockPositionFromInteractionPoint(origin, interactionPoint);
        
        if (null != globalBlockPosition) {
            int[] chunkPosition = getChunkPositionFromGlobalBlockPosition(globalBlockPosition);
            int[] localBlockPosition = convertGlobalToLocalBlockPosition(globalBlockPosition, chunkPosition);
            
            GlobalBlockPosition blockPosition = new GlobalBlockPosition();
            blockPosition.setChunkPositionX(chunkPosition[0]);
            blockPosition.setChunkPositionY(chunkPosition[1]);
            blockPosition.setBlockPositionX(localBlockPosition[0]);
            blockPosition.setBlockPositionY(localBlockPosition[1]);
            blockPosition.setBlockPositionZ(localBlockPosition[2]);
            
            return blockPosition;
        }   
        
        return null;
    }   
    
    public float[] calculateOrigin() {
        float leftMostPoint = -0.5f * ((dimensions * 16) / 2);
        float topMostPoint = leftMostPoint;
        
        float[] origin = {leftMostPoint, 0.f, topMostPoint};
        
        return origin;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private int[] getGlobalBlockPositionFromInteractionPoint(float[] origin, Vector3f interactionPoint) {
        if (interactionPoint.x < origin[0] || interactionPoint.x > -origin[0]) {
            return null;
        }
        
        if (interactionPoint.y < 0 || interactionPoint.y > 0.5f * 256) {
            return null;
        }   
        
        if (interactionPoint.z < origin[2] || interactionPoint.z > -origin[2]) {
            return null;
        }  
        
        float xPositionOffset = -(origin[0] - interactionPoint.x);
        float zPositionOffset = -(origin[2] - interactionPoint.z);
        
        return new int[]{(int)Math.floor(xPositionOffset / 0.5f), (int)Math.floor(interactionPoint.y / 0.5f), (int)Math.floor(zPositionOffset / 0.5f)};
    }
    
    private int[] getChunkPositionFromGlobalBlockPosition(int[] globalBlockPosition) {
        int chunkPositionX = (int)Math.floor(globalBlockPosition[0] / 16);
        int chunkPositionY = (int)Math.floor(globalBlockPosition[2] / 16);
        
        return new int[]{chunkPositionX, chunkPositionY};
    }

    private int[] convertGlobalToLocalBlockPosition(int[] globalBlockPosition, int[] chunkPosition) {
        int localBlockPositionX = globalBlockPosition[0] - (chunkPosition[0] * 16);
        int localBlockPositionZ = globalBlockPosition[2] - (chunkPosition[1] * 16);
        
        return new int[]{localBlockPositionX, globalBlockPosition[1], localBlockPositionZ};
    }    
    
    //</editor-fold>
}
