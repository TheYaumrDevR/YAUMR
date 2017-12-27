package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainInteractor;

/**
 *
 * @author R
 */
public class RefactoredIslandEditorState extends YaumrGameState implements IslandEditorState {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String MAIN_MENU_PANEL_NAME = "#mainMenu";
    private static final String HELP_PANEL_NAME = "#helpPanel";
    
    private static final String TOGGLE_MAIN_MENU_ACTION_NAME = "toggleMainMenu";
    private static final String TOGGLE_HELP_TEXT_ACTION_NAME = "toggleHelp";
    private static final String TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME = "toggleTerraformingInventory";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ActionListener keyEventHandler;
        
    private Element mainMenu;
    private Element helpPanel;
    
    private IslandEditorStateMainInteractor windowsInteractor;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RefactoredIslandEditorState() {
        keyEventHandler = new ActionListener() {
            
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                switch (name) {
                    case TOGGLE_MAIN_MENU_ACTION_NAME:
                        toggleMainMenu(isPressed);
                        break;
                    case TOGGLE_HELP_TEXT_ACTION_NAME:
                        toggleHelpText(isPressed);
                        break;
                    case TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME:
                        break;
                    default:
                        break;
                }
            }
        };
    }    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JME Overrides">
    
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
    } 

    @Override
    public void bind(Nifty nifty, Screen screen) {
        super.bind(nifty, screen);
        
        windowsInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorStateMainInteractor.class);
        mainMenu = screen.findElementById(MAIN_MENU_PANEL_NAME);
        helpPanel = screen.findElementById(HELP_PANEL_NAME);
    }    

    @Override
    public void onStartScreen() {
        initKeys();
        hideAllVisibleGUIItems();  
        windowsInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorStateMainInteractor.class);
    }

    @Override
    public void onEndScreen() {
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(IslandEditorState.class);
        detachKeys();
    }   
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Interface Implementations">
    
    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(IslandEditorState.class, this);
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0.529f, 0.808f, 0.922f, 1.0f));        
    }     
    
    @Override
    public void showMainMenu() {
        if (null != mainMenu) {
            mainMenu.show();
        }
    }
    
    @Override
    public void showHelpPanel() {
        if (null != helpPanel) {
            helpPanel.show();
        }
    }

    @Override
    public void showTerraformingToolbox() {
    }
    
    @Override
    public void hideAllVisibleGUIItems() {
        if (null != mainMenu) {
            mainMenu.hide();
        }
        
        if (null != helpPanel) {
            helpPanel.hide();
        }         
    }
    
    @Override
    public void activateMovementControls() {
        YaumrGame.getInstance().getFlyByCamera().setEnabled(true);
        YaumrGame.getInstance().getFlyByCamera().setDragToRotate(false);                       
    }
    
    @Override
    public void deactivateMovementControls() {
        YaumrGame.getInstance().getFlyByCamera().setEnabled(false);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="UI Callbacks">
    
    public void gotoGameEntryState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();        
    }

    public void quitGame() {
        System.exit(0);        
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initKeys() {
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_MAIN_MENU_ACTION_NAME, new KeyTrigger(KeyInput.KEY_ESCAPE));
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_HELP_TEXT_ACTION_NAME, new KeyTrigger(KeyInput.KEY_H));        
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME, new KeyTrigger(KeyInput.KEY_I));
        
        YaumrGame.getInstance().getInputManager().addListener(keyEventHandler, 
                new String[] {
                    TOGGLE_MAIN_MENU_ACTION_NAME, 
                    TOGGLE_HELP_TEXT_ACTION_NAME,
                    TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME
                });          
    }
    
    private void detachKeys() {
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_MAIN_MENU_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_HELP_TEXT_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME);
        
        YaumrGame.getInstance().getInputManager().removeListener(keyEventHandler);        
    }
    
    private void toggleMainMenu(boolean toggleKeyIsPressed) {
        if (toggleKeyIsPressed) {
            windowsInteractor.toggleMainMenu();
        }
    }
    
    private void toggleHelpText(boolean toggleKeyIsPressed) {
        if (toggleKeyIsPressed) {
            windowsInteractor.toggleHelpMenu();
        }
    }
    
    //</editor-fold>
}
