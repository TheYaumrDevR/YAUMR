package de.ethasia.yaumr.presenters.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.presenters.implementation.BlockOutlineRendererImpl;
import de.ethasia.yaumr.presenters.implementation.IslandRendererImpl;

/**
 * This class registers all presenter instances for later usage.
 * 
 * @author R
 */
@AutowiringClass
public class PresenterRegistration {
    
    @AutowiringMethod
    public void registerPresenterImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerSingletonInstance(BlockOutlineRenderer.class, new BlockOutlineRendererImpl());
        classInstanceContainer.registerSingletonInstance(IslandRenderer.class, new IslandRendererImpl());
    }    
}
