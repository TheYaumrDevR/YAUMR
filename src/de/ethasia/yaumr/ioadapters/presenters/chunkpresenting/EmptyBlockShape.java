package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;

/**
 *
 * @author R
 */
public class EmptyBlockShape extends BlockShape {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void setBlockToCreateDataFrom(Block value) {}
    
    @Override
    public float[] getShapeVertices(int xPositionInChunk, int zPositionInChunk) {
        return new float[0];
    }

    @Override
    public int[] getVertexIndices(int amountOfAlreadySetupBlocksInChunk) {
        return new int[0];
    }

    @Override
    public float[] getNormals() {
        return new float[0];
    }

    @Override
    public float[] getUVCoordinates() {
        return new float[0];
    }    
    
    //</editor-fold>
    
}
