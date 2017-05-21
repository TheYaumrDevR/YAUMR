package de.ethasia.yaumr.blockengine.usecases.implementations;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.blockengine.usecases.interfaces.BlockPlacementUsecase;

/**
 *
 * @author R
 */
public class BlockPlacementUsecaseImpl implements BlockPlacementUsecase {

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void placeBlock(Vector3f interactionPoint, Island parentIsland, BlockTypes blocktype) {
        float[] origin = calculateOrigin(parentIsland.getDimensions());
        int[] globalBlockPosition = getGlobalBlockPosition(origin, interactionPoint);
        
        if (null != globalBlockPosition) {
            int[] chunkPosition = getChunkPosition(globalBlockPosition);
            int[] localBlockPosition = convertGlobalToLocalBlockPosition(globalBlockPosition, chunkPosition);
            
            Block placedBlock = new Block();
            placedBlock.setBlockType(blocktype);
            
            parentIsland.placeBlock(chunkPosition, localBlockPosition, placedBlock);
        }
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private float[] calculateOrigin(int islandDimension) {
        float leftMostPoint = -0.5f * ((islandDimension * 16) / 2);
        float topMostPoint = leftMostPoint;
        
        float[] origin = {leftMostPoint, 0.f, topMostPoint};
        
        return origin;
    }
    
    private int[] getGlobalBlockPosition(float[] origin, Vector3f interactionPoint) {
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
    
    private int[] getChunkPosition(int[] globalBlockPosition) {
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
