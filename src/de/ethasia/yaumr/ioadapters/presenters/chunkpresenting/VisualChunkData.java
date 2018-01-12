package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import java.util.List;

/**
 * Holds all the data needed to render a chunk.
 * 
 * @author R
 */
public class VisualChunkData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int chunkX;
    private int chunkZ;
    
    private List<Float> vertices;
    private List<Integer> vertexIndices;
    private List<Float> faceNormals;
    private List<Float> uvCoordinates;
    
    //</editor-fold>
}
