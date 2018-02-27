package de.ethasia.yaumr.ioadapters;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.IslandEditorStateSetupInteractorImpl;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainWindowsPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateSetupInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandRepository;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandFilesystemRepository;
import de.ethasia.yaumr.ioadapters.presenters.ChunkPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.IslandEditorStateMainWindowsPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.IslandInitializationStateErrorMessagePresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.IslandInitializationStateWarningMessagePresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.TerraformingToolsQuickbarPresenterImpl;

/**
 *
 * @author R
 */
@AutowiringClass
public class IOAdaptersLayerResolutionRegistration {
    
    @AutowiringMethod
    public void resolveDependencies() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerImplementation(IslandEditorStateMainWindowsPresenter.class, IslandEditorStateMainWindowsPresenterImpl.class);  
        classInstanceContainer.registerImplementation(IslandEditorStateSetupInteractor.class, IslandEditorStateSetupInteractorImpl.class);
        classInstanceContainer.registerImplementation(TerraformingToolsQuickbarPresenter.class, TerraformingToolsQuickbarPresenterImpl.class);
        classInstanceContainer.registerImplementation(ChunkPresenter.class, ChunkPresenterImpl.class);
        classInstanceContainer.registerImplementation(IslandRepository.class, IslandFilesystemRepository.class);
        classInstanceContainer.registerImplementation(IslandInitializationStateErrorMessagePresenter.class, IslandInitializationStateErrorMessagePresenterImpl.class);
        classInstanceContainer.registerImplementation(IslandInitializationStateWarningMessagesPresenter.class, IslandInitializationStateWarningMessagePresenterImpl.class);
    }
}
