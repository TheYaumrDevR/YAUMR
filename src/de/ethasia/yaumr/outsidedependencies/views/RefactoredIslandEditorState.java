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
import de.ethasia.yaumr.interactors.interfaces.LoadCurrentIslandOnScreenUseCase;
import de.ethasia.yaumr.interactors.interfaces.SaveIslandInteractor;
import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.ioadapters.interfaces.BlockInteractionIndicatorPresenter;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.QuickSelectionBarControl;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.InventoryGrid;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsInteractor;
import de.ethasia.yaumr.interactors.interfaces.TimedUpdateInteractor;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithNotices;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.outsidedependencies.renderers.RootNodeProvider;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.SizeValueType;

/**
 *
 * @author R
 */
public class RefactoredIslandEditorState extends YaumrGameState implements IslandEditorState, AppStateWithNotices {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String MAIN_MENU_PANEL_NAME = "#mainMenu";
    private static final String HELP_PANEL_NAME = "#helpPanel";
    private static final String BOTTOM_QUICKSELECTION_BAR_NAME = "#bottomQuickSelectionBar";  
    private static final String TOOLGRID_NAME = "#itemSelectionGrid";
    private static final String SAVE_ISLAND_WINDOW_NAME = "#saveIslandDialogWindow";
    private static final String ISLAND_NAME_TEXTFIELD_NAME = "#islandNameInput";
    
    private static final String TOGGLE_MAIN_MENU_ACTION_NAME = "toggleMainMenu";
    private static final String TOGGLE_HELP_TEXT_ACTION_NAME = "toggleHelp";
    private static final String TOGGLE_TERRAFORMING_INVENTORY_ACTION_NAME = "toggleTerraformingInventory";
    private static final String SELECT_QUICKBAR_ITEM_ACTION_NAME = "selectItem";
    private static final String EXECUTE_PRIMARY_ACTION_EVENT_NAME = "executePrimaryAction";  
    
    private static final String NOTICE_WINDOW_CONTAINER_NAME = "#noticeWindowContainer";
    private static final String NOTICE_WINDOW_NAME = "#noticeWindow";
    private static final String NOTICE_BODY_PANEL_NAME = "#noticeWindowBody";
    private static final String NOTICE_HEADER_PANEL_NAME = "#noticeWindowHeader";
    private static final String NOTICE_TEXT_NAME = "#noticeText";          
    
    private static final String BLOCK_INTERACTION_INDICATOR_NAME = "BlockInteractionIndicator";  
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final ActionListener keyEventHandler;
        
    private Element mainMenu;
    private Element helpPanel;
    private Element saveIslandWindow;
    private TextField islandNameTextField;
    private Geometry blockInteractionIndicator;
    private QuickSelectionBar quickSelectionBar;
    private InventoryGrid toolsSelectionGrid;
    
    protected Element noticeWindowContainer;
    protected Element noticePanel;
    protected Element noticeBodyPanel;
    protected Element noticeHeaderPanel;
    protected Label noticeText;        
    
    private IslandEditorStateMainInteractor windowsInteractor;
    private IslandEditorStateSetupInteractor setupInteractor;
    private TerraformingToolsInteractor terraformingToolsSelector;
    private TimedUpdateInteractor timedUpdateInteractor;
    private BlockInteractionIndicatorPresenter blockInteractionIndicatorPresenter;  
    
    private boolean mainMenuInputIsBlocked;
    private boolean saveIslandNameWindowIsBlocked;
    
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
                            toggleToolsGrid(isPressed);
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
        saveIslandWindow = screen.findElementById(SAVE_ISLAND_WINDOW_NAME);
        quickSelectionBar = screen.findNiftyControl(BOTTOM_QUICKSELECTION_BAR_NAME, QuickSelectionBar.class);    
        toolsSelectionGrid = screen.findNiftyControl(TOOLGRID_NAME, InventoryGrid.class);
        islandNameTextField = screen.findNiftyControl(ISLAND_NAME_TEXTFIELD_NAME, TextField.class);     
        
