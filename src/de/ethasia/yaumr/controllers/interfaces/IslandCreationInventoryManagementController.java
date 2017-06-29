package de.ethasia.yaumr.controllers.interfaces;

import de.lessvoid.nifty.screen.Screen;

/**
 * This controller is responsible for handling the player's interaction with the
 * inventory/block selector in the island creation state.
 * 
 * @author R
 */
public interface IslandCreationInventoryManagementController {
    
    public void initialize(Screen niftyScreen);
    public void deInitialize();
    
    public boolean inventoryGridIsVisible();
    public void hideInventoryGrid();
    public void showInventoryGrid();
}
