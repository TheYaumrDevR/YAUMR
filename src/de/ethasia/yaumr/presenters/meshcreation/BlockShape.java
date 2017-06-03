package de.ethasia.yaumr.presenters.meshcreation;

import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.FacingDirection;

/**
 * Provides rendering data for all kinds of block shapes.
 * 
 * @author R
 */
public interface BlockShape {
    
    public Float[] getShapeVertices(int[] blockPositionInChunk, FacingDirection[] nonObstructedDirections);
    public Integer[] getShapeIndices(int alreadyPlacedVerticesInChunk, FacingDirection[] nonObstructedDirections);
    public Float[] getShapeNormals(FacingDirection[] nonObstructedDirections);
    public Float[] getShapeUVs(FacingDirection[] nonObstructedDirections, BlockTypes blockType);
    public int getAmountOfVertices(FacingDirection[] nonObstructedDirections);
    public void setFacingDirection(FacingDirection facingDirection);
}
