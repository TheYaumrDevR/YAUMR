package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * Represents a state of the game, e.g. island editor or ingame.
 * 
 * @author R
 */
public abstract class YaumrGameState extends AbstractAppState implements ScreenController {
    
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
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
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
    }     
    
    //</editor-fold>
}
