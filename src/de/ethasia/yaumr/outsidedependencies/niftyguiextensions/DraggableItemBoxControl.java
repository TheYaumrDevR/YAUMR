package de.ethasia.yaumr.outsidedependencies.niftyguiextensions;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.DraggableItemBox;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;

/**
 * 
 * @author R
 */
public class DraggableItemBoxControl extends AbstractController implements DraggableItemBox {

    //<editor-fold defaultstate="collapsed" desc="Part IDs">
    
    private static final String ITEM_IMAGE_NAME = "#itemImage";
    private static final String DUPLICATE_ITEM_IMAGE_NAME = "#itemImageDuplicate";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Nifty nifty;
    private Screen screen;
    private ImageRenderer itemImageRenderer;
    private ImageRenderer duplicateItemImageRenderer;
    
    private QuickSelectableEntity entityToSelect;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters prmtrs) {
        super.bind(element);
        
        this.nifty = nifty;
        this.screen = screen;
        
        Element itemImage = element.findElementById(ITEM_IMAGE_NAME);   
        Element duplicateImage = element.findElementById(DUPLICATE_ITEM_IMAGE_NAME); 
        
        if (null != itemImage) {
            itemImageRenderer = itemImage.getRenderer(ImageRenderer.class);        
        }    
        
        if (null != duplicateImage) {
            duplicateItemImageRenderer = duplicateImage.getRenderer(ImageRenderer.class);        
        }         
    }

    @Override
    public void onStartScreen() {}

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }
    
    @Override
    public QuickSelectableEntity getDisplayedItem() {
        return entityToSelect;
    }

    @Override
    public void setDisplayedItem(QuickSelectableEntity item) {
        entityToSelect = item;
        
        if (null != itemImageRenderer && null != entityToSelect) {
            itemImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, entityToSelect.getQuickSelectionImagePath(), false));         
        } 
        
        if (null != duplicateItemImageRenderer && null != entityToSelect) {
            duplicateItemImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, entityToSelect.getQuickSelectionImagePath(), false));         
        }         
    }   
    
    //</editor-fold>   
}
