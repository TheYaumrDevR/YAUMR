package de.ethasia.yaumr.outsidedependencies.tests.mocks;

import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.VisualChunkData;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author rondrejk
 */
public class ChunkRendererMock extends ClassMock implements ChunkRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private VisualChunkData chunkDataPassedToRenderMethod;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        incrementMockCounterForCalledMethod("renderChunk");
        chunkDataPassedToRenderMethod = chunkData;
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public VisualChunkData getChunkDataPassedToRenderMethod() {
        return chunkDataPassedToRenderMethod;
    }
    
    //</editor-fold>
}
