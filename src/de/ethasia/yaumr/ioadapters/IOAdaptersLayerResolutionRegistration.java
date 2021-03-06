package de.ethasia.yaumr.ioadapters;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.IslandEditorStateSetupInteractorImpl;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainWindowsPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateSetupInteractor;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandsOnFilesystem;
import de.ethasia.yaumr.ioadapters.presenters.ChunkPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.IslandEditorStateMainWindowsPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.ErrorMessagePresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.WarningMessagePresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.TerraformingToolsQuickbarPresenterImpl;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandListPresenter;
import de.ethasia.yaumr.interactors.interfaces.NoticePresenter;
import de.ethasia.yaumr.ioadapters.presenters.IslandListPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.NoticePresenterImpl;
import de.ethasia.yaumr.interactors.interfaces.WarningMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.Islands;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsGridPresenter;
import de.ethasia.yaumr.ioadapters.presenters.TerraformingToolsGridPresenterImpl;

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
        classInstanceContainer.registerImplementation(TerraformingToolsGridPresenter.class, TerraformingToolsGridPresenterImpl.class);
        classInstanceContainer.registerImplementation(ChunkPresenter.class, ChunkPresenterImpl.class);
        classInstanceContainer.registerImplementation(Islands.class, IslandsOnFilesystem.class);
        classInstanceContainer.registerImplementation(ErrorMessagePresenter.class, ErrorMessagePresenterImpl.class);
        classInstanceContainer.registerImplementation(WarningMessagePresenter.class, WarningMessagePresenterImpl.class);
        classInstanceContainer.registerImplementation(IslandListPresenter.class, IslandListPresenterImpl.class);
        classInstanceContainer.registerImplementation(NoticePresenter.class, NoticePresenterImpl.class);
    }
}
