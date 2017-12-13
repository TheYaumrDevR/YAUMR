package yaumrrefactored.core;

/**
 * Represents a block with all of its properties.
 * 
 * @author R
 */
public class Block {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockTypes blockType;
    
    // This is faster than having objects. 
    private BlockFaceTypes rightFace;
    private BlockFaceTypes frontFace;
    private BlockFaceTypes leftFace;
    private BlockFaceTypes backFace;
    private BlockFaceTypes topFace;
    private BlockFaceTypes bottomFace;
    
    private boolean rightFaceIsCovering;
    private boolean frontFaceIsCovering;
    private boolean leftFaceIsCovering;
    private boolean backFaceIsCovering;
    private boolean topFaceIsCovering;
    private boolean bottomFaceIsCovering;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Block() {
        blockType = BlockTypes.AIR;
        initDefaultBlockFaces(BlockTypes.AIR);
    }
    
    public Block(BlockTypes blockType) {
        this.blockType = blockType;
        initDefaultBlockFaces(blockType);      
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public BlockTypes getBlockType() {
        return blockType;
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
    
    public boolean rightFaceIsCovering() {
        return rightFaceIsCovering;
    }
    
    public boolean leftFaceIsCovering() {
        return leftFaceIsCovering;
    }
    
    public boolean frontFaceIsCovering() {
        return frontFaceIsCovering;
    }
    
    public boolean backFaceIsCovering() {
        return backFaceIsCovering;
    }
    
    public boolean topFaceIsCovering() {
        return topFaceIsCovering;
    }
    
    public boolean bottomFaceIsCovering() {
        return bottomFaceIsCovering;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void resetBlockToType(BlockTypes value) {
        blockType = value;
        initDefaultBlockFaces(value);
    }
    
    public void setBlockTo(Block otherBlock) {
        blockType = otherBlock.blockType;
        
        frontFace = otherBlock.getFrontFace();
        leftFace = otherBlock.getLeftFace();
        backFace = otherBlock.getBackFace();
        rightFace = otherBlock.getRightFace();
        topFace = otherBlock.getTopFace();
        bottomFace = otherBlock.getBottomFace();

        frontFaceIsCovering = otherBlock.frontFaceIsCovering();
        leftFaceIsCovering = otherBlock.leftFaceIsCovering();
        backFaceIsCovering = otherBlock.backFaceIsCovering();
        rightFaceIsCovering = otherBlock.rightFaceIsCovering();
        topFaceIsCovering = otherBlock.topFaceIsCovering();
        bottomFaceIsCovering = otherBlock.bottomFaceIsCovering();
    }
    
    public void rotateOnAxisX(AxisRotationValues rotationValue) {
        if (!blockType.canRotateAroundAxisX()) {
            return;
        }
        
        BlockFaceTypes newBackFace = backFace;
        BlockFaceTypes newBottomFace = bottomFace;
        BlockFaceTypes newFrontFace = frontFace;
        BlockFaceTypes newTopFace = topFace;
        
        boolean coversBackFace = backFaceIsCovering;
        boolean coversBottomFace = bottomFaceIsCovering;
        boolean coversFrontFace = frontFaceIsCovering;
        boolean coversTopFace = topFaceIsCovering;
                
        if (rotationValue == AxisRotationValues.MINUS_NINETY) {
            newBackFace = topFace;
            newBottomFace = backFace;
            newFrontFace = bottomFace;
            newTopFace = frontFace;
            
            coversBackFace = topFaceIsCovering;
            coversBottomFace = backFaceIsCovering;
            coversFrontFace = bottomFaceIsCovering;
            coversTopFace = frontFaceIsCovering;
        } else if (rotationValue == AxisRotationValues.NINETY) {
            newFrontFace = topFace;
            newBottomFace = frontFace;
            newBackFace = bottomFace;
            newTopFace = backFace;
            
            coversFrontFace = topFaceIsCovering;
            coversBottomFace = frontFaceIsCovering;
            coversBackFace = bottomFaceIsCovering;
            coversTopFace =  backFaceIsCovering;       
        }
      
        topFace = newTopFace;
        backFace = newBackFace;
        bottomFace = newBottomFace;
        frontFace = newFrontFace;

        topFaceIsCovering = coversTopFace;
        backFaceIsCovering = coversBackFace;
        bottomFaceIsCovering = coversBottomFace;
        frontFaceIsCovering = coversFrontFace;
    }
    
    public void rotateOnAxisY(AxisRotationValues rotationValue) {
        if (!blockType.canRotateAroundAxisY()) {
            return;
        }
        
        BlockFaceTypes newBackFace = backFace;
        BlockFaceTypes newLeftFace = leftFace;
        BlockFaceTypes newFrontFace = frontFace;
        BlockFaceTypes newRightFace = rightFace;
        
        boolean coversBackFace = backFaceIsCovering;
        boolean coversLeftFace = leftFaceIsCovering;
        boolean coversFrontFace = frontFaceIsCovering;
        boolean coversRightFace = rightFaceIsCovering;
        
        if (rotationValue == AxisRotationValues.MINUS_NINETY) {
            newRightFace = backFace;
            newFrontFace = rightFace;
            newLeftFace = frontFace;
            newBackFace = leftFace;
            
            coversBackFace = leftFaceIsCovering;
            coversRightFace = backFaceIsCovering;
            coversFrontFace = rightFaceIsCovering;
            coversLeftFace = frontFaceIsCovering;
        } else if (rotationValue == AxisRotationValues.NINETY) {
            newRightFace = frontFace;
            newFrontFace = leftFace;
            newLeftFace = backFace;
            newBackFace = rightFace;
            
            coversRightFace = frontFaceIsCovering;
            coversFrontFace = leftFaceIsCovering;
            coversLeftFace = backFaceIsCovering;
            coversBackFace = rightFaceIsCovering;
        }
        
        leftFace = newLeftFace;
        backFace = newBackFace;
        rightFace = newRightFace;
        frontFace = newFrontFace; 
        
        leftFaceIsCovering = coversLeftFace;
        backFaceIsCovering = coversBackFace;
        rightFaceIsCovering = coversRightFace;
        frontFaceIsCovering = coversFrontFace; 
    }
    
    public void rotateOnAxisZ(AxisRotationValues rotationValue) {
        if (!blockType.canRotateAroundAxisZ()) {
            return;
        }
        
        BlockFaceTypes newTopFace = topFace;
        BlockFaceTypes newLeftFace = leftFace;
        BlockFaceTypes newBottomFace = bottomFace;
        BlockFaceTypes newRightFace = rightFace;
        
        boolean coversTopFace = topFaceIsCovering;
        boolean coversLeftFace = leftFaceIsCovering;
        boolean coversBottomFace = bottomFaceIsCovering;
        boolean coversRightFace = rightFaceIsCovering;
        
        if (rotationValue == AxisRotationValues.MINUS_NINETY) {
            newTopFace = leftFace;
            newLeftFace = bottomFace;
            newBottomFace = rightFace;
            newRightFace = topFace;
            
            coversTopFace = leftFaceIsCovering;
            coversLeftFace = bottomFaceIsCovering;
            coversBottomFace = rightFaceIsCovering;
            coversRightFace = topFaceIsCovering;
        } else if (rotationValue == AxisRotationValues.NINETY) {
            newTopFace = rightFace;
            newLeftFace = topFace;
            newBottomFace = leftFace;
            newRightFace = bottomFace;
            
            coversTopFace = rightFaceIsCovering;
            coversLeftFace = topFaceIsCovering;
            coversBottomFace = leftFaceIsCovering;
            coversRightFace = bottomFaceIsCovering; 
        }
        
        leftFace = newLeftFace;
        topFace = newTopFace;
        rightFace = newRightFace;
        bottomFace = newBottomFace;  
        
        leftFaceIsCovering = coversLeftFace;
        topFaceIsCovering = coversTopFace;
        rightFaceIsCovering = coversRightFace;
        bottomFaceIsCovering = coversBottomFace;           
    }
    
    public boolean isAffectedByAutomatonType(String automatonTypeName) {
        if (null == blockType.getAffectingAutomatonNames().get(automatonTypeName))
        {
            return false;
        }
        
        return blockType.getAffectingAutomatonNames().get(automatonTypeName);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initDefaultBlockFaces(BlockTypes blockType) {
        BlockFacesOfBlock faces = blockType.getFacesWithCoveringData();
        
        rightFace = faces.getRightFace();
        leftFace = faces.getLeftFace();
        frontFace = faces.getFrontFace();
        backFace = faces.getBackFace();
        topFace = faces.getTopFace();
        bottomFace = faces.getBottomFace();

        rightFaceIsCovering = faces.getRightFaceIsCovering();
        leftFaceIsCovering = faces.getLeftFaceIsCovering();
        frontFaceIsCovering = faces.getFrontFaceIsCovering();
        backFaceIsCovering = faces.getBackFaceIsCovering();
        topFaceIsCovering = faces.getTopFaceIsCovering();
        bottomFaceIsCovering = faces.getBottomFaceIsCovering();
    }
    
    //</editor-fold>
}
