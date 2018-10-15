package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.LoadCurrentIslandOnScreenUseCase;

public class LoadCurrentIslandOnScreenUseCaseImpl implements LoadCurrentIslandOnScreenUseCase {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void loadCurrentIslandToView() {
        ClassInstanceContainer serviceLocator = YaumrGame.getInstance().getClassInstanceContainer();
        
        ChunkPresenter chunkPresenter = serviceLocator.getImplementationInstance(ChunkPresenter.class);
        IslandManipulationFacade islandManipulationFacade = serviceLocator.getSingletonInstance(IslandManipulationFacade.class);
        
        chunkPresenter.presentAllChunksInIsland(islandManipulationFacade.getIsland());
    }    
    
    //</editor-fold>
        
}
