package de.ethasia.yaumr.customcontrols.implementations;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.customcontrols.interfaces.DroppableItemBox;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author R
 */
public class DroppableItemBoxControl extends AbstractController implements DroppableItemBox {
    
    //<editor-fold defaultstate="collapsed" desc="Part IDs">
    
    private static final String ITEM_IMAGE_NAME = "#itemImage";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Nifty nifty;
    private Screen screen;
    private ImageRenderer itemImageRenderer;
    
    private String droppableId;
    private Droppable droppable;
    
    private QuickSelectableEntity displayedEntity;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters prmtrs) {
        super.bind(element);
        
        this.nifty = nifty;
        this.screen = screen;
        
        Element itemImage = element.findElementById(ITEM_IMAGE_NAME);   
        
        if (null != itemImage) {
            itemImageRenderer = itemImage.getRenderer(ImageRenderer.class);        
        } 
        
        droppableId = prmtrs.get("droppableID");
        droppable = element.findNiftyControl(droppableId, Droppable.class);
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }   
    
    @Override
    public void setDropFilter(InventoryGridQuickbarDropFilter dropFilter) {
        if (null != droppable) {
            droppable.addFilter(dropFilter);
        }
    }
    
    @Override
    public void setDisplayedItem(QuickSelectableEntity item) {
        displayedEntity = item;
        
        if (null != itemImageRenderer && null != item) {
            itemImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, item.getQuickSelectionImagePath(), false));         
        } 
    }     
    
    @Override
    public QuickSelectableEntity getDisplayedItem() {
        return displayedEntity;
    }
    
    @Override
    public void clearDisplayedItem() {
        displayedEntity = null;
        
        if (null != itemImageRenderer) {
            itemImageRenderer.setImage(null);
        } 
    }
    
    //</editor-fold>
}
