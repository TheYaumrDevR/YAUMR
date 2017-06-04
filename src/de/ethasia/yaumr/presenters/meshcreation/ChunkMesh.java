package de.ethasia.yaumr.presenters.meshcreation;

import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds the visual data of a Chunk.
 * 
 * @author R
 */
public class ChunkMesh extends Mesh {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private List<Float> vertices;
    private List<Integer> indices;
    private List<Float> normals;
    private List<Float> uvCoordinates;
    private int numberOfVerticesCreated;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void updateRenderingData(Block[][][] chunkBlocks) {
        vertices = new ArrayList<>();
        indices = new ArrayList<>();
        normals = new ArrayList<>();
        uvCoordinates = new ArrayList<>();
        
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 16; k++) {
                    Block block = chunkBlocks[i][j][k];
                    
                    if (block.getBlockType() != BlockTypes.AIR && block.isVisible()) {
                        updateBuffers(new int[]{i, j, k}, block);
                    }
                }
            }
        }
        
        updateGeometries();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBuffers(int[] blockPosition, Block block) {
        addBlockVertices(blockPosition, block);
        addBlockIndices(block);
        addBlockNormals(block);
        addBlockUVs(block);        
    }
    
    private void addBlockVertices(int[] blockPosition, Block block) {
        Float[] flattenedVertices = block.getBlockType().getShape().getShapeVertices(blockPosition, block);
        
        vertices.addAll(Arrays.asList(flattenedVertices));
    } 
    
    private void addBlockIndices(Block block) {
        Integer[] currentBlockIndices = block.getBlockType().getShape().getShapeIndices(numberOfVerticesCreated, block);

        indices.addAll(Arrays.asList(currentBlockIndices));
        numberOfVerticesCreated += block.getBlockType().getShape().getAmountOfVertices(block);        
    }
    
    private void addBlockNormals(Block block) {
        Float[] currentBlockNormals = block.getBlockType().getShape().getShapeNormals(block);
        
        normals.addAll(Arrays.asList(currentBlockNormals));
    }
    
    private void addBlockUVs(Block block) {
        Float[] currentBlockUVs = block.getBlockType().getShape().getShapeUVs(block);
        
        uvCoordinates.addAll(Arrays.asList(currentBlockUVs));
    }    
    
    private void updateGeometries() {
        updateGeometryVertices();
        updateGeometryNormals();
        updateGeometryUVs();
        updateGeometryIndices();
    }
    
    private void updateGeometryVertices() {
        FloatBuffer vertexBuffer = BufferUtils.createVector3Buffer(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            vertexBuffer.put(vertices.get(i));
        }
        
        setBuffer(Type.Position, 3, vertexBuffer);
        updateBound();
    }
    
    private void updateGeometryNormals() {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(normals.size());
        for (int i = 0; i < normals.size(); i++) {
            vertexBuffer.put(normals.get(i));
        }        
        
        setBuffer(Type.Normal, 3, vertexBuffer);
    }    
    
    private void updateGeometryUVs() {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(uvCoordinates.size());
        for (int i = 0; i < uvCoordinates.size(); i++) {
            vertexBuffer.put(uvCoordinates.get(i));
        }        
        
        setBuffer(Type.TexCoord, 2, vertexBuffer);        
    }
    
    private void updateGeometryIndices() {
        IntBuffer vertexBuffer = BufferUtils.createIntBuffer(indices.size());
        for (int i = 0; i < indices.size(); i++) {
            vertexBuffer.put(indices.get(i));
        }        
        
        setBuffer(Type.Index, 3, vertexBuffer);        
    }    
    
    //</editor-fold>
}
