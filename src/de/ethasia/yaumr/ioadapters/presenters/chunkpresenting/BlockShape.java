package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.Quaternion;
import de.ethasia.yaumr.core.blocks.Vector3;

/**
 *
 * @author R
 */
public abstract class BlockShape {
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">

    public abstract void setBlockToCreateDataFrom(Block value);
    public abstract float[] getShapeVertices(int xPositionInChunk, int yPositionInChunk);
    public abstract int[] getVertexIndices(int amountOfAlreadySetupBlocksInChunk);
    public abstract float[] getNormals();
    public abstract float[] getUVCoordinates();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    protected void rotatePoint(Vector3 point, Quaternion rotation) {
        Vector3 vectorFromQuat = rotation.getVectorFromImaginaryPart();
        float scalarFromQuat = rotation.getRealPart();
        
        Vector3 firstSummand = vectorFromQuat.scaleImmutable(2.0f * vectorFromQuat.dot(point));
        Vector3 secondSummand = point.scaleImmutable(scalarFromQuat * scalarFromQuat - vectorFromQuat.dot(vectorFromQuat));
        Vector3 thirdSummand = vectorFromQuat.cross(point).scale(2.0f * scalarFromQuat);
        
        point.set(firstSummand.add(secondSummand).add(thirdSummand));
    }
    
    //</editor-fold>
}
