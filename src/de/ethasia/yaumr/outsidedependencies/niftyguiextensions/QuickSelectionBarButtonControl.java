package de.ethasia.yaumr.outsidedependencies.niftyguiextensions;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBarButton;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;

/**
 * Implementation of QuickSelectionBarButton.
 * 
 * @author R
 */
public class QuickSelectionBarButtonControl extends AbstractController implements QuickSelectionBarButton {

    //<editor-fold defaultstate="collapsed" desc="Part IDs">
    
    private static final String ITEM_IMAGE_NAME = "#itemImage";
    private static final String FRAME_IMAGE_NAME = "#buttonFrame";
    private static final String SELECTED_IMAGE_PATH = "Interface/ImagesGUI/HUD/QuickSelectionBar/SelectionBarSelected.png";
    private static final String UNSELECTED_IMAGE_PATH = "Interface/ImagesGUI/HUD/QuickSelectionBar/SelectionBar.png";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Nifty nifty;
    private Screen screen;
    private ImageRenderer itemImageRenderer;
    private ImageRenderer frameImageRenderer;
    
    private QuickSelectableEntity entityToSelect;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters prmtrs) {
        super.bind(element);
        
        this.nifty = nifty;
        this.screen = screen;
        
        Element itemImage = element.findElementById(ITEM_IMAGE_NAME);
        Element frameImage = element.findElementById(FRAME_IMAGE_NAME);
        
        if (null != itemImage) {
            itemImageRenderer = itemImage.getRenderer(ImageRenderer.class);        
        }
        
        if (null != frameImage) {
            frameImageRenderer = frameImage.getRenderer(ImageRenderer.class); 
        }
    }

    @Override
    public void onStartScreen() {}

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }

    @Override
    public void setSelected() {
        if (null != frameImageRenderer) {
            frameImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, SELECTED_IMAGE_PATH, false));         
        }
    }

    @Override
    public void setUnselected() {
        if (null != frameImageRenderer) {
            frameImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, UNSELECTED_IMAGE_PATH, false));         
        }    
    }

    @Override
    public void setItemToDisplay(ItemDisplayData itemDisplayData) {
        if (null != itemImageRenderer) {
            if (null == entityToSelect) {
                itemImageRenderer.setImage(null);
            } else {
                itemImageRenderer.setImage(nifty.getRenderEngine().createImage(screen, itemDisplayData.getItemImagePath(), false));         
            }
        }         
    }
    
    //</editor-fold>
}
