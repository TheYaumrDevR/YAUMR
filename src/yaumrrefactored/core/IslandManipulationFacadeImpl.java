package yaumrrefactored.core;

import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.blocks.Block;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.core.blocks.BlockPlacementStrategy;
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
            BlockPlacementStrategy blockPlacementStrategy = block.getBlockPlacementStrategy();
            if (null == blockPlacementStrategy) {
                if (island.placeBlockAt(block, position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);                
                }
            } else {
                if (blockPlacementStrategy.placeBlockOnIslandAt(island, position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);                
                }                
            }
        }
    } 
    
    @Override
    public void removeBlockAt(BlockPosition position) {
        if (null != island) {
            Block blockToRemove = island.getBlockAt(position);
            BlockPlacementStrategy blockPlacementStrategy = blockToRemove.getBlockPlacementStrategy();
            
            if (null == blockPlacementStrategy) {
                if (island.removeBlockAt(position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);
                }                
            } else {
                if (blockPlacementStrategy.removeBlockFromIslandAt(island, position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);
                }                  
            }
        }
    }
    
    @Override
    public void copyBlockTo(Block blockToCopy, BlockPosition position) {
        if (null != island) {
            BlockPlacementStrategy blockPlacementStrategy = blockToCopy.getBlockPlacementStrategy();
            
            if (null == blockPlacementStrategy) {
                if (island.copyBlockTo(blockToCopy, position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);
                }                
            } else {
                if (blockPlacementStrategy.copyBlockToPositionOnIsland(island, position)) {
                    grassToEarthUpdater.setChangedPosition(position);
                    fallingSandHandler.setChangedPosition(position);
                }
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
 