        noticeWindowContainer = screen.findElementById(NOTICE_WINDOW_CONTAINER_NAME);
        noticePanel = screen.findElementById(NOTICE_WINDOW_NAME);
        noticeBodyPanel = screen.findElementById(NOTICE_BODY_PANEL_NAME);
        noticeHeaderPanel = screen.findElementById(NOTICE_HEADER_PANEL_NAME);
        noticeText = screen.findNiftyControl(NOTICE_TEXT_NAME, Label.class);          
        
        setupInteractor.setupInventoryInteractorsForIslandEditorState();
        terraformingToolsSelector = dependencyResolver.getSingletonInstance(TerraformingToolsInteractor.class);
    }    

    @Override
    public void onStartScreen() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        initKeys();
        hideAllVisibleGUIItems();  
        terraformingToolsSelector.setSelectedToolIndex(0);
        
        if (null != toolsSelectionGrid) {
            toolsSelectionGrid.hide();
        }       
        
        initRenderers();
        timedUpdateInteractor = dependencyResolver.getImplementationInstance(TimedUpdateInteractor.class);
        
        LoadCurrentIslandOnScreenUseCase showCurrentIslandUseCase = dependencyResolver.getImplementationInstance(LoadCurrentIslandOnScreenUseCase.class);
        showCurrentIslandUseCase.loadCurrentIslandToView();
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
        registerStateWithDependencyResolverIfRequired(YaumrGame.getInstance().getClassInstanceContainer());      
        setupApplicationParameters();
        setUpCameraLocationAndSpeed();
    }     
    
    @Override
    public void showMainMenu() {
        if (null != mainMenu) {
            mainMenu.show();
        }
    }
    
    @Override
    public void showHelpPanel() {
        if (null != helpPanel && !mainMenuInputIsBlocked) {
            helpPanel.show();
        }
    }

    @Override
    public void showTerraformingToolbox() {
    }    
    
    @Override
    public void hideAllVisibleGUIItems() {
        mainMenuInputIsBlocked = false;
        saveIslandNameWindowIsBlocked = false;
        
        if (null != mainMenu) {
            mainMenu.hide();
        }
        
        if (null != helpPanel) {
            helpPanel.hide();
        }  
        
        if (null != saveIslandWindow) {
            saveIslandWindow.hide();
        }
        
        if (null != errorMessagePanel) {
            errorMessagePanel.hide();
        }
        
        if (null != noticePanel) {
            noticePanel.hide();
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
        if (!mainMenuInputIsBlocked) {
            if (null != blockInteractionIndicatorPresenter) {
                blockInteractionIndicatorPresenter.removePresentedPointingIndicator();
            }
            
            YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();                    
        }
    }

    public void quitGame() {
        if (!mainMenuInputIsBlocked) {
            System.exit(0);
        }               
    }    
    
    public void selectQuickbarItem(String uiEventName, boolean keyIsPressed) {
        if (keyIsPressed) {
            if (null != quickSelectionBar) {
                Integer itemIndex = quickSelectionBar.getItemIndexForKeyActionName(uiEventName);
                terraformingToolsSelector.setSelectedToolIndex(itemIndex);
            }
        }
    }
    
    public void showSaveGameDialog() {
        if (null != saveIslandWindow && !mainMenuInputIsBlocked) {
            saveIslandWindow.show();
            mainMenuInputIsBlocked = true;
        }
    }
    
    public void hideSaveGameDialog() {
        mainMenuInputIsBlocked = false;
        if (null != saveIslandWindow && !saveIslandNameWindowIsBlocked) {
            saveIslandWindow.hide();
        }
    }    
    
    public void saveIsland() {
        if (null != islandNameTextField && !saveIslandNameWindowIsBlocked) {
            ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
            SaveIslandInteractor saveIslandInteractor = dependencyResolver.getImplementationInstance(SaveIslandInteractor.class);
            if (null != saveIslandInteractor) {
                hideSaveGameDialog();
                String islandName = islandNameTextField.getRealText();     
                saveIslandInteractor.saveIslandFromCurrentManipulationFacade(islandName);
            }
        }
    }
    
    @Override
    public void showNotice(String notice) {
        if (noticeWindowElementsArePresent()) {
            mainMenuInputIsBlocked = true;
            saveIslandNameWindowIsBlocked = true;             
            
            TextRenderer textWidthMeasurer = new TextRenderer(nifty);
            textWidthMeasurer.setFont(nifty.createFont("Interface/Fonts/MSUIGothic.fnt"));
            textWidthMeasurer.setText(notice);
            
            int windowXOffset = (noticeWindowContainer.getWidth() - textWidthMeasurer.getTextWidth()) / 2;
            noticePanel.setConstraintX(new SizeValue(windowXOffset, SizeValueType.Pixel));
            
            int windowYOffset = (noticeWindowContainer.getHeight() - noticePanel.getHeight()) / 2;
            noticePanel.setConstraintY(new SizeValue(windowYOffset, SizeValueType.Pixel));            
            
            noticeWindowContainer.layoutElements();
            
            noticeText.setText(notice);
            
            noticePanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            noticeBodyPanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            
            int headerPanelOffset = (noticeBodyPanel.getWidth() - noticeHeaderPanel.getWidth()) / 2;
            noticeHeaderPanel.setConstraintX(new SizeValue(headerPanelOffset, SizeValueType.Pixel));              
            
            noticeBodyPanel.layoutElements();
            noticePanel.layoutElements();
            
            noticePanel.show();            
        }
    }
    
    @Override
    public void showErrorMessage(String message) {
        if (errorMessageWindowElementsArePresent()) {
            mainMenuInputIsBlocked = true;
            saveIslandNameWindowIsBlocked = true;            
        }
    }
    
    public void onErrorMessageConfirmed() {
        saveIslandNameWindowIsBlocked = false;
        
        if (null != errorMessagePanel) {
            errorMessagePanel.hide();
        }         
    }
    
    public void onNoticeConfirmed() {
        saveIslandNameWindowIsBlocked = false;
        mainMenuInputIsBlocked = false;
        
        if (null != noticePanel) {
            noticePanel.hide();
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void registerStateWithDependencyResolverIfRequired(ClassInstanceContainer dependencyResolver) {
        dependencyResolver.registerSingletonInstance(IslandEditorState.class, this);
        dependencyResolver.removeSingletonInstance(AppStateWithNotices.class);
        dependencyResolver.registerSingletonInstance(AppStateWithNotices.class, this);          
    }
    
    private void setupApplicationParameters() {
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0.529f, 0.808f, 0.922f, 1.0f));        
    }
    
    private void setUpCameraLocationAndSpeed() {
        IslandManipulationFacade islandManipulationFacade = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandManipulationFacade.class);
        float camXZLocation = 0.5f * (islandManipulationFacade.getIslandEdgeLengthInBlocks() / 2);
        YaumrGame.getInstance().getCamera().setLocation(new Vector3f(camXZLocation, 0, camXZLocation));
        YaumrGame.getInstance().getFlyByCamera().setMoveSpeed(12.5f);        
    }
    
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
    
    private boolean noticeWindowElementsArePresent() {
        return null != noticeText 
                && null != noticePanel
                && null != noticeBodyPanel
                && null != noticeHeaderPanel
                && null != noticeWindowContainer;
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
    
    private void toggleToolsGrid(boolean keyIsPressed) {
        if (null != toolsSelectionGrid) {
            if (toolsSelectionGrid.isVisible()) {
                toolsSelectionGrid.hide();
            } else {
                toolsSelectionGrid.show();
            }
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
