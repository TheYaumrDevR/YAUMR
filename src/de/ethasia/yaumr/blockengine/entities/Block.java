package de.ethasia.yaumr.blockengine.entities;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;

/**
 * Represents a block in the game world. Players can place them, remove them
 * and interact with them.
 * 
 * @author R
 */
public class Block implements QuickSelectableEntity {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String QUICK_SELECTION_BLOCK_IMAGES_PATH = "Interface/ImagesGUI/HUD/BlockIcons/";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockTypes blockType;
    private boolean isVisible;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public BlockTypes getBlockType() {
        return blockType;
    }
    
    public void setBlockType(BlockTypes blockType) {
        this.blockType = blockType;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public String getQuickSelectionImagePath() {
        return QUICK_SELECTION_BLOCK_IMAGES_PATH + blockType.toString().toLowerCase() + ".png";
    }    
    
    //</editor-fold>
}
