package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockFaceTypes;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockShape;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockTypeToBlockShapeMapper;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.Vector2Int;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.VisualChunkData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author R
 */
public class ChunkPresenterImpl implements ChunkPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int CHUNK_EDGE_LENGTH_IN_BLOCKS = 16;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    Set<Vector2Int> changedChunkCoordinates;
    Map<Block, int[]> blocksToBeRendered;
    
    private Island currentlyRenderedIsland;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ChunkPresenterImpl() {
        changedChunkCoordinates = new HashSet<>();
        blocksToBeRendered = new HashMap<>();
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
        currentlyRenderedIsland = island;
        ChunkRenderer chunkRenderer = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(ChunkRenderer.class);
        
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
                    for (int k = xStart; k < xBound; k++) {
                        int[] positionOfRenderedBlockLocalAndGlobal = {k, i, j, blockChunkX, blockChunkZ};
                        
                        Block blockToBeRendered = island.getBlockAt(positionOfRenderedBlockLocalAndGlobal);
                        
                        if (null != blockToBeRendered) {
                            blocksToBeRendered.put(blockToBeRendered, positionOfRenderedBlockLocalAndGlobal);
                        }
                        
                        blockChunkX++;
                    }
                    
                    blockChunkZ++;
                    blockChunkX = 0;
                }
                
                blockChunkZ = 0;
            }
          
            VisualChunkData renderData = createRenderDataForBlocks();
            renderData.setPosition(chunkPositionToBeRendered.getX(), chunkPositionToBeRendered.getY());
            blocksToBeRendered.clear();
            
            chunkRenderer.renderChunk(renderData);
        }
        
        changedChunkCoordinates.clear();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private VisualChunkData createRenderDataForBlocks() {
        int indexBlockOffset = 0;
        VisualChunkData renderData = new VisualChunkData();
        renderData.setUpWithNumberOfBlocksInChunk(blocksToBeRendered.size());
        
        Set<Block> blocks = blocksToBeRendered.keySet();
        for (Block block : blocks) {
            int[] blockPosition = blocksToBeRendered.get(block);
            
            BlockShape blockShape = BlockTypeToBlockShapeMapper.getBlockShapeForBlockType(block.getBlockType()); 
            blockShape.setBlockToCreateDataFrom(block);
            blockShape.setBackFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.BACK, blockPosition));
            blockShape.setRightFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.RIGHT, blockPosition));
            blockShape.setFrontFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.FRONT, blockPosition));
            blockShape.setLeftFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.LEFT, blockPosition));
            blockShape.setTopFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.TOP, blockPosition));
            blockShape.setBottomFaceOfBlockIsCovered(currentlyRenderedIsland.blockFaceAtPositionIsHidden(BlockFaceTypes.BOTTOM, blockPosition));
                        
            renderData.addVerticesToTemporaryBuffer(blockShape.getShapeVertices(blockPosition[3], blockPosition[4]));
            int[] indices = blockShape.getVertexIndicesForLastCreatedVertices(indexBlockOffset);
            renderData.addIndicesToTemporaryBuffer(indices);
            renderData.addNormalsToTemporaryBuffer(blockShape.getNormalsForLastCreatedVertices());
            renderData.addUVCoordinatesToTemporaryBuffer(blockShape.getUVCoordinatesForLastCreatedVertices());
            
            indexBlockOffset += blockShape.getHighestIndexNumberForCurrentBlock();
        }
        
        renderData.buildChunkData();
        return renderData;
    }
    
    //</editor-fold>
}
