package de.ethasia.yaumr.controllers.implementations;

import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.controllers.interfaces.IslandCreationInventoryManagementController;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.InventoryGrid;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author R
 */
public class IslandCreationInventoryManagementControllerImpl implements IslandCreationInventoryManagementController {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String SELECTION_GRID_NAME = "#itemSelectionGrid";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private InventoryGrid selectionGrid;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void initialize(Screen niftyScreen) {
        selectionGrid = niftyScreen.findNiftyControl(SELECTION_GRID_NAME, InventoryGrid.class);
        initializeInventory();
    }

    @Override
    public void deInitialize() {
    }    
    
    @Override
    public boolean inventoryGridIsVisible() {
        if (null != selectionGrid) {
            return selectionGrid.isVisible();
        }
        
        return false;
    }
        
    @Override
    public void hideInventoryGrid() {
        if (null != selectionGrid) {
            selectionGrid.hide();
        }
    }
    
    @Override
    public void showInventoryGrid() {
        if (null != selectionGrid) {
            selectionGrid.show();
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initializeInventory() {
        if (null != selectionGrid) {
            Block blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ROCK);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.EARTH);

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.CLAY);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.SAND);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BEDROCK);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.PLOWED_EARTH);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WATERED_EARTH);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.PLOWED_WATERED_EARTH);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.LEAVES);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WALNUT_WOOD);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.OAK_WOOD);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ASH_WOOD);

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BIRCH_WOOD);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WALNUT_PLANKS);

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.OAK_PLANKS);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ASH_PLANKS);

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BIRCH_PLANKS);
        }
    }
    
    //</editor-fold>
}
