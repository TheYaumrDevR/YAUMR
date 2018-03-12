package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.IslandRepository;
import de.ethasia.yaumr.interactors.interfaces.NoticePresenter;
import de.ethasia.yaumr.interactors.interfaces.SaveIslandInteractor;
import java.util.UUID;

/**
 *
 * @author R
 */
public class SaveIslandInteractorImpl implements SaveIslandInteractor {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void saveIslandFromCurrentManipulationFacade(String islandName) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        IslandManipulationFacade islandManipulationFacade = dependencyResolver.getSingletonInstance(IslandManipulationFacade.class);
        
        if (null != islandManipulationFacade) {
            if (islandManipulationFacade.isCreatingNewIsland()) {
                saveNewIsland(islandName);
            }
        }
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void saveNewIsland(String islandName) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        IslandManipulationFacade islandManipulationFacade = dependencyResolver.getSingletonInstance(IslandManipulationFacade.class);        
        IslandRepository islandRepository = dependencyResolver.getImplementationInstance(IslandRepository.class);        
        
        UUID islandGuid = islandManipulationFacade.getIslandGUID();
        int islandEdgeLength = islandManipulationFacade.getIslandEdgeLengthInBlocks();

        IslandMetaData islandMetaData = new IslandMetaData();
        islandMetaData.setIslandName(islandName);
        islandMetaData.setIslandGUID(islandGuid);
        islandMetaData.setIslandEdgeLengthInBlocks(islandEdgeLength);
            
        if (islandRepository.createNewIsland(islandManipulationFacade.getIsland(), islandMetaData)) {
            NoticePresenter noticePresenter = dependencyResolver.getImplementationInstance(NoticePresenter.class);
            noticePresenter.showNotice("Island successfully saved.");
        }
    }
    
    //</editor-fold>
}
