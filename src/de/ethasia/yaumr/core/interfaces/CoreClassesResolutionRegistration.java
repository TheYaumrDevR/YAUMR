package de.ethasia.yaumr.core.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.EarthBlockTypesDailyUpdateCellularAutomaton;
import de.ethasia.yaumr.core.FallingSandyBlockCellularAutomatonImpl;
import de.ethasia.yaumr.core.GrassToEarthCellularAutomatonImpl;

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
        classInstanceContainer.registerImplementation(EarthBlockTypesDailyUpdateCellularAutomaton.class, EarthBlockTypesDailyUpdateCellularAutomaton.class);
    }      
}
