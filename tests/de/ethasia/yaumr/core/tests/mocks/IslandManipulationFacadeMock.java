package de.ethasia.yaumr.core.tests.mocks;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.FallingSandyBlockCellularAutomatonImpl;
import de.ethasia.yaumr.core.GrassToEarthCellularAutomatonImpl;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;

public class IslandManipulationFacadeMock implements IslandManipulationFacade {
    
    private Island islandToChange;
    private GrassToEarthCellularAutomatonImpl grassToEarthAutomaton;
    private FallingSandyBlockCellularAutomatonImpl fallingBlocksHandler;
  
    public void setGrassToEarthCA(GrassToEarthCellularAutomatonImpl value) {
        grassToEarthAutomaton = value;
        grassToEarthAutomaton.setIslandManipulationFacade(this);
    }
  
    public void setFallingBlockHandler(FallingSandyBlockCellularAutomatonImpl value) {
        fallingBlocksHandler = value;
        fallingBlocksHandler.setIslandManipulationFacade(this);
    }
  
    @Override
    public void setIsland(Island value) {
        islandToChange = value;
    }
  
    @Override
    public void placeBlockAt(Block block, BlockPosition position) {
        if ((null != islandToChange) && (islandToChange.placeBlockAt(block, position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
        }
    }
  
    @Override
    public void removeBlockAt(BlockPosition position) {
        if ((null != islandToChange) && (islandToChange.removeBlockAt(position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
        }
    }
  
    @Override
    public void copyBlockTo(Block blockToCopy, BlockPosition position) {
        if ((null != islandToChange) && (islandToChange.copyBlockTo(blockToCopy, position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
        }
    }
  
    @Override
    public void tick(long timeSinceLastTickInMS) {}

    @Override
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(InteractionVector vector) {
        BlockPosition result = new BlockPosition(0, 0, 0);
        return result;
    }
}