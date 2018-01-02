package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.debug.WireBox;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainInteractor;
import de.ethasia.yaumr.ioadapters.interfaces.BlockInteractionIndicatorPresenter;

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
    
    private static final String BLOCK_INTERACTION_INDICATOR_NAME = "BlockInteractionIndicator";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ActionListener keyEventHandler;
        
    private Element mainMenu;
    private Element helpPanel;
    private Geometry blockInteractionIndicator;
    
    private IslandEditorStateMainInteractor windowsInteractor;
    private BlockInteractionIndicatorPresenter blockInteractionIndicatorPresenter;
    
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
    public void update(float tpf) {
        if (null != blockInteractionIndicatorPresenter) {
            Camera camera = YaumrGame.getInstance().getCamera();  
            Vector3f pointingPoint = camera.getLocation().add(camera.getDirection().normalize().mult(2.0f));
            blockInteractionIndicatorPresenter.presentPointingIndicator(pointingPoint.x, pointingPoint.y, pointingPoint.z);            
        }
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
        blockInteractionIndicatorPresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(BlockInteractionIndicatorPresenter.class);
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
        IslandManipulationFacade islandManipulationFacade = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandManipulationFacade.class);
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(IslandEditorState.class, this);
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0.529f, 0.808f, 0.922f, 1.0f));
        
        float camXZLocation = 0.5f * (islandManipulationFacade.getIslandEdgeLengthInBlocks() / 2);
        YaumrGame.getInstance().getCamera().setLocation(new Vector3f(camXZLocation, 0, camXZLocation));
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
    
    @Override
    public void displayBlockPointingIndicator(InteractionVector position) {
        if (null == blockInteractionIndicator) {
            blockInteractionIndicator = createBlockPointingIndicator();
        }
        
        YaumrGame.getInstance().getRootNode().attachChild(blockInteractionIndicator);
        blockInteractionIndicator.setLocalTranslation(position.getX(), position.getY(), position.getZ());
    }

    @Override
    public void removeBlockPointingIndicator() {
        YaumrGame.getInstance().getRootNode().detachChildNamed(BLOCK_INTERACTION_INDICATOR_NAME);
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
    
    private Geometry createBlockPointingIndicator() {
        WireBox wireCube = new WireBox(0.25f, 0.25f, 0.25f);
        Geometry geometry = new Geometry(BLOCK_INTERACTION_INDICATOR_NAME, wireCube );
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Black);
        geometry.setMaterial(material);
        
        return geometry;
    }
    
    //</editor-fold>
}
