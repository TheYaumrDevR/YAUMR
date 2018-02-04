package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

/**
 * Holds all the data needed to render a chunk.
 * 
 * @author R
 */
public class VisualChunkData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private float[][] verticesOfBlocks;
    private int[][] indicesOfBlocks;
    private float[][] normalsOfBlocks;
    private float[][] uvCoordinatesOfBlocks;
    
    private int currentVertexAmount;
    private int currentIndexAmount;
    private int currentNormalsAmount;
    private int currentUVAmount;
    
    private int numberOfTimesVerticesAdded;
    private int numberOfTimesIndicesAdded;
    private int numberOfTimesNormalsAdded;
    private int numberOfTimesUVAdded;
    
    private int chunkX;
    private int chunkZ;
    
    private float[] allVerticesFlattened;
    private int[] allIndicesFlattened;
    private float[] allNormalsFlattened;
    private float[] allUVCoordinatesFlattened;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public float[] getVertices() {
        return allVerticesFlattened;
    }
    
    public int[] getIndices() {
        return allIndicesFlattened;
    }
    
    public float[] getNormals() {
        return allNormalsFlattened;
    }
    
    public float[] getUVCoordinates() {
        return allUVCoordinatesFlattened;
    }    
    
    public void setPosition(int x, int z) {
        chunkX = x;
        chunkZ = z;
    }

    public int getX() {
        return chunkX;
    }
    
    public int getZ() {
        return chunkZ;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setUpWithNumberOfBlocksInChunk(int numberOfBlocksInChunk) {
        verticesOfBlocks = new float[numberOfBlocksInChunk][];
        indicesOfBlocks = new int[numberOfBlocksInChunk][];
        normalsOfBlocks = new float[numberOfBlocksInChunk][];
        uvCoordinatesOfBlocks = new float[numberOfBlocksInChunk][];
    }
    
    public void addVerticesToTemporaryBuffer(float[] vertices) {
        verticesOfBlocks[numberOfTimesVerticesAdded] = vertices;
        currentVertexAmount += vertices.length;
        numberOfTimesVerticesAdded++;
    }
    
    public void addIndicesToTemporaryBuffer(int[] indices) {
        indicesOfBlocks[numberOfTimesIndicesAdded] = indices;
        currentIndexAmount += indices.length;   
        numberOfTimesIndicesAdded++;
    }
    
    public void addNormalsToTemporaryBuffer(float[] normals) {
        normalsOfBlocks[numberOfTimesNormalsAdded] = normals;
        currentNormalsAmount += normals.length;
        numberOfTimesNormalsAdded++;
    }
    
    public void addUVCoordinatesToTemporaryBuffer(float[] uvCoordinates) {
        uvCoordinatesOfBlocks[numberOfTimesUVAdded] = uvCoordinates;
        currentUVAmount += uvCoordinates.length;
        numberOfTimesUVAdded++;
    }
    
    public void buildChunkData() {
        flattenVertices();
        flattenIndices();
        flattenNormals();
        flattenUVs();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void flattenVertices() {
        allVerticesFlattened = new float[currentVertexAmount];
        int k = 0;  
        
        for (int i = 0; i < verticesOfBlocks.length; i++) {
            for (int j = 0; j < verticesOfBlocks[i].length; j++) {
                allVerticesFlattened[k] = verticesOfBlocks[i][j];
                k++;
            }
        }        
    }
    
    private void flattenIndices() {
        allIndicesFlattened = new int[currentIndexAmount];
        int k = 0;  
        
        for (int i = 0; i < indicesOfBlocks.length; i++) {
            for (int j = 0; j < indicesOfBlocks[i].length; j++) {
                allIndicesFlattened[k] = indicesOfBlocks[i][j];
                k++;
            }
        }        
    }
    
    private void flattenNormals() {
        allNormalsFlattened = new float[currentNormalsAmount];
        int k = 0;  
        
        for (int i = 0; i < normalsOfBlocks.length; i++) {
            for (int j = 0; j < normalsOfBlocks[i].length; j++) {
                allNormalsFlattened[k] = normalsOfBlocks[i][j];
                k++;
            }
        }        
    }    
    
    private void flattenUVs() {
        allUVCoordinatesFlattened = new float[currentUVAmount];
        int k = 0;  
        
        for (int i = 0; i < uvCoordinatesOfBlocks.length; i++) {
            for (int j = 0; j < uvCoordinatesOfBlocks[i].length; j++) {
                allUVCoordinatesFlattened[k] = uvCoordinatesOfBlocks[i][j];
                k++;
            }
        }        
    }
    
    //</editor-fold>
}
