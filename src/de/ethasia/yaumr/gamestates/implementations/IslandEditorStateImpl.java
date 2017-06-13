package de.ethasia.yaumr.gamestates.implementations;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.usecases.interfaces.FallingBlockCellularAutomaton;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.gamestates.interfaces.GameEntryState;
import de.ethasia.yaumr.gamestates.interfaces.IslandEditorState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

/**
 * See IslandEditorState.
 * 
 * @author R
 */
public class IslandEditorStateImpl extends YaumrGameState implements IslandEditorState {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String MAIN_MENU_PANEL_NAME = "#mainMenu";
    private static final String HELP_PANEL_NAME = "#helpPanel";
    
    private static final String TOGGLE_MAIN_MENU_ACTION_NAME = "toggleMainMenu";
    private static final String TOGGLE_HELP_TEXT_ACTION_NAME = "toggleHelp";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ActionListener keyEventHandler;
    
    private BlockPlacementController blockPlacementController;
    private FallingBlockCellularAutomaton fallingBlockHandler;
    private Element mainMenu;
    private Element helpPanel;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public IslandEditorStateImpl() {
        keyEventHandler = new ActionListener() {
            
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (name.equals(TOGGLE_MAIN_MENU_ACTION_NAME)) {
                    toggleMainMenu(isPressed);
                } else if (name.equals(TOGGLE_HELP_TEXT_ACTION_NAME)) {
                    toggleHelpText(isPressed);
                }
            }
        };
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/IslandCreationView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "islandCreationGUI";
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        YaumrGame.getInstance().getFlyByCamera().setEnabled(true);
        YaumrGame.getInstance().getFlyByCamera().setDragToRotate(false);
        
        blockPlacementController = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockPlacementController.class);
        fallingBlockHandler = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(FallingBlockCellularAutomaton.class);
    }   
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        super.bind(nifty, screen);
        
        mainMenu = screen.findElementById(MAIN_MENU_PANEL_NAME);
        helpPanel = screen.findElementById(HELP_PANEL_NAME);
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        blockPlacementController.update(tpf);
        fallingBlockHandler.tick(tpf);
    }

    @Override
    public void onStartScreen() {
        blockPlacementController.initialize(niftyScreen);
        initKeys();
        
        if (null != mainMenu) {
            mainMenu.hide();
        }
        
        if (null != helpPanel) {
            helpPanel.hide();
        }
    }

    @Override
    public void onEndScreen() {
        blockPlacementController.deInitialize();
        detachKeys();
    }

    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0.529f, 0.808f, 0.922f, 1.0f));
    }    
    
    @Override
    public void showHelpPanel() {
        if (null != helpPanel) {
            helpPanel.show();
        }
        
        if (null != mainMenu) {
            mainMenu.hide();
        }
    }

    @Override
    public void gotoGameEntryState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();        
    }

    @Override
    public void quitGame() {
        System.exit(0);
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initKeys() {
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_MAIN_MENU_ACTION_NAME, new KeyTrigger(KeyInput.KEY_ESCAPE));
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_HELP_TEXT_ACTION_NAME, new KeyTrigger(KeyInput.KEY_H));        
        
        YaumrGame.getInstance().getInputManager().addListener(keyEventHandler, 
                new String[] {
                    TOGGLE_MAIN_MENU_ACTION_NAME, 
                    TOGGLE_HELP_TEXT_ACTION_NAME
                });        
    }
    
    private void detachKeys() {
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_MAIN_MENU_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_HELP_TEXT_ACTION_NAME);
        
        YaumrGame.getInstance().getInputManager().removeListener(keyEventHandler);
    }    
    
    private void toggleMainMenu(boolean toggleKeyIsPressed) {
        if (null != mainMenu && toggleKeyIsPressed) {
            if (mainMenu.isVisible()) {
                mainMenu.hide();
                YaumrGame.getInstance().getFlyByCamera().setEnabled(true);
                YaumrGame.getInstance().getFlyByCamera().setDragToRotate(false);                
            } else {
                mainMenu.show();
                YaumrGame.getInstance().getFlyByCamera().setEnabled(false);
            }
        }
    }
    
    private void toggleHelpText(boolean toggleKeyIsPressed) {
        if (null != helpPanel && toggleKeyIsPressed) {
            if (helpPanel.isVisible()) {
                helpPanel.hide();
                YaumrGame.getInstance().getFlyByCamera().setEnabled(true);
                YaumrGame.getInstance().getFlyByCamera().setDragToRotate(false);                 
            } else {
                helpPanel.show();
                
                if (null != mainMenu && mainMenu.isVisible()) {
                    mainMenu.hide();
                }
            }
        }
    }
    
    //</editor-fold>
}
