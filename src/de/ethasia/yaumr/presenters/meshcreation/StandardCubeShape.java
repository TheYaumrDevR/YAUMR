package de.ethasia.yaumr.presenters.meshcreation;

import com.jme3.math.Vector3f;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.FacingDirection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A shape which represents the standard 6 faced textured cube/block.
 * 
 * @author R
 */
public class StandardCubeShape implements BlockShape {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static StandardCubeShape instance;
    private FacingDirection facingDirection;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private StandardCubeShape() {}
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public Float[] getShapeVertices(int[] blockPositionInChunk, Block block) {
        Vector3f[] blockAxes = {
            Vector3f.UNIT_X.mult(0.25f),
            Vector3f.UNIT_Y.mult(0.25f),
            Vector3f.UNIT_Z.mult(0.25f)
        };
        
        final Vector3f center = new Vector3f(0.5f * blockPositionInChunk[0] + 0.25f, 0.5f * blockPositionInChunk[1] + 0.25f, 0.5f * blockPositionInChunk[2] + 0.25f);
        
        Vector3f[] v = new Vector3f[] {
                center.subtract(blockAxes[0]).subtractLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).subtractLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).addLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).addLocal(blockAxes[1]).subtractLocal(blockAxes[2]),
                center.add(blockAxes[0]).subtractLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).subtractLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.add(blockAxes[0]).addLocal(blockAxes[1]).addLocal(blockAxes[2]),
                center.subtract(blockAxes[0]).addLocal(blockAxes[1]).addLocal(blockAxes[2])
        };      
        
        List<Float> vertices = new ArrayList<>();
        for (FacingDirection direction : FacingDirection.values()) {
            if (!block.isDirectionObstructed(direction)) {
                switch (direction) {
                    case BACK:
                        vertices.addAll(Arrays.asList(v[0].x, v[0].y, v[0].z, v[1].x, v[1].y, v[1].z, v[2].x, v[2].y, v[2].z, v[3].x, v[3].y, v[3].z));
                        break;
                    case RIGHT:
                        vertices.addAll(Arrays.asList(v[1].x, v[1].y, v[1].z, v[4].x, v[4].y, v[4].z, v[6].x, v[6].y, v[6].z, v[2].x, v[2].y, v[2].z));
                        break;                    
                    case FRONT:
                        vertices.addAll(Arrays.asList(v[4].x, v[4].y, v[4].z, v[5].x, v[5].y, v[5].z, v[7].x, v[7].y, v[7].z, v[6].x, v[6].y, v[6].z));
                        break;                      
                    case LEFT:
                        vertices.addAll(Arrays.asList(v[5].x, v[5].y, v[5].z, v[0].x, v[0].y, v[0].z, v[3].x, v[3].y, v[3].z, v[7].x, v[7].y, v[7].z));
                        break;                      
                    case TOP:
                        vertices.addAll(Arrays.asList(v[2].x, v[2].y, v[2].z, v[6].x, v[6].y, v[6].z, v[7].x, v[7].y, v[7].z, v[3].x, v[3].y, v[3].z));
                        break;                     
                    case BOTTOM:
                        vertices.addAll(Arrays.asList(v[0].x, v[0].y, v[0].z, v[5].x, v[5].y, v[5].z, v[4].x, v[4].y, v[4].z, v[1].x, v[1].y, v[1].z));
                        break;                       
                }                
            }
        }
        
        Float[] returnedVertices = new Float[vertices.size()];
        return vertices.toArray(returnedVertices);
    }

    @Override
    public Integer[] getShapeIndices(int alreadyPlacedVerticesInChunk, Block block) {
        int o = alreadyPlacedVerticesInChunk;
        int offIdx = 0;
        
        List<Integer> indices = new ArrayList<>();
        for (FacingDirection direction : FacingDirection.values()) {
            if (!block.isDirectionObstructed(direction)) {
                indices.addAll(Arrays.asList(2 + offIdx + o,  1 + offIdx + o,  0 + offIdx + o,  3 + offIdx + o,  2 + offIdx + o,  0 + offIdx + o));
                offIdx += 4;              
            }
        }        
        
        Integer[] returnedIndices = new Integer[indices.size()];
        return indices.toArray(returnedIndices);
    }

    @Override
    public Float[] getShapeNormals(Block block) {
        List<Float> normals = new ArrayList<>();
        for (FacingDirection direction : FacingDirection.values()) {
            if (!block.isDirectionObstructed(direction)) {
                switch (direction) {
                    case BACK:
                        normals.addAll(Arrays.asList(0f,  0f, -1f,  0f,  0f, -1f,  0f,  0f, -1f,  0f,  0f, -1f));
                        break;
                    case RIGHT:
                        normals.addAll(Arrays.asList(1f,  0f,  0f,  1f,  0f,  0f,  1f,  0f,  0f,  1f,  0f,  0f));
                        break;                    
                    case FRONT:
                        normals.addAll(Arrays.asList(0f,  0f,  1f,  0f,  0f,  1f,  0f,  0f,  1f,  0f,  0f,  1f));
                        break;                      
                    case LEFT:
                        normals.addAll(Arrays.asList(-1f,  0f,  0f, -1f,  0f,  0f, -1f,  0f,  0f, -1f,  0f,  0f));
                        break;                      
                    case TOP:
                        normals.addAll(Arrays.asList(0f,  1f,  0f,  0f,  1f,  0f,  0f,  1f,  0f,  0f,  1f,  0f));
                        break;                     
                    case BOTTOM:
                        normals.addAll(Arrays.asList(0f, -1f,  0f,  0f, -1f,  0f,  0f, -1f,  0f,  0f, -1f,  0f));
                        break;                       
                }    
            }
        }
        
        Float[] returnedNormals = new Float[normals.size()];
        return normals.toArray(returnedNormals);
    }
    
    @Override
    public Float[] getShapeUVs(Block block) {
        List<Float> uvCoordinates = new ArrayList<>();
        float[] allUVCoordinates = block.getBlockType().getUVCoordinates();
        
        for (FacingDirection direction : FacingDirection.values()) {
            if (!block.isDirectionObstructed(direction)) {
                switch (direction) {
                    case BACK:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[0], allUVCoordinates[1], allUVCoordinates[2], allUVCoordinates[3], allUVCoordinates[4], allUVCoordinates[5], allUVCoordinates[6], allUVCoordinates[7]));
                        break;
                    case RIGHT:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[8], allUVCoordinates[9], allUVCoordinates[10], allUVCoordinates[11], allUVCoordinates[12], allUVCoordinates[13], allUVCoordinates[14], allUVCoordinates[15]));
                        break;                    
                    case FRONT:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[16], allUVCoordinates[17], allUVCoordinates[18], allUVCoordinates[19], allUVCoordinates[20], allUVCoordinates[21], allUVCoordinates[22], allUVCoordinates[23]));
                        break;                      
                    case LEFT:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[24], allUVCoordinates[25], allUVCoordinates[26], allUVCoordinates[27], allUVCoordinates[28], allUVCoordinates[29], allUVCoordinates[30], allUVCoordinates[31]));
                        break;                      
                    case TOP:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[32], allUVCoordinates[33], allUVCoordinates[34], allUVCoordinates[35], allUVCoordinates[36], allUVCoordinates[37], allUVCoordinates[38], allUVCoordinates[39]));
                        break;                     
                    case BOTTOM:
                        uvCoordinates.addAll(Arrays.asList(allUVCoordinates[40], allUVCoordinates[41], allUVCoordinates[42], allUVCoordinates[43], allUVCoordinates[44], allUVCoordinates[45], allUVCoordinates[46], allUVCoordinates[47]));
                        break;                       
                }                
            }
        }  
        
        Float[] returnedUVs = new Float[uvCoordinates.size()];
        return uvCoordinates.toArray(returnedUVs);
    }
    
    @Override
    public int getAmountOfVertices(Block block) {
        int amountOfVertices = 0;
        for (FacingDirection direction : FacingDirection.values()) {
            if (!block.isDirectionObstructed(direction)) {
                amountOfVertices += 4;
            }
        }    
        
        return amountOfVertices;
    }

    @Override
    public void setFacingDirection(FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
    }    
    
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static StandardCubeShape getInstance() {
        if (null == instance) {
            instance = new StandardCubeShape();
        }
        
        return instance;
    }
    
    //</editor-fold>
}
