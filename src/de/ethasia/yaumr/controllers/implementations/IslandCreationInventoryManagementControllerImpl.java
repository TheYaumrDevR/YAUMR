package de.ethasia.yaumr.controllers.implementations;

import de.ethasia.yaumr.blockengine.entities.Block;
import de.ethasia.yaumr.blockengine.entities.BlockTypes;
import de.ethasia.yaumr.controllers.interfaces.IslandCreationInventoryManagementController;
import de.ethasia.yaumr.customcontrols.interfaces.InventoryGrid;
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
            return selectionGrid.inventoryGridIsVisible();
        }
        
        return false;
    }
        
    @Override
    public void hideInventoryGrid() {
        if (null != selectionGrid) {
            selectionGrid.hideInventoryGrid();
        }
    }
    
    @Override
    public void showInventoryGrid() {
        if (null != selectionGrid) {
            selectionGrid.showInventoryGrid();
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initializeInventory() {
        if (null != selectionGrid) {
            Block blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ROCK);
            selectionGrid.setItemOnPosition(blockToAdd, 1);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.EARTH);
            selectionGrid.setItemOnPosition(blockToAdd, 2);

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.CLAY);
            selectionGrid.setItemOnPosition(blockToAdd, 3);  
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.SAND);
            selectionGrid.setItemOnPosition(blockToAdd, 4);     
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BEDROCK);
            selectionGrid.setItemOnPosition(blockToAdd, 5);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.PLOWED_EARTH);
            selectionGrid.setItemOnPosition(blockToAdd, 6);   
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WATERED_EARTH);
            selectionGrid.setItemOnPosition(blockToAdd, 7);    
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.PLOWED_WATERED_EARTH);
            selectionGrid.setItemOnPosition(blockToAdd, 8);  
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.LEAVES);
            selectionGrid.setItemOnPosition(blockToAdd, 9);
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WALNUT_WOOD);
            selectionGrid.setItemOnPosition(blockToAdd, 10);   
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.OAK_WOOD);
            selectionGrid.setItemOnPosition(blockToAdd, 11); 
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ASH_WOOD);
            selectionGrid.setItemOnPosition(blockToAdd, 12); 

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BIRCH_WOOD);
            selectionGrid.setItemOnPosition(blockToAdd, 13);             
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.WALNUT_PLANKS);
            selectionGrid.setItemOnPosition(blockToAdd, 14); 

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.OAK_PLANKS);
            selectionGrid.setItemOnPosition(blockToAdd, 15);     
            
            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.ASH_PLANKS);
            selectionGrid.setItemOnPosition(blockToAdd, 16);    

            blockToAdd = new Block();
            blockToAdd.setBlockType(BlockTypes.BIRCH_PLANKS);
            selectionGrid.setItemOnPosition(blockToAdd, 17);               
        }
    }
    
    //</editor-fold>
}
