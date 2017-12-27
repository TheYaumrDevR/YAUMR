package de.ethasia.yaumr.ioadapters;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateMainWindowsPresenter;
import de.ethasia.yaumr.ioadapters.presenters.IslandEditorStateMainWindowsPresenterImpl;

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
    }
}
