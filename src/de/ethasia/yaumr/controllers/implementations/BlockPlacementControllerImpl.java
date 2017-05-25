package de.ethasia.yaumr.controllers.implementations;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.customcontrols.implementations.QuickSelectionBarControl;
import de.ethasia.yaumr.customcontrols.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.presenters.interfaces.BlockOutlineRenderer;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author R
 */
public class BlockPlacementControllerImpl implements BlockPlacementController {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BOTTOM_QUICKSELECTION_BAR_NAME = "#bottomQuickSelectionBar";
    
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
                if (!isPressed) {
                    if (null != quickSelectionBar) {
                        quickSelectionBar.reactToKeyInput(name);
                    }
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
        
        quickSelectionBar.addItemToPosition(earthBlock, 0);
        
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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initKeys() {
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_1));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_2));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_3));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_4));
        YaumrGame.getInstance().getInputManager().addMapping(QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION, new KeyTrigger(KeyInput.KEY_5));
        
        YaumrGame.getInstance().getInputManager().addListener(keyEventHandler, 
                new String[] {
                    QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION, 
                    QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION,
                    QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION
                });        
    }
    
    private void detachKeys() {
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FIRST_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_SECOND_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_THIRD_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FOURTH_ITEM_KEYACTION);
        YaumrGame.getInstance().getInputManager().deleteMapping(QuickSelectionBarControl.SELECT_FIFTH_ITEM_KEYACTION);
        
        YaumrGame.getInstance().getInputManager().removeListener(keyEventHandler);
    }
    
    //</editor-fold>
}
