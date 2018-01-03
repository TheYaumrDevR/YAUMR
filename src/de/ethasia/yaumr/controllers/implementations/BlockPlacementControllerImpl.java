package de.ethasia.yaumr.controllers.implementations;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.QuickSelectionBarControl;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.presenters.interfaces.BlockOutlineRenderer;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author R
 */
public class BlockPlacementControllerImpl implements BlockPlacementController {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BOTTOM_QUICKSELECTION_BAR_NAME = "#bottomQuickSelectionBar";
    private static final String EXECUTE_PRIMARY_ACTION_EVENT_NAME = "executePrimaryAction";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private QuickSelectionBar quickSelectionBar;
    private final ActionListener keyEventHandler;
    private Island islandToEdit;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BlockPlacementControllerImpl() {
        keyEventHandler = new ActionListener() {
            
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (name.equals(EXECUTE_PRIMARY_ACTION_EVENT_NAME)) {
                    executePrimaryAction(isPressed);
                } else {
                    handleQuickSelectionButtonPressed(isPressed, name);
                }
            }
        };
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void initialize(Screen niftyScreen) {
        quickSelectionBar = niftyScreen.findNiftyControl(BOTTOM_QUICKSELECTION_BAR_NAME, QuickSelectionBar.class);
        initKeys();
        islandToEdit = new Island(16);
    }    
    
    @Override
    public void update(float tpf) {
        Camera camera = YaumrGame.getInstance().getCamera();  
        Vector3f pointingPoint = camera.getLocation().add(camera.getDirection().normalize().mult(2.0f));
        
        if (null != islandToEdit) {
            GlobalBlockPosition blockPosition = islandToEdit.calculateBlockPositionInIsland(pointingPoint);
            BlockOutlineRenderer placementIndicatorRenderer = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockOutlineRenderer.class);
            placementIndicatorRenderer.renderBlockOutline(islandToEdit, blockPosition);
        }
    }
    
    @Override
    public void setSelectableItemAtPosition(QuickSelectableEntity item, int position) {
        quickSelectionBar.addItemToPosition(item, position);
    }
    
    @Override
    public void deInitialize() {
        detachKeys();
        BlockOutlineRenderer placementIndicatorRenderer = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockOutlineRenderer.class);
        placementIndicatorRenderer.unrenderCurrentlyRenderedObjects();
        IslandRenderer islandRenderer = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandRenderer.class);
        islandRenderer.removeRenderedData();
        islandToEdit = null;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initKeys() {
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
                    QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_SIXTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_SEVENTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_EIGHTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_NINTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_TENTH_ITEM_KEYACTION,
                    EXECUTE_PRIMARY_ACTION_EVENT_NAME
                });        
    }
    
    private void detachKeys() {
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
        YaumrGame.getInstance().getInputManager().deleteMapping(EXECUTE_PRIMARY_ACTION_EVENT_NAME);
        
        YaumrGame.getInstance().getInputManager().removeListener(keyEventHandler);
    }
    
    private void handleQuickSelectionButtonPressed(boolean isPressed, String eventName) {
        if (!isPressed) {
            if (null != quickSelectionBar) {
                quickSelectionBar.reactToKeyInput(eventName);
            }
        }
    }
    
    private void executePrimaryAction(boolean isPressed) {
        if (isPressed) {
            if (null != islandToEdit) {
                Camera camera = YaumrGame.getInstance().getCamera();  
                Vector3f pointingPoint = camera.getLocation().add(camera.getDirection().normalize().mult(2.0f));                    
                    
                if (null != quickSelectionBar) {
                    QuickSelectableEntity selectedEntity = quickSelectionBar.getEntityContainedInSelection();
                    
                    if (null != selectedEntity) {
                        selectedEntity.executePrimaryAction(pointingPoint, islandToEdit);
                    }
                }
            }                
        }
    }
    
    //</editor-fold>
}
