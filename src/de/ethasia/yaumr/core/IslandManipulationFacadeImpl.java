package de.ethasia.yaumr.core;

import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.blocks.BlockPlacementStrategy;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;
import java.util.List;
import java.util.UUID;

/**
 * Represents a facade which provides interface methods to place and remove blocks from the current Island. 
 * All manipulation should be done exclusively through his class.
 * 
 * @author R
 */
public class IslandManipulationFacadeImpl implements IslandManipulationFacade {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private UUID islandGUID;
    private String islandName;
    
    private boolean isCreatingNewIsland;
    
    private Island island;
    private GrassToEarthCellularAutomatonImpl grassToEarthUpdater;
    private FallingSandyBlockCellularAutomatonImpl fallingSandHandler;
    private EarthBlockTypesDailyUpdateCellularAutomaton uncoveredEarthBlocksUpdater;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    @Override
    public void setNewlyCreatedIsland(Island islandToChange) {
        islandGUID = UUID.randomUUID();
        isCreatingNewIsland = false;
        
        setupRelevantObjects(islandToChange);
    }
    
    @Override
    public void setLoadedIsland(Island islandToChange, String name, UUID islandGUID) {
        this.islandGUID = islandGUID;
        islandName = name;
        isCreatingNewIsland = true;
        
        setupRelevantObjects(islandToChange);     
    }
    
    @Override
    public Island getIsland() {
        return island;
    }    
    
    @Override
    public boolean isCreatingNewIsland() {
        return isCreatingNewIsland;
    }
    
    @Override
    public UUID getIslandGUID() {
        return islandGUID;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Facade Methods">
    
    @Override
    public boolean placeBlockAt(Block block, BlockPosition position) {
        if (null != island) {
            BlockPlacementStrategy blockPlacementStrategy = block.getBlockPlacementStrategy();
            if (null == blockPlacementStrategy) {
                if (island.placeBlockAt(block, position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }
            } else {
                if (blockPlacementStrategy.placeBlockOnIslandAt(island, position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }                
            }
        }
        
        return false;
    } 
    
    @Override
    public boolean removeBlockAt(BlockPosition position) {
        if (null != island) {
            Block blockToRemove = island.getBlockAt(position);
            
            if (null == blockToRemove) {
                return false;
            }
            
            BlockPlacementStrategy blockPlacementStrategy = blockToRemove.getBlockPlacementStrategy();
            
            if (null == blockPlacementStrategy) {
                if (island.removeBlockAt(position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }                
            } else {
                if (blockPlacementStrategy.removeBlockFromIslandAt(island, position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }                  
            }
        }
        
        return false;
    }
    
    @Override
    public boolean copyBlockTo(Block blockToCopy, BlockPosition position) {
        if (null != island) {
            BlockPlacementStrategy blockPlacementStrategy = blockToCopy.getBlockPlacementStrategy();
            
            if (null == blockPlacementStrategy) {
                if (island.copyBlockTo(blockToCopy, position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }                
            } else {
                if (blockPlacementStrategy.copyBlockToPositionOnIsland(island, position)) {
                    setChangedPositionForCellularAutomatons(position);
                    
                    return true;
                }
            }
        }
        
        return false;
    } 
    
    @Override
    public List<BlockPosition> tick(long timeSinceLastTickInMS) {
        if (null != island) {
            grassToEarthUpdater.tick(timeSinceLastTickInMS);
            fallingSandHandler.tick(timeSinceLastTickInMS);
            
            List<BlockPosition> updatedBlocks = grassToEarthUpdater.getUpdatedPositionsSinceLastTick();
            updatedBlocks.addAll(fallingSandHandler.getUpdatedPositionsSinceLastTick());
            
            return updatedBlocks;
        }
        
        return null;
    }
    
    @Override
    public List<BlockPosition> performDailyUpdates() {
        if (null != island) {
            uncoveredEarthBlocksUpdater.tick(0);
            
            List<BlockPosition> updatedBlocks = uncoveredEarthBlocksUpdater.getUpdatedPositionsSinceLastTick();            
            return updatedBlocks;
        }
        
        return null;
    }
    
    @Override
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(InteractionVector vector) {
        return getBlockPositionOnCurrentIslandForInteractionVector(vector.getX(), vector.getY(), vector.getZ());
    }
    
    @Override
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(float pointingPositionX, float pointingPositionY, float pointingPositionZ) {
        if (null != island) {
            int horizontalEdgeLength = island.getHorizontalEdgeLengthOfIslandInBlocks();
            int height = Island.HEIGHT_IN_BLOCKS;
            
            if (pointingPositionX > 0 && pointingPositionY > 0 && pointingPositionZ > 0) {
                int posX = (int)Math.floor(pointingPositionX / 0.5f);
                int posY = (int)Math.floor(pointingPositionY / 0.5f);
                int posZ = (int)Math.floor(pointingPositionZ / 0.5f);
                
                if (posX < horizontalEdgeLength && posY < height && posZ < horizontalEdgeLength) {
                    return new BlockPosition(posX, posY, posZ);
                }
            }
        }
        
        return null;
    }    
    
    @Override
    public int getIslandEdgeLengthInBlocks() {
        if (null != island) {
            return island.getHorizontalEdgeLengthOfIslandInBlocks();
        } 
        
        return 0;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void setChangedPositionForCellularAutomatons(BlockPosition position) {
        grassToEarthUpdater.setChangedPosition(position);
        fallingSandHandler.setChangedPosition(position);
        uncoveredEarthBlocksUpdater.setChangedPosition(position);        
    }
    
    private void setupRelevantObjects(Island islandToChange) {
        island = islandToChange;
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        grassToEarthUpdater = dependencyResolver.getImplementationInstance(GrassToEarthCellularAutomatonImpl.class);
        fallingSandHandler = dependencyResolver.getImplementationInstance(FallingSandyBlockCellularAutomatonImpl.class);
        uncoveredEarthBlocksUpdater = dependencyResolver.getImplementationInstance(EarthBlockTypesDailyUpdateCellularAutomaton.class);
        
        grassToEarthUpdater.setIslandToUpdate(island);
        grassToEarthUpdater.setIslandManipulationFacade(this);
        fallingSandHandler.setIslandToUpdate(island);
        fallingSandHandler.setIslandManipulationFacade(this);
        uncoveredEarthBlocksUpdater.setIslandToUpdate(island);
        uncoveredEarthBlocksUpdater.setIslandManipulationFacade(this);           
    }
    
    //</editor-fold>
}
  