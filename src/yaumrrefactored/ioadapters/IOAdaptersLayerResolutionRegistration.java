package yaumrrefactored.ioadapters;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.interactors.interfaces.IslandEditorStateMainWindowsPresenter;
import yaumrrefactored.ioadapters.presenters.IslandEditorStateMainWindowsPresenterImpl;

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
