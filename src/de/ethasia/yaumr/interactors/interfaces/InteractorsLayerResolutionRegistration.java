package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.IslandCreationInteractorImpl;
import de.ethasia.yaumr.interactors.IslandEditorStateMainInteractorImpl;
import de.ethasia.yaumr.interactors.TerraformingToolsSelectorImpl;

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
        classInstanceContainer.registerImplementation(IslandCreationInteractor.class, IslandCreationInteractorImpl.class);
        classInstanceContainer.registerImplementation(TerraformingToolsSelector.class, TerraformingToolsSelectorImpl.class);
    }     
}
