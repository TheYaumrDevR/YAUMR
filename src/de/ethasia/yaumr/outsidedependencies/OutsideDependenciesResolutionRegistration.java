package de.ethasia.yaumr.outsidedependencies;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.outsidedependencies.renderers.ChunkRendererImpl;

/**
 *
 * @author R
 */
@AutowiringClass
public class OutsideDependenciesResolutionRegistration {
    
    @AutowiringMethod
    public void resolveDependencies() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerImplementation(ChunkRenderer.class, ChunkRendererImpl.class);
    }
}
