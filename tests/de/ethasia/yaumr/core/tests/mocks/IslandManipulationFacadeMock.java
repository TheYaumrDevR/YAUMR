package de.ethasia.yaumr.core.tests.mocks;

import de.ethasia.yaumr.core.EarthBlockTypesDailyUpdateCellularAutomaton;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.FallingSandyBlockCellularAutomatonImpl;
import de.ethasia.yaumr.core.GrassToEarthCellularAutomatonImpl;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.tests.helpers.ClassMock;
import java.util.LinkedList;
import java.util.List;

public class IslandManipulationFacadeMock extends ClassMock implements IslandManipulationFacade {
    
    private Island islandToChange;
    private GrassToEarthCellularAutomatonImpl grassToEarthAutomaton;
    private FallingSandyBlockCellularAutomatonImpl fallingBlocksHandler;
    private EarthBlockTypesDailyUpdateCellularAutomaton earthToGrassAutomaton;
  
    public void setGrassToEarthCA(GrassToEarthCellularAutomatonImpl value) {
        grassToEarthAutomaton = value;
        grassToEarthAutomaton.setIslandManipulationFacade(this);
    }
  
    public void setFallingBlockHandler(FallingSandyBlockCellularAutomatonImpl value) {
        fallingBlocksHandler = value;
        fallingBlocksHandler.setIslandManipulationFacade(this);
    }
    
    public void setEarthBlocksDailyUpdateCA(EarthBlockTypesDailyUpdateCellularAutomaton value) {
        earthToGrassAutomaton = value;
        earthToGrassAutomaton.setIslandManipulationFacade(this);        
    }
  
    @Override
    public void setIsland(Island value) {
        islandToChange = value;
    }
  
    @Override
    public boolean placeBlockAt(Block block, BlockPosition position) {
        if ((null != islandToChange) && (islandToChange.placeBlockAt(block, position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
            
            if (null != earthToGrassAutomaton) {
                earthToGrassAutomaton.setChangedPosition(position);
            }
            
            return true;
        }
        
        return false;
    }
  
    @Override
    public boolean removeBlockAt(BlockPosition position) {
        incrementMockCounterForCalledMethod("removeBlockAt");
        
        if ((null != islandToChange) && (islandToChange.removeBlockAt(position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
            
            if (null != earthToGrassAutomaton) {
                earthToGrassAutomaton.setChangedPosition(position);
            }            
            
            return true;
        }
        
        return false;
    }
  
    @Override
    public boolean copyBlockTo(Block blockToCopy, BlockPosition position) {
        incrementMockCounterForCalledMethod("copyBlockTo");
        
        if ((null != islandToChange) && (islandToChange.copyBlockTo(blockToCopy, position))) {
            
            if (null != grassToEarthAutomaton) {
                grassToEarthAutomaton.setChangedPosition(position);
            }
      
            if (null != fallingBlocksHandler) {
                fallingBlocksHandler.setChangedPosition(position);
            }
            
            if (null != earthToGrassAutomaton) {
                earthToGrassAutomaton.setChangedPosition(position);
            }            
            
            return true;
        }
        
        return false;
    }
  
    @Override
    public List<BlockPosition> tick(long timeSinceLastTickInMS) { return new LinkedList<>(); }
    
    @Override
    public List<BlockPosition> performDailyUpdates() { return new LinkedList<>(); }

    @Override
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(InteractionVector vector) {
        return getBlockPositionOnCurrentIslandForInteractionVector(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public int getIslandEdgeLengthInBlocks() {
        return islandToChange.getHorizontalEdgeLengthOfIslandInBlocks();
    }

    @Override
    public BlockPosition getBlockPositionOnCurrentIslandForInteractionVector(float pointingPositionX, float pointingPositionY, float pointingPositionZ) {
        if (pointingPositionX < 0
                || pointingPositionY < 0
                || pointingPositionZ < 0) {
            return null;
        }
        
        int posX = (int)Math.floor(pointingPositionX / 0.5f);
        int posY = (int)Math.floor(pointingPositionY / 0.5f);
        int posZ = (int)Math.floor(pointingPositionZ / 0.5f);        
        
        return new BlockPosition(posX, posY, posZ);
    }

    @Override
    public Island getIsland() {
        return islandToChange;
    }
}