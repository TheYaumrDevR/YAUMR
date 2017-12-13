package yaumrrefactored.core;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;

/**
 * Represents a facade which provides interface methods to place and remove blocks from the current Island. 
 * All manipulation should be done exclusively through his class.
 * 
 * @author R
 */
public class IslandManipulationFacadeImpl implements IslandManipulationFacade {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Island island;
    private GrassToEarthCellularAutomatonImpl grassToEarthUpdater;
    private FallingSandyBlockCellularAutomatonImpl fallingSandHandler;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    @Override
    public void setIsland(Island islandToChange) {
        island = islandToChange;
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        grassToEarthUpdater = dependencyResolver.getImplementationInstance(GrassToEarthCellularAutomatonImpl.class);
        fallingSandHandler = dependencyResolver.getImplementationInstance(FallingSandyBlockCellularAutomatonImpl.class);
        
        grassToEarthUpdater.setIslandToUpdate(island);
        grassToEarthUpdater.setIslandManipulationFacade(this);
        fallingSandHandler.setIslandToUpdate(island);
        fallingSandHandler.setIslandManipulationFacade(this);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Facade Methods">
    
    @Override
    public void placeBlockAt(Block block, BlockPosition position) {
        if (null != island) {
            if (island.placeBlockAt(block, position)) {
                grassToEarthUpdater.setChangedPosition(position);
                fallingSandHandler.setChangedPosition(position);                
            }
        }
    } 
    
    @Override
    public void removeBlockAt(BlockPosition position) {
        if (null != island) {
            if (island.removeBlockAt(position)) {
                grassToEarthUpdater.setChangedPosition(position);
                fallingSandHandler.setChangedPosition(position);
            }
        }
    }
    
    @Override
    public void copyBlockTo(Block blockToCopy, BlockPosition position) {
        if (null != island) {
            if (island.copyBlockTo(blockToCopy, position)) {
                grassToEarthUpdater.setChangedPosition(position);
                fallingSandHandler.setChangedPosition(position);
            }
        }
    }   
    
    @Override
    public void tick(long timeSinceLastTickInMS) {
        if (null != island) {
            grassToEarthUpdater.tick(timeSinceLastTickInMS);
            fallingSandHandler.tick(timeSinceLastTickInMS);
        }
    }
    
    //</editor-fold>
}
 