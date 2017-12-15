package yaumrrefactored.core.tests.mock;

import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.FallingSandyBlockCellularAutomatonImpl;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;
import yaumrrefactored.core.Island;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;

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
}