package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandListPopulatingInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandListPresenter;
import java.util.stream.Stream;
import de.ethasia.yaumr.interactors.interfaces.Islands;

/**
 *
 * @author R
 */
public class IslandListPopulatingInteractorImpl implements IslandListPopulatingInteractor {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void loadAllAvailableIslandsIntoIslandList() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        Islands islandDeserializer = dependencyResolver.getImplementationInstance(Islands.class);
        IslandListPresenter islandListPresenter = dependencyResolver.getImplementationInstance(IslandListPresenter.class);
        
        Stream<IslandMetaData> allIslandsMetaData = islandDeserializer.getMetadataOfAllAvailableIslands();
        islandListPresenter.showAvailableIslandList(allIslandsMetaData);
    }    
    
    //</editor-fold>
}
