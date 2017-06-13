package de.ethasia.yaumr.controllers.implementations;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.customcontrols.implementations.QuickSelectionBarControl;
import de.ethasia.yaumr.customcontrols.interfaces.QuickSelectionBar;
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
    private QuickSelectableEntity selectedEntity;
    
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
        
        Block earthBlock = new Block();
        earthBlock.setBlockType(BlockTypes.EARTH);
        
        Block grassBlock = new Block();
        grassBlock.setBlockType(BlockTypes.GRASS); 
        
        Block rockBlock = new Block();
        rockBlock.setBlockType(BlockTypes.ROCK);  
        
        Block wateredEarthBlock = new Block();
        wateredEarthBlock.setBlockType(BlockTypes.WATERED_EARTH);

        Block plowedEarthBlock = new Block();
        plowedEarthBlock.setBlockType(BlockTypes.PLOWED_EARTH);

        Block plowedWateredEarthBlock = new Block();
        plowedWateredEarthBlock.setBlockType(BlockTypes.PLOWED_WATERED_EARTH);    
        
        Block clayBlock = new Block();
        clayBlock.setBlockType(BlockTypes.CLAY); 

        Block bedrockBlock = new Block();
        bedrockBlock.setBlockType(BlockTypes.BEDROCK);    
        
        Block sandBlock = new Block();
        sandBlock.setBlockType(BlockTypes.SAND);              
        
        quickSelectionBar.addItemToPosition(earthBlock, 0);
        quickSelectionBar.addItemToPosition(grassBlock, 1);
        quickSelectionBar.addItemToPosition(rockBlock, 2);
        quickSelectionBar.addItemToPosition(wateredEarthBlock, 3);
        quickSelectionBar.addItemToPosition(plowedEarthBlock, 4);
        quickSelectionBar.addItemToPosition(plowedWateredEarthBlock, 5);
        quickSelectionBar.addItemToPosition(clayBlock, 6);
        quickSelectionBar.addItemToPosition(sandBlock, 7);
        quickSelectionBar.addItemToPosition(bedrockBlock, 8);
        
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
                selectedEntity = quickSelectionBar.reactToKeyInput(eventName);
            }
        }
    }
    
    private void executePrimaryAction(boolean isPressed) {
        if (isPressed) {
            if (null != selectedEntity) {
                if (null != islandToEdit) {
                    Camera camera = YaumrGame.getInstance().getCamera();  
                    Vector3f pointingPoint = camera.getLocation().add(camera.getDirection().normalize().mult(2.0f));                    
                    
                    selectedEntity.executePrimaryAction(pointingPoint, islandToEdit);
                }                
            }
        }
    }
    
    //</editor-fold>
}
