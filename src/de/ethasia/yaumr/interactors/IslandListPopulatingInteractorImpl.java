package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandListPopulatingInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandListPresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandRepository;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class IslandListPopulatingInteractorImpl implements IslandListPopulatingInteractor {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void loadAllAvailableIslandsIntoIslandList() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        IslandRepository islandDeserializer = dependencyResolver.getImplementationInstance(IslandRepository.class);
        IslandListPresenter islandListPresenter = dependencyResolver.getImplementationInstance(IslandListPresenter.class);
        
        Stream<IslandMetaData> allIslandsMetaData = islandDeserializer.getMetadataOfAllAvailableIslands();
        islandListPresenter.showAvailableIslandList(allIslandsMetaData);
    }    
    
    //</editor-fold>
}
