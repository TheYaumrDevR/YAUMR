package de.ethasia.yaumr.presenters.meshes;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the visual data of a Chunk.
 * 
 * @author R
 */
public class ChunkMesh extends Mesh {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final float[] GEOMETRY_NORMALS_DATA = {
        0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
        1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
        0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
       -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
        0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
        0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
    };   
    
    private static final float[] GEOMETRY_UV_DATA = {
        1, 0, 0, 0, 0, 1, 1, 1, // back
        1, 0, 0, 0, 0, 1, 1, 1, // right
        1, 0, 0, 0, 0, 1, 1, 1, // front
        1, 0, 0, 0, 0, 1, 1, 1, // left
        1, 0, 0, 0, 0, 1, 1, 1, // top
        1, 0, 0, 0, 0, 1, 1, 1  // bottom
    };    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private List<Float> vertices;
    private List<Integer> indices;
    private List<Float> normals;
    private List<Float> uvCoordinates;
    private int numberOfVerticesCreated;
    
    private final Vector3f[] blockAxes = {
        Vector3f.UNIT_X.mult(0.25f),
        Vector3f.UNIT_Y.mult(0.25f),
        Vector3f.UNIT_Z.mult(0.25f)
    };
    
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
                        updateBuffers(new int[]{i, j, k});
                    }
                }
            }
        }
        
        updateGeometries();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void updateBuffers(int[] blockPosition) {
        addBlockVertices(blockPosition);
        addBlockIndices();
        addBlockNormals();
        addBlockUVs();        
    }
    
    private void addBlockVertices(int[] blockPosition) {
        // A block has 6 faces. Each has 4 vertices consisting of 3 coordinates.
        Vector3f[] v = createBlockVertices(blockPosition);
        float[] flattenedVertices = new float[] {
            v[0].x, v[0].y, v[0].z, v[1].x, v[1].y, v[1].z, v[2].x, v[2].y, v[2].z, v[3].x, v[3].y, v[3].z, // back
            v[1].x, v[1].y, v[1].z, v[4].x, v[4].y, v[4].z, v[6].x, v[6].y, v[6].z, v[2].x, v[2].y, v[2].z, // right
            v[4].x, v[4].y, v[4].z, v[5].x, v[5].y, v[5].z, v[7].x, v[7].y, v[7].z, v[6].x, v[6].y, v[6].z, // front
            v[5].x, v[5].y, v[5].z, v[0].x, v[0].y, v[0].z, v[3].x, v[3].y, v[3].z, v[7].x, v[7].y, v[7].z, // left
            v[2].x, v[2].y, v[2].z, v[6].x, v[6].y, v[6].z, v[7].x, v[7].y, v[7].z, v[3].x, v[3].y, v[3].z, // top
            v[0].x, v[0].y, v[0].z, v[5].x, v[5].y, v[5].z, v[4].x, v[4].y, v[4].z, v[1].x, v[1].y, v[1].z  // bottom            
        };
        
        for (int i = 0; i < flattenedVertices.length; i++) {
            vertices.add(flattenedVertices[i]);
        }
    }
    
    /**
     * Creates the 8 vertices belonging to the block at the given position.
     * 
     * @param blockPosition The position of the block.
     * @return The 8 vertices belonging to the block.
     */
    private Vector3f[] createBlockVertices(int[] blockPosition) {
        final Vector3f center = new Vector3f(0.5f * blockPosition[0] + 0.25f, 0.5f * blockPosition[1] + 0.25f, 0.5f * blockPosition[2] + 0.25f);
        
        return new Vector3f[] {
                center.subtract(blockAxes[0]).subtractLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).subtractLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).addLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).addLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).subtractLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).subtractLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.add(blockAxes[0]).addLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).addLocal(blockAxes[1]).addLocal(blockAxes[2])
        };
    }    
    
    private void addBlockIndices() {
        int o = numberOfVerticesCreated;
        
        int[] currentBlockIndices = {
            2 + o,  1 + o,  0 + o,  3 + o,  2 + o,  0 + o, // back
            6 + o,  5 + o,  4 + o,  7 + o,  6 + o,  4 + o, // right
            10 + o,  9 + o,  8 + o, 11 + o, 10 + o,  8 + o, // front
            14 + o, 13 + o, 12 + o, 15 + o, 14 + o, 12 + o, // left
            18 + o, 17 + o, 16 + o, 19 + o, 18 + o, 16 + o, // top
            22 + o, 21 + o, 20 + o, 23 + o, 22 + o, 20 + o  // bottom
        };
        
        for (int i = 0; i < currentBlockIndices.length; i++) {
            indices.add(currentBlockIndices[i]);
        }        
        
        numberOfVerticesCreated += 24;        
    }
    
    private void addBlockNormals() {
        for (int i = 0; i < GEOMETRY_NORMALS_DATA.length; i++) {
            normals.add(GEOMETRY_NORMALS_DATA[i]);
        }          
    }
    
    private void addBlockUVs() {
        for (int i = 0; i < GEOMETRY_UV_DATA.length; i++) {
            uvCoordinates.add(GEOMETRY_UV_DATA[i]);
        }          
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
