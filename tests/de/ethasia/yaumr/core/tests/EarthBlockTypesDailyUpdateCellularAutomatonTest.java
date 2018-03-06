package de.ethasia.yaumr.core.tests;

import de.ethasia.yaumr.core.EarthBlockTypesDailyUpdateCellularAutomaton;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.tests.mocks.IslandManipulationFacadeMock;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author R
 */
public class EarthBlockTypesDailyUpdateCellularAutomatonTest {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Island updatedIsland;
    private static IslandManipulationFacadeMock islandManipulationFacade;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Tests">
    
    @BeforeClass
    public static void setUpClass() {
        updatedIsland = new Island(256);
        islandManipulationFacade = new IslandManipulationFacadeMock();
        islandManipulationFacade.setNewlyCreatedIsland(updatedIsland);
    }
    
    @Test
    public void testTick_earthBlockIsPlaced_isConverted() {
        EarthBlockTypesDailyUpdateCellularAutomaton testCandidate = new EarthBlockTypesDailyUpdateCellularAutomaton();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setEarthBlocksDailyUpdateCA(testCandidate);
        
        BlockPosition placementPosition = new BlockPosition(34, 12, 109);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
        testCandidate.tick(0);
        
        assertEquals(BlockTypes.GRASSY_EARTH, placedBlock.getBlockType());
    }
    
    @Test
    public void testTick_wateredEarthBlockIsPlaced_driesUp() {
        EarthBlockTypesDailyUpdateCellularAutomaton testCandidate = new EarthBlockTypesDailyUpdateCellularAutomaton();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setEarthBlocksDailyUpdateCA(testCandidate);
        
        BlockPosition placementPosition = new BlockPosition(15, 4, 27);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_WATERED);
        
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
        testCandidate.tick(0);

        assertEquals(BlockTypes.EARTH, placedBlock.getBlockType());        
    }
    
    @Test
    public void testTick_wateredPlowedBlockIsPlaced_driesUp() {
        EarthBlockTypesDailyUpdateCellularAutomaton testCandidate = new EarthBlockTypesDailyUpdateCellularAutomaton();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setEarthBlocksDailyUpdateCA(testCandidate);
        
        BlockPosition placementPosition = new BlockPosition(170, 94, 128);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED_WATERED);
        
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
        testCandidate.tick(0);
        
        assertEquals(BlockTypes.EARTH_PLOWED, placedBlock.getBlockType());          
    }
    
    @Test
    public void testTick_wateredSeededBlockIsPlaced_driesUp() {
        EarthBlockTypesDailyUpdateCellularAutomaton testCandidate = new EarthBlockTypesDailyUpdateCellularAutomaton();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setEarthBlocksDailyUpdateCA(testCandidate);
        
        BlockPosition placementPosition = new BlockPosition(210, 211, 150);
        Block placedBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_SEEDED_WATERED);
        
        islandManipulationFacade.placeBlockAt(placedBlock, placementPosition);
        testCandidate.tick(0);
        
        assertEquals(BlockTypes.EARTH_SEEDED, placedBlock.getBlockType());          
    }    
    
    @Test
    public void testTick_blockAboveEarthBlockIsRemoved_earthBlockIsConverted() {
        EarthBlockTypesDailyUpdateCellularAutomaton testCandidate = new EarthBlockTypesDailyUpdateCellularAutomaton();
        testCandidate.setIslandToUpdate(updatedIsland);
        islandManipulationFacade.setEarthBlocksDailyUpdateCA(testCandidate);
        
        BlockPosition placementPosition = new BlockPosition(143, 67, 240);
        BlockPosition coveringPosition = new BlockPosition(143, 68, 240);
        Block coveringBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        Block coveredBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        
        islandManipulationFacade.placeBlockAt(coveringBlock, coveringPosition);
        islandManipulationFacade.placeBlockAt(coveredBlock, placementPosition);        
        testCandidate.tick(0);
        islandManipulationFacade.removeBlockAt(coveringPosition);
        testCandidate.tick(0);
        
        assertEquals(BlockTypes.GRASSY_EARTH, coveredBlock.getBlockType());          
    }
    
    //</editor-fold>
}
