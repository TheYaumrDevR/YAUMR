package de.ethasia.yaumr.outsidedependencies.tests.mocks;

import com.google.common.primitives.Floats;
import java.util.List;

import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.VisualChunkData;
import de.ethasia.yaumr.tests.helpers.ClassMock;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 *
 * @author rondrejk
 */
public class ChunkRendererMock extends ClassMock implements ChunkRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<VisualChunkData> dataOfAllRenderedChunks;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ChunkRendererMock() {
        dataOfAllRenderedChunks = new LinkedList<>();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        incrementMockCounterForCalledMethod("renderChunk");
        dataOfAllRenderedChunks.add(chunkData);
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void resetRenderedData() {
        dataOfAllRenderedChunks.clear();
    }
    
    public Float[] getAllRenderedVertices() {
        List<Float> allVertices = new LinkedList<>();
        
        for (VisualChunkData chunkData : dataOfAllRenderedChunks) {
            for (float vertex : chunkData.getVertices()) {
                allVertices.add(vertex);
            }
        }
        
        return allVertices.toArray(new Float[]{});
    }
    
    public Integer[] getAllRenderedIndices() {
        List<Integer> allIndices = new LinkedList<>();
        
        for (VisualChunkData chunkData : dataOfAllRenderedChunks) {
            for (int index : chunkData.getIndices()) {
                allIndices.add(index);
            }
        }
        
        return allIndices.toArray(new Integer[]{});
    }

    public Float[] getAllRenderedNormals() {
        List<Float> allNormals = new LinkedList<>();
        
        for (VisualChunkData chunkData : dataOfAllRenderedChunks) {
            for (float normal : chunkData.getNormals()) {
                allNormals.add(normal);
            }
        }
        
        return allNormals.toArray(new Float[]{});
    }    
    
    public Float[] getAllRenderedUVCoordinates() {
        List<Float> allUVCoordinates = new LinkedList<>();
        
        for (VisualChunkData chunkData : dataOfAllRenderedChunks) {
            for (float uvCoordinate : chunkData.getUVCoordinates()) {
                allUVCoordinates.add(uvCoordinate);
            }
        }
        
        return allUVCoordinates.toArray(new Float[]{});
    }     
    
    //</editor-fold>
}
