package yaumrrefactored.core.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.core.FallingSandyBlockCellularAutomatonImpl;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;

/**
 * 
 * @author R
 */
@AutowiringClass
public class CoreClassesResolutionRegistration {
    
    @AutowiringMethod
    public void registerPresenterImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerImplementation(FallingSandyBlockCellularAutomatonImpl.class, FallingSandyBlockCellularAutomatonImpl.class);
        classInstanceContainer.registerImplementation(GrassToEarthCellularAutomatonImpl.class, GrassToEarthCellularAutomatonImpl.class);
    }      
}
