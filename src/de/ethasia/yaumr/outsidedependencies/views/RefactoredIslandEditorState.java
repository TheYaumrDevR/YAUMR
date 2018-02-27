package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.debug.WireBox;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateSetupInteractor;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.ioadapters.interfaces.BlockInteractionIndicatorPresenter;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.QuickSelectionBarControl;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.InventoryGrid;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsInteractor;
import de.ethasia.yaumr.interactors.interfaces.TimedUpdateInteractor;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.outsidedependencies.renderers.RootNodeProvider;

/**
 *
 * @author R
 */
public class RefactoredIslandEditorState extends YaumrGameState implements IslandEditorState {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String MAIN_MENU_PANEL_NAME = "#mainMenu";
    private static final String HELP_PANEL_NAME = "#helpPanel";
    private static final String BOTTOM_QUICKSELECTION_BAR_NAME = "#bottomQuickSelectionBar";  
    private static final String TOOLGRID_NAME = "#itemSelectionGrid";
    
    private static final String TOGGLE_MAIN_MENU_ACTION_NAME = "toggleMainMenu";
    private static final String TOGGLE_HELP_TEXT_ACTION_NAME = "toggleHelp";
    private static final String TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME = "toggleTerraformingInventory";
    private static final String SELECT_QUICKBAR_ITEM_ACTION_NAME = "selectItem";
    private static final String EXECUTE_PRIMARY_ACTION_EVENT_NAME = "executePrimaryAction";    
    
    private static final String BLOCK_INTERACTION_INDICATOR_NAME = "BlockInteractionIndicator";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ActionListener keyEventHandler;
        
    private Element mainMenu;
    private Element helpPanel;
    private Geometry blockInteractionIndicator;
    private QuickSelectionBar quickSelectionBar;
    private InventoryGrid toolsSelectionGrid;
    
