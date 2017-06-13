package de.ethasia.yaumr.blockengine.usecases.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.usecases.implementations.FallingBlockCellularAutomatonImpl;

/**
 *
 * @author R
 */
@AutowiringClass
public class UseCaseRegistration {
    
    @AutowiringMethod
    public void registerUseCaseImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerSingletonInstance(FallingBlockCellularAutomaton.class, new FallingBlockCellularAutomatonImpl());
    }
}
