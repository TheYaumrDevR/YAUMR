package de.ethasia.yaumr.presenters.meshcreation;

import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.FacingDirection;

/**
 * Provides rendering data for all kinds of block shapes.
 * 
 * @author R
 */
public interface BlockShape {
    
    public Float[] getShapeVertices(int[] blockPositionInChunk, Block block);
    public Integer[] getShapeIndices(int alreadyPlacedVerticesInChunk, Block block);
    public Float[] getShapeNormals(Block block);
    public Float[] getShapeUVs(Block block);
    public int getAmountOfVertices(Block block);
    public void setFacingDirection(FacingDirection facingDirection);
}
