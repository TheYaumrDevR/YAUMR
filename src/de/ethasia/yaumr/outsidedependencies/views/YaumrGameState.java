package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithErrorMessages;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.SizeValueType;

/**
 * Represents a state of the game, e.g. island editor or ingame.
 * 
 * @author R
 */
public abstract class YaumrGameState extends AbstractAppState implements ScreenController, AppStateWithErrorMessages {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String ERROR_MESSAGE_WINDOW_CONTAINER_NAME = "#errorMessageWindowContainer";
    private static final String ERROR_MESSAGE_WINDOW_NAME = "#errorMessageWindow";
    private static final String ERROR_MESSAGE_BODY_PANEL_NAME = "#errorMessageWindowBody";
    private static final String ERROR_MESSAGE_HEADER_PANEL_NAME = "#errorMessageWindowHeader";
    private static final String ERROR_MESSAGE_TEXT_NAME = "#errorMessageText";      
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    /** The NiftyJmeDisplay which displays the NiftyGUI for all states. */
    protected static NiftyJmeDisplay niftyDisplay;    
    /** The NiftyJmeDisplay's nifty. */
    protected Nifty nifty;   
    /** The Nifty screen. */
    protected Screen niftyScreen;
    /** The game instance. */
    protected Application app;        
    /** True if the bind method has been called. */
    protected boolean isBound = false;    
    
    protected Element errorMessageWindowContainer;
    protected Element errorMessagePanel;
    protected Element errorMessageBodyPanel;
    protected Element errorMessageHeaderPanel;
    protected Label errorMessageText;      
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    /** 
     * Gets the path of the file where the GUI definition is found.
     * @return The path of the file where the GUI definition is found.
     */
    protected abstract String getNiftyViewFileName();
    
    /** 
     * Gets the name of the Nifty screen on which the GUI lies.
     * @return The name of the Nifty screen on which the GUI lies.
     */
    protected abstract String getNiftyScreenName();
            
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        if (null == YaumrGameState.niftyDisplay) {
            YaumrGameState.niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(), 
                    app.getInputManager(), 
                    app.getAudioRenderer(), 
                    app.getGuiViewPort());
        }
        
        this.app = app;
        nifty = YaumrGameState.niftyDisplay.getNifty();   
        app.getGuiViewPort().addProcessor(YaumrGameState.niftyDisplay);
        
        nifty.fromXml(getNiftyViewFileName(), getNiftyScreenName(), this);
    } 
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        
        app.getGuiViewPort().removeProcessor(YaumrGameState.niftyDisplay);
    }    
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        isBound = true;
        this.nifty = nifty;
        niftyScreen = screen;
        
        errorMessageWindowContainer = screen.findElementById(ERROR_MESSAGE_WINDOW_CONTAINER_NAME);
        errorMessagePanel = screen.findElementById(ERROR_MESSAGE_WINDOW_NAME);
        errorMessageBodyPanel = screen.findElementById(ERROR_MESSAGE_BODY_PANEL_NAME);
        errorMessageHeaderPanel = screen.findElementById(ERROR_MESSAGE_HEADER_PANEL_NAME);
        errorMessageText = screen.findNiftyControl(ERROR_MESSAGE_TEXT_NAME, Label.class);         
    }     
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void showErrorMessage(String message) {
        if (errorMessageWindowElementsArePresent()) {         
            TextRenderer textWidthMeasurer = new TextRenderer(nifty);
            textWidthMeasurer.setFont(nifty.createFont("Interface/Fonts/MSUIGothic.fnt"));
            textWidthMeasurer.setText(message);
            
            int windowXOffset = (errorMessageWindowContainer.getWidth() - textWidthMeasurer.getTextWidth()) / 2;
            errorMessagePanel.setConstraintX(new SizeValue(windowXOffset, SizeValueType.Pixel));
            
            int windowYOffset = (errorMessageWindowContainer.getHeight() - errorMessagePanel.getHeight()) / 2;
            errorMessagePanel.setConstraintY(new SizeValue(windowYOffset, SizeValueType.Pixel));            
            
            errorMessageWindowContainer.layoutElements();
            
            errorMessageText.setText(message);
            
            errorMessagePanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            errorMessageBodyPanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            
            int headerPanelOffset = (errorMessageBodyPanel.getWidth() - errorMessageHeaderPanel.getWidth()) / 2;
            errorMessageHeaderPanel.setConstraintX(new SizeValue(headerPanelOffset, SizeValueType.Pixel));              
            
            errorMessageBodyPanel.layoutElements();
            errorMessagePanel.layoutElements();
            
            errorMessagePanel.show();
        }
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    protected boolean errorMessageWindowElementsArePresent() {
        return null != errorMessageText 
                && null != errorMessagePanel
                && null != errorMessageBodyPanel
                && null != errorMessageHeaderPanel
                && null != errorMessageWindowContainer;
    }      
    
    //</editor-fold>
}
