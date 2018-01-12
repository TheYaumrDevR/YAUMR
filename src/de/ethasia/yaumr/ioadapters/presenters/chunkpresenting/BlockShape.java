package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;

/**
 *
 * @author R
 */
public interface BlockShape {
    
    public Float[] getShapeVertices(Block block);
    public Integer[] getVertexIndices(Block block);
    public Float[] getNormals(Block block);
    public Float[] getUVCoordinates(Block block);
}
