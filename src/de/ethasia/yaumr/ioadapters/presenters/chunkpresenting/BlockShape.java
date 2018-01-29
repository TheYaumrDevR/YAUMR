package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;

/**
 *
 * @author R
 */
public abstract class BlockShape {
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">

    public abstract void setBlockToCreateDataFrom(Block value);
    public abstract void setBackFaceOfBlockIsCovered(boolean value);
    public abstract void setRightFaceOfBlockIsCovered(boolean value);
    public abstract void setFrontFaceOfBlockIsCovered(boolean value);
    public abstract void setLeftFaceOfBlockIsCovered(boolean value);
    public abstract void setTopFaceOfBlockIsCovered(boolean value);
    public abstract void setBottomFaceOfBlockIsCovered(boolean value);
    
    public abstract float[] getShapeVertices(int xPositionInChunk, int yPositionInChunk);
    public abstract int[] getVertexIndicesForLastCreatedVertices(int amountOfAlreadySetupBlocksInChunk);
    public abstract float[] getNormalsForLastCreatedVertices();
    public abstract float[] getUVCoordinatesForLastCreatedVertices();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    //</editor-fold>
}
