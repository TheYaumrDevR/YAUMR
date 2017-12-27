package yaumrrefactored.interactors.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.interactors.IslandEditorStateMainInteractorImpl;

/**
 *
 * @author R
 */
@AutowiringClass
public class InteractorsLayerResolutionRegistration {
    
    @AutowiringMethod
    public void registerPresenterImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerImplementation(IslandEditorStateMainInteractor.class, IslandEditorStateMainInteractorImpl.class);
    }     
}
