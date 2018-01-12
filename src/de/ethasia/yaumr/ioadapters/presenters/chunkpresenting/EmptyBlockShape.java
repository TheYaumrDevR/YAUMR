package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;

/**
 *
 * @author R
 */
public class EmptyBlockShape implements BlockShape {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public Float[] getShapeVertices(Block block) {
        return new Float[0];
    }

    @Override
    public Integer[] getVertexIndices(Block block) {
        return new Integer[0];
    }

    @Override
    public Float[] getNormals(Block block) {
        return new Float[0];
    }

    @Override
    public Float[] getUVCoordinates(Block block) {
        return new Float[0];
    }    
    
    //</editor-fold>
    
}
