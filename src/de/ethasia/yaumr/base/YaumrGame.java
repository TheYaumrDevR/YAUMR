package de.ethasia.yaumr.base;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithErrorMessages;

import de.ethasia.yaumr.outsidedependencies.views.YaumrGameState;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;

/**
 * This class is the entry point of the application. All important things
 * are initialized here.
 * 
 * @author R
 */
public class YaumrGame extends SimpleApplication {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final String GAME_NAME_BASE = "YAUMR";
    private static final String GAME_NAME_WITH_VERSION = "YAUMR Alpha 0.1";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    /** The instance of the game. Stored for global access. */
    private static YaumrGame gameInstance;    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    /** The AbstractAppState the game is currently in. */
    private YaumrGameState currentGameState;     
    /** Provides a centralized location to register instances/implementations to Interfaces and retrieve them. */
    private ClassInstanceContainer classInstanceContainer;    
    
    //</editor-fold>

    public static void main(String[] args) {
        gameInstance = new YaumrGame();
        
        gameInstance.setShowSettings(false);
        
        AppSettings gameSettings = new AppSettings(true);
        gameSettings.setTitle(YaumrGame.GAME_NAME_WITH_VERSION);
        gameSettings.setResolution(1280, 720);
        
        gameInstance.setSettings(gameSettings);        
        
        gameInstance.start();
    }

    //<editor-fold defaultstate="collapsed" desc="SimpleApplication Overrides">
    
    @Override
    public void simpleInitApp() {
        /* Disable the game being closable by pressing escape. */
        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);   
        
        setDisplayStatView(false);
        setDisplayFps(false);    
        
        getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();
    }

    @Override
    public void simpleUpdate(float tpf) {

    }

    @Override
    public void simpleRender(RenderManager rm) {

    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    /**
     * Retrieves the game instance.
     * 
     * @return The game instance.
     */
    public static YaumrGame getInstance() {
        if (null == YaumrGame.gameInstance) {
            YaumrGame.gameInstance = new YaumrGame();
        }
        
        return YaumrGame.gameInstance;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public ClassInstanceContainer getClassInstanceContainer() {
        if (null == classInstanceContainer) {
            classInstanceContainer = new ClassInstanceContainer();
            classInstanceContainer.performAutoWiring();
        }
        
        return classInstanceContainer;
    }    
    
    /**
     * Changes the state of the game to the given YaumrGameState.
     * 
     * @param newGameState The new state of the game.
     */
    public void setGameState(YaumrGameState newGameState) {
        if (null != currentGameState) {
            stateManager.detach(currentGameState);
        }
        
        stateManager.attach(newGameState);
        currentGameState = newGameState;
        
        ClassInstanceContainer dependencyResolver = getClassInstanceContainer();
        dependencyResolver.removeSingletonInstance(AppStateWithErrorMessages.class);
        
        if (newGameState instanceof AppStateWithErrorMessages) {
            dependencyResolver.registerSingletonInstance(AppStateWithErrorMessages.class, (AppStateWithErrorMessages)newGameState);
        }
    }    
    
    //</editor-fold>
}
