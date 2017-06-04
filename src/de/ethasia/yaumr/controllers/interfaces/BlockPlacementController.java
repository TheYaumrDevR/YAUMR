package de.ethasia.yaumr.controllers.interfaces;

import de.lessvoid.nifty.screen.Screen;

/**
 * This controller is responsible for translating user input into placing
 * and removing of blocks.
 * 
 * @author R
 */
public interface BlockPlacementController {
    
    public void initialize(Screen niftyScreen);
    public void update(float tpf);
    public void deInitialize();
}
