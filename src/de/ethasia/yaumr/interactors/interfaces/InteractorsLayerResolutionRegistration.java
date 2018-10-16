package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;

import de.ethasia.yaumr.interactors.IslandCreationInteractorImpl;
import de.ethasia.yaumr.interactors.IslandDeletionInteractorImpl;
import de.ethasia.yaumr.interactors.IslandEditorStateMainInteractorImpl;
import de.ethasia.yaumr.interactors.IslandListPopulatingInteractorImpl;
import de.ethasia.yaumr.interactors.LoadCurrentIslandOnScreenUseCaseImpl;
import de.ethasia.yaumr.interactors.SaveIslandInteractorImpl;
import de.ethasia.yaumr.interactors.TerraformingToolsInteractorImpl;
import de.ethasia.yaumr.interactors.TimedUpdateInteractorImpl;

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
        classInstanceContainer.registerImplementation(TerraformingToolsInteractor.class, TerraformingToolsInteractorImpl.class);
        classInstanceContainer.registerImplementation(TimedUpdateInteractor.class, TimedUpdateInteractorImpl.class);
        classInstanceContainer.registerImplementation(IslandListPopulatingInteractor.class, IslandListPopulatingInteractorImpl.class);
        classInstanceContainer.registerImplementation(SaveIslandInteractor.class, SaveIslandInteractorImpl.class);
        classInstanceContainer.registerImplementation(IslandDeletionInteractor.class, IslandDeletionInteractorImpl.class);
        classInstanceContainer.registerImplementation(LoadCurrentIslandOnScreenUseCase.class, LoadCurrentIslandOnScreenUseCaseImpl.class);
    }     
}
