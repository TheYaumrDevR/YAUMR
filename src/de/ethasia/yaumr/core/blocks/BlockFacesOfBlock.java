package de.ethasia.yaumr.core.blocks;

/**
 * Container class for all faces of a block.
 * 
 * @author R
 */
public class BlockFacesOfBlock {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockFaceTypes rightFace;
    private BlockFaceTypes leftFace;
    private BlockFaceTypes frontFace;
    private BlockFaceTypes backFace;
    private BlockFaceTypes topFace;
    private BlockFaceTypes bottomFace;
    
    private boolean rightFaceIsCovering;
    private boolean leftFaceIsCovering;
    private boolean frontFaceIsCovering;
    private boolean backFaceIsCovering;
    private boolean topFaceIsCovering;
    private boolean bottomFaceIsCovering;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BlockFacesOfBlock() {
        rightFace = BlockFaceTypes.RIGHT;
        leftFace = BlockFaceTypes.LEFT;
        frontFace = BlockFaceTypes.FRONT;
        backFace = BlockFaceTypes.BACK;
        topFace = BlockFaceTypes.TOP;
        bottomFace = BlockFaceTypes.BOTTOM;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setRightFace(BlockFaceTypes value) {
        if (null != value && 
                (value == leftFace
                || value == frontFace
                || value == backFace
                || value == topFace
                || value == bottomFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }
        
        rightFace = value;
    }
    
    public void setLeftFace(BlockFaceTypes value) {
        if (null != value && 
                (value == rightFace
                || value == frontFace
                || value == backFace
                || value == topFace
                || value == bottomFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }
        
        leftFace = value;
    }
    
    public void setFrontFace(BlockFaceTypes value) {
        if (null != value && 
                (value == rightFace
                || value == leftFace
                || value == backFace
                || value == topFace
                || value == bottomFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }        
        
        frontFace = value;
    }
    
    public void setBackFace(BlockFaceTypes value) {
        if (null != value && 
                (value == rightFace
                || value == frontFace
                || value == leftFace
                || value == topFace
                || value == bottomFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }        
        
        backFace = value;
    }
    
    public void setTopFace(BlockFaceTypes value) {
        if (null != value && 
                (value == rightFace
                || value == frontFace
                || value == backFace
                || value == leftFace
                || value == bottomFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }
        
        topFace = value;
    }
    
    public void setBottomFace(BlockFaceTypes value) {
        if (null != value && 
                (value == rightFace
                || value == frontFace
                || value == backFace
                || value == topFace
                || value == leftFace)) {
            throw new IllegalArgumentException("The same face type cannot be set to two different positions!");
        }        
        
        bottomFace = value;
    }
    
    public void setRightFaceIsCovering(boolean value) {
        rightFaceIsCovering = value;
    }
    
    public void setLeftFaceIsCovering(boolean value) {
        leftFaceIsCovering = value;
    }
    
    public void setFrontFaceIsCovering(boolean value) {
        frontFaceIsCovering = value;
    }
    
    public void setBackFaceIsCovering(boolean value) {
        backFaceIsCovering = value;
    }
    
    public void setTopFaceIsCovering(boolean value) {
        topFaceIsCovering = value;
    }
    
    public void setBottomFaceIsCovering(boolean value) {
        bottomFaceIsCovering = value;
    }
    
    public BlockFaceTypes getRightFace() {
        return rightFace;
    }
    
    public BlockFaceTypes getLeftFace() {
        return leftFace;
    }
    
    public BlockFaceTypes getFrontFace() {
        return frontFace;
    }
    
    public BlockFaceTypes getBackFace() {
        return backFace;
    }
    
    public BlockFaceTypes getTopFace() {
        return topFace;
    }
    
    public BlockFaceTypes getBottomFace() {
        return bottomFace;
    }
    
    public boolean getRightFaceIsCovering() {
        return rightFaceIsCovering;
    }
    
    public boolean getLeftFaceIsCovering() {
        return leftFaceIsCovering;
    }
    
    public boolean getFrontFaceIsCovering() {
        return frontFaceIsCovering;
    }
    
    public boolean getBackFaceIsCovering() {
        return backFaceIsCovering;
    }
    
    public boolean getTopFaceIsCovering() {
        return topFaceIsCovering;
    }
    
    public boolean getBottomFaceIsCovering() {
        return bottomFaceIsCovering;
    }
    
    //</editor-fold>
}
