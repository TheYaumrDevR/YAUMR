package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockShape;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockTypeToBlockShapeMapper;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.Vector2Int;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.VisualChunkData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author R
 */
public class ChunkPresenterImpl implements ChunkPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int CHUNK_EDGE_LENGTH_IN_BLOCKS = 16;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    Set<Vector2Int> changedChunkCoordinates;
    List<Block> blocksToBeRendered;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ChunkPresenterImpl() {
        changedChunkCoordinates = new HashSet<>();
        blocksToBeRendered = new LinkedList<>();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void setChangedPosition(int[] position) {
        if (position.length == 3) {
            int x = position[0];
            int z = position[2];
            
            int chunkX = (int)Math.floor((float)x / (float)CHUNK_EDGE_LENGTH_IN_BLOCKS);
            int chunkZ = (int)Math.floor((float)z / (float)CHUNK_EDGE_LENGTH_IN_BLOCKS);
            
            Vector2Int changedChunkCoordinate = new Vector2Int(chunkX, chunkZ);
            changedChunkCoordinates.add(changedChunkCoordinate);
        } else {
            throw new IllegalArgumentException("The position array for a position on an Island must consist of 3 integers corresponding to x, y and z axis position respectively.");
        }
    }

    @Override
    public void presentChunksForChangedPositions(Island island) {   
        for (Vector2Int chunkPositionToBeRendered : changedChunkCoordinates) {
            int zStart = chunkPositionToBeRendered.getY() * CHUNK_EDGE_LENGTH_IN_BLOCKS;
            int xStart = chunkPositionToBeRendered.getX() * CHUNK_EDGE_LENGTH_IN_BLOCKS;
            int zBound = zStart + CHUNK_EDGE_LENGTH_IN_BLOCKS;
            int xBound = xStart + CHUNK_EDGE_LENGTH_IN_BLOCKS;     
            
            zBound = zBound < island.getHorizontalEdgeLengthOfIslandInBlocks() ? zBound : island.getHorizontalEdgeLengthOfIslandInBlocks();
            xBound = xBound < island.getHorizontalEdgeLengthOfIslandInBlocks() ? xBound : island.getHorizontalEdgeLengthOfIslandInBlocks();
            
            int blockChunkX = 0;
            int blockChunkZ = 0;
            
            for (int i = 0; i < Island.HEIGHT_IN_BLOCKS; i++) {
                for (int j = zStart; j < zBound; j++) {
                    blockChunkZ++;
                    
                    for (int k = xStart; k < xBound; k++) {
                        Block blockToBeRendered = island.getBlockAt(new BlockPosition(k, i, j));
                        blockChunkX++;
                        
                        if (null != blockToBeRendered) {
                            blocksToBeRendered.add(blockToBeRendered);
                        }
                    }
                }
            }
          
            VisualChunkData renderData = createRenderDataForBlocks(blockChunkX, blockChunkZ);
            blocksToBeRendered.clear();
            
            // Pass render data to renderer;
        }
        
        changedChunkCoordinates.clear();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private VisualChunkData createRenderDataForBlocks(int blockChunkX, int blockChunkZ) {
        int amountOfAlreadyCreatedBlocksForChunk = 0;
        VisualChunkData renderData = new VisualChunkData();
        renderData.setUpWithNumberOfBlocksInChunk(blocksToBeRendered.size());
        
        for (Block block : blocksToBeRendered) {
            BlockShape blockShape = BlockTypeToBlockShapeMapper.getBlockShapeForBlockType(BlockTypes.ROCK); 
            blockShape.setBlockToCreateDataFrom(block);
            
            renderData.addVerticesToTemporaryBuffer(blockShape.getShapeVertices(blockChunkX, blockChunkZ));
            renderData.addIndicesToTemporaryBuffer(blockShape.getVertexIndices(amountOfAlreadyCreatedBlocksForChunk));
            renderData.addNormalsToTemporaryBuffer(blockShape.getNormals());
            renderData.addUVCoordinatesToTemporaryBuffer(blockShape.getUVCoordinates());
            
            amountOfAlreadyCreatedBlocksForChunk++;
        }
        
        renderData.buildChunkData();
        return renderData;
    }
    
    //</editor-fold>
}
