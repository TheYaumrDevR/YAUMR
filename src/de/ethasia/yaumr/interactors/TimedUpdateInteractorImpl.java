package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.TimedUpdateInteractor;
import java.util.List;

/**
 *
 * @author R
 */
public class TimedUpdateInteractorImpl implements TimedUpdateInteractor {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IslandManipulationFacade islandManipulationFacade;
    private final ChunkPresenter chunkPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TimedUpdateInteractorImpl() {
        islandManipulationFacade = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandManipulationFacade.class);
        chunkPresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ChunkPresenter.class);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void tick(float tpf) {
        long timeSinceLastUpdateInMS = (long)(tpf * 1000);
        
        if (null != islandManipulationFacade) {
            List<BlockPosition> changedBlocks = islandManipulationFacade.tick(timeSinceLastUpdateInMS);
            requestChunkRenderingForPositionsIfNecessary(changedBlocks);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void requestChunkRenderingForPositionsIfNecessary(List<BlockPosition> changedBlockPositions) {
        if (changedBlockPositions.size() > 0) {
            for (BlockPosition changedPosition : changedBlockPositions) {
                int[] changedCoordinates = {changedPosition.x, changedPosition.y, changedPosition.z};
                chunkPresenter.setChangedPosition(changedCoordinates);
            }
            
            chunkPresenter.presentChunksForChangedPositions(islandManipulationFacade.getIsland());
        }
    }
    
    //</editor-fold>
}
