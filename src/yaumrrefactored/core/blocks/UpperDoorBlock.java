package yaumrrefactored.core.blocks;

import yaumrrefactored.core.blocks.DoorBlock;
import yaumrrefactored.core.blocks.BlockTypes;

/**
 *
 * @author R
 */
public class UpperDoorBlock extends DoorBlock {

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public UpperDoorBlock(BlockTypes blockType) {
        super(blockType);
    }    
    
    //</editor-fold>
    
    public void copyStateFromLowerDoorBlock(DoorBlock lowerDoorBlock) {
        
    }
}
