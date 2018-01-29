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
    public void setBackFaceOfBlockIsCovered(boolean value) {}
    
    @Override
    public void setRightFaceOfBlockIsCovered(boolean value) {}
    
    @Override
    public void setFrontFaceOfBlockIsCovered(boolean value) {}
    
    @Override
    public void setLeftFaceOfBlockIsCovered(boolean value) {}
    
    @Override
    public void setTopFaceOfBlockIsCovered(boolean value) {}
    
    @Override
    public void setBottomFaceOfBlockIsCovered(boolean value) {}  
    
    @Override
    public float[] getShapeVertices(int xPositionInChunk, int zPositionInChunk) {
        return new float[0];
    }

    @Override
    public int[] getVertexIndicesForLastCreatedVertices(int amountOfAlreadySetupBlocksInChunk) {
        return new int[0];
    }

    @Override
    public float[] getNormalsForLastCreatedVertices() {
        return new float[0];
    }

    @Override
    public float[] getUVCoordinatesForLastCreatedVertices() {
        return new float[0];
    }    
    
    //</editor-fold>
    
}
