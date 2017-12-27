package de.ethasia.yaumr.blockengine.entities;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * An island is a subdivision of a game world. Players can move from one island
 * to another through portals.
 * 
 * @author R
 */
public class Island {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<Chunk> chunks;
    private final int dimensions;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates an empty island of size dimensions * dimensions.
     * 
     * @param dimensions 
     */
    public Island(int dimensions) {
        chunks = new ArrayList<>();
        this.dimensions = dimensions;
        initChunkList();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public int getDimensions() {
        return dimensions;
    }
    
    public List<Chunk> getChunks() {
        return chunks;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean placeBlock(Vector3f interactionPoint, BlockTypes blockType) {
        GlobalBlockPosition blockPosition = calculateBlockPositionInIsland(interactionPoint);
        boolean blockPlacedOrOutsideGrid = true;
        
        if (null != blockPosition) {
            int chunkIndex = dimensions * blockPosition.getChunkPositionX() + blockPosition.getChunkPositionY();
            blockPlacedOrOutsideGrid = chunks.get(chunkIndex).placeBlock(blockType, blockPosition);
            
            ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
            classInstanceContainer.getSingletonInstance(IslandRenderer.class).updateModifiedChunk(this, chunks.get(chunkIndex));
        }
        
        return blockPlacedOrOutsideGrid;
    }
    
    public void removeBLock(Vector3f interactionPoint) {
        GlobalBlockPosition blockPosition = calculateBlockPositionInIsland(interactionPoint);
        
        if (null != blockPosition) {
            int chunkIndex = dimensions * blockPosition.getChunkPositionX() + blockPosition.getChunkPositionY();            
            
            if (null != chunks.get(chunkIndex)) {
                chunks.get(chunkIndex).removeBlock(blockPosition);   
                
                ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
                classInstanceContainer.getSingletonInstance(IslandRenderer.class).updateModifiedChunk(this, chunks.get(chunkIndex));                
            }
        }
    }
    
    /**
     * 
     * @param interactionPoint The interaction point. Used to calculate the block pointed at.
     * @param positionRelativeToInteractionPoint The block position relative to the block pointed at.
     * e.g. [-1, 0, 0] is the block one position to the left (on the x-axis).
     * @return 
     */
    public boolean blockOnPositionRelativeToInteractionPointIsFree(Vector3f interactionPoint, int[] positionRelativeToInteractionPoint) {
        return false;
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
    
    private void initChunkList() {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                Chunk chunk = new Chunk(i, j);
                chunk.setParentIsland(this);
                chunks.add(chunk);                
            }
        }
    }
    
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
