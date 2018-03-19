package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.IslandMetaData;
import de.ethasia.yaumr.interactors.interfaces.IslandListPresenter;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithIslandList;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class IslandListPresenterImpl implements IslandListPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showAvailableIslandList(Stream<IslandMetaData> metaDataOfIslands) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        AppStateWithIslandList islandListAppState = dependencyResolver.getSingletonInstance(AppStateWithIslandList.class);
        
        if (null != islandListAppState) {
            islandListAppState.showIslandList(metaDataOfIslands);
        }
    }    
    
    //</editor-fold>
}
