package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.NoticePresenter;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithNotices;

/**
 *
 * @author R
 */
public class NoticePresenterImpl implements NoticePresenter {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showNotice(String message) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        AppStateWithNotices appStateWithNotices = dependencyResolver.getSingletonInstance(AppStateWithNotices.class);
        
        if (null != appStateWithNotices) {
            appStateWithNotices.showNotice(message);
        }
    }    
    
    //</editor-fold>    
}