    private IslandEditorStateMainInteractor windowsInteractor;
    private IslandEditorStateSetupInteractor setupInteractor;
    private TerraformingToolsInteractor terraformingToolsSelector;
    private TimedUpdateInteractor timedUpdateInteractor;
    private BlockInteractionIndicatorPresenter blockInteractionIndicatorPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RefactoredIslandEditorState() {
        keyEventHandler = new ActionListener() {
            
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (name.startsWith(SELECT_QUICKBAR_ITEM_ACTION_NAME)) {
                    selectQuickbarItem(name, isPressed);
                } else {
                    switch (name) {
                        case TOGGLE_MAIN_MENU_ACTION_NAME:
                            toggleMainMenu(isPressed);
                            break;
                        case TOGGLE_HELP_TEXT_ACTION_NAME:
                            toggleHelpText(isPressed);
                            break;
                        case TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME:
                            break;
                        case EXECUTE_PRIMARY_ACTION_EVENT_NAME:
                            executePrimaryAction(isPressed);
                            break;
                        default:
                            break;
                    }                    
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
            Vector3f pointingPoint = getCursorPointingLocation();
            blockInteractionIndicatorPresenter.presentPointingIndicator(pointingPoint.x, pointingPoint.y, pointingPoint.z);            
        }
        
        if (null != timedUpdateInteractor) {
            timedUpdateInteractor.tick(tpf);
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
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        windowsInteractor = dependencyResolver.getImplementationInstance(IslandEditorStateMainInteractor.class);
        setupInteractor = dependencyResolver.getImplementationInstance(IslandEditorStateSetupInteractor.class);
        blockInteractionIndicatorPresenter = dependencyResolver.getImplementationInstance(BlockInteractionIndicatorPresenter.class);
        mainMenu = screen.findElementById(MAIN_MENU_PANEL_NAME);
        helpPanel = screen.findElementById(HELP_PANEL_NAME);
        quickSelectionBar = screen.findNiftyControl(BOTTOM_QUICKSELECTION_BAR_NAME, QuickSelectionBar.class);    
        toolsSelectionGrid = screen.findNiftyControl(TOOLGRID_NAME, InventoryGrid.class);
        
        setupInteractor.setupInventoryInteractorsForIslandEditorState();
        terraformingToolsSelector = dependencyResolver.getSingletonInstance(TerraformingToolsInteractor.class);
    }    

    @Override
    public void onStartScreen() {
        initKeys();
        hideAllVisibleGUIItems();  
        terraformingToolsSelector.setSelectedToolIndex(0);
        
        if (null != toolsSelectionGrid) {
            toolsSelectionGrid.hideInventoryGrid();
        }       
        
        initRenderers();
        timedUpdateInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(TimedUpdateInteractor.class);
    }

    @Override
    public void onEndScreen() {
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(IslandEditorState.class);
        removeRenderers();
        timedUpdateInteractor = null;
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
    
    @Override
    public void selectItemOnQuickbar(int itemIndex) {
        if (null != quickSelectionBar) {
            quickSelectionBar.highlightSelectionAtIndex(itemIndex);
        }
    }
    
    @Override
    public void showItemsOnQuickbar(ItemDisplayData[] displayData) {
        if (null != quickSelectionBar) {
            quickSelectionBar.setItemDisplayData(displayData);
        }
    }     
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="UI Callbacks">
    
    public void gotoGameEntryState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();        
    }

    public void quitGame() {
        System.exit(0);        
    }    
    
    public void selectQuickbarItem(String uiEventName, boolean keyIsPressed) {
        if (keyIsPressed) {
            if (null != quickSelectionBar) {
                Integer itemIndex = quickSelectionBar.getItemIndexForKeyActionName(uiEventName);
                terraformingToolsSelector.setSelectedToolIndex(itemIndex);
            }
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initKeys() {
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_MAIN_MENU_ACTION_NAME, new KeyTrigger(KeyInput.KEY_ESCAPE));
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_HELP_TEXT_ACTION_NAME, new KeyTrigger(KeyInput.KEY_H));        
        YaumrGame.getInstance().getInputManager().addMapping(TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME, new KeyTrigger(KeyInput.KEY_I));
        
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_1));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_2));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_3));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_4));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_5));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_SIXTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_6));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_SEVENTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_7));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_EIGHTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_8));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_NINTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_9));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_TENTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_0));  
        
        YaumrGame.getInstance().getInputManager().addMapping(EXECUTE_PRIMARY_ACTION_EVENT_NAME, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        
        YaumrGame.getInstance().getInputManager().addListener(keyEventHandler, 
                new String[] {
                    TOGGLE_MAIN_MENU_ACTION_NAME, 
                    TOGGLE_HELP_TEXT_ACTION_NAME,
                    TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME,
                    EXECUTE_PRIMARY_ACTION_EVENT_NAME,
                    QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_SIXTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_SEVENTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_EIGHTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_NINTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_TENTH_ITEM_KEYACTION                    
                });          
    }
    
    private void detachKeys() {
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_MAIN_MENU_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_HELP_TEXT_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(EXECUTE_PRIMARY_ACTION_EVENT_NAME);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_SIXTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_SEVENTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_EIGHTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_NINTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_TENTH_ITEM_KEYACTION);        
        
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
    
    private void executePrimaryAction(boolean isPressed) {
        if (null != terraformingToolsSelector && isPressed) {
            if (null != blockInteractionIndicator) {
                float x = blockInteractionIndicator.getLocalTranslation().x;
                float y = blockInteractionIndicator.getLocalTranslation().y;
                float z = blockInteractionIndicator.getLocalTranslation().z;
                InteractionVector interactionPosition = new InteractionVector(x, y, z);  
                
                terraformingToolsSelector.executeActionOfCurrentlySelectedTool(interactionPosition);
            }
        }
    }
    
    private Vector3f getCursorPointingLocation() {
        Camera camera = YaumrGame.getInstance().getCamera();  
        return camera.getLocation().add(camera.getDirection().normalize().mult(2.0f));        
    }
    
    private Geometry createBlockPointingIndicator() {
        WireBox wireCube = new WireBox(0.25f, 0.25f, 0.25f);
        Geometry geometry = new Geometry(BLOCK_INTERACTION_INDICATOR_NAME, wireCube );
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Black);
        geometry.setMaterial(material);
        
        return geometry;
    }
    
    private void initRenderers() {
        ChunkRenderer chunkRenderer = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ChunkRenderer.class);
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(ChunkRenderer.class, chunkRenderer);

        if (chunkRenderer instanceof RootNodeProvider) {
            YaumrGame.getInstance().getRootNode().attachChild(((RootNodeProvider)chunkRenderer).getRootNode());
        }
    }
    
    private void removeRenderers() {
        ChunkRenderer chunkRenderer = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(ChunkRenderer.class);
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(ChunkRenderer.class); 
        
        if (chunkRenderer instanceof RootNodeProvider) {
            YaumrGame.getInstance().getRootNode().detachChild(((RootNodeProvider)chunkRenderer).getRootNode());
        }
    }
    
    //</editor-fold>
}
