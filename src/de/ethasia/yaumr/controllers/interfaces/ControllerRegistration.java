package de.ethasia.yaumr.controllers.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.controllers.implementations.BlockPlacementControllerImpl;
import de.ethasia.yaumr.controllers.implementations.IslandCreationInventoryManagementControllerImpl;

/**
 * Registers controller implementations for later resolution.
 * 
 * @author R
 */
@AutowiringClass
public class ControllerRegistration {
    
    @AutowiringMethod
    public void registerControllerImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerSingletonInstance(BlockPlacementController.class, new BlockPlacementControllerImpl());
        classInstanceContainer.registerSingletonInstance(IslandCreationInventoryManagementController.class, new IslandCreationInventoryManagementControllerImpl());
    }
}
