package de.ethasia.yaumr.ioadapters.presenters.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.IslandManipulationFacadeImpl;
import de.ethasia.yaumr.core.blocks.AxisRotationValues;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.ioadapters.presenters.ChunkPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockTypeToTextureCoordinatesMapper;
import de.ethasia.yaumr.outsidedependencies.tests.mocks.ChunkRendererMock;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author R
 */
public class ChunkPresenterTest {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Island testIsland;
    private static ChunkRendererMock usedChunkRendererMock;
    
    //</editor-fold>
    
    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        usedChunkRendererMock = new ChunkRendererMock();
        dependencyResolver.removeSingletonInstance(ChunkRenderer.class);
        dependencyResolver.registerSingletonInstance(ChunkRenderer.class, usedChunkRendererMock); 
    }
    
    @Before
    public void setUpTest() {
        ChunkRendererMock.resetMethodCallCounts();
        testIsland = new Island(18);
    }
    
    @Test
    public void testPresentChunksForChangedPositions_unrotatedStandardCubesArePlacedInLShape_correctDataIsPassedToChunkRenderer() {        
        ChunkPresenterImpl testCandidate = new ChunkPresenterImpl();
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setIsland(testIsland);
        
        Block testBlockOne = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        Block testBlockTwo = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.COPPER_VEIN);
        Block testBlockThree = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GOLD_VEIN);
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(3, 20, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(4, 20, 3));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(4, 20, 4));
        
        testCandidate.setChangedPosition(new int[] {3, 20, 3});
        testCandidate.setChangedPosition(new int[] {4, 20, 3});
        testCandidate.setChangedPosition(new int[] {4, 20, 4});
        
        testCandidate.presentChunksForChangedPositions(testIsland);
        float[] vertices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getVertices();
        int[] indices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getIndices();
        float[] normals = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getNormals();
        float[] uvCoordinates = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getUVCoordinates();
        
        assertEquals(1, ChunkRendererMock.getCallCounterForMethodName("renderChunk"));
        assertEquals(168, vertices.length);
        assertEquals(84, indices.length);
        assertEquals(168, normals.length);
        assertEquals(112, uvCoordinates.length);
        
        float[] expectedUVLeftBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockOne.getBlockType());
        float[] expectedUVRightBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockTwo.getBlockType());
        float[] expectedUVFrontBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockThree.getBlockType());
        
        // The order in which they appear is not deterministic
        if (vertices[0] == 1.5f) {
            assertEquals(10.f, vertices[1], 0.001f);
            assertEquals(1.5f, vertices[2], 0.001f);  
            
            assertEquals(0.f, normals[0], 0.001f);
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(-1.f, normals[2], 0.001f);
            
            assertEquals(expectedUVLeftBlock[0], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVLeftBlock[1], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVLeftBlock[2], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVLeftBlock[3], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVLeftBlock[4], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVLeftBlock[5], uvCoordinates[5], 0.001f);            
        } else if (vertices[0] == 2.0f) {
            assertEquals(10.f, vertices[1], 0.001f);
            assertEquals(1.5f, vertices[2], 0.001f); 
            
            assertEquals(0.f, normals[0], 0.001f);
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(-1.f, normals[2], 0.001f); 
            
            assertEquals(expectedUVRightBlock[0], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVRightBlock[1], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVRightBlock[2], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVRightBlock[3], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVRightBlock[4], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVRightBlock[5], uvCoordinates[5], 0.001f);               
        } else if (vertices[0] == 2.5f) {
            assertEquals(10.f, vertices[1], 0.001f);
            assertEquals(2.f, vertices[2], 0.001f); 

            assertEquals(1.f, normals[0], 0.001f); 
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(0.f, normals[2], 0.001f);
            
            assertEquals(expectedUVFrontBlock[0], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVFrontBlock[1], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVFrontBlock[2], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVFrontBlock[3], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVFrontBlock[4], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVFrontBlock[5], uvCoordinates[5], 0.001f);               
        } else {
            assertTrue(false);
        }
        
        assertEquals(55, indices[81]);
    }
    
    @Test
    public void testPresentChunksForChangedPositions_unrotatedStandardCubesArePlacedIn3x3x3Shape_correctDataIsPassedToChunkRenderer() {        
        ChunkPresenterImpl testCandidate = new ChunkPresenterImpl();
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setIsland(testIsland);
        
        Block testBlockOne = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block testBlockTwo = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);
        Block testBlockThree = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(3, 14, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(4, 14, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(5, 14, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(3, 14, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(4, 14, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(5, 14, 4));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(3, 14, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(4, 14, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(5, 14, 5));
        
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(3, 15, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(4, 15, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(5, 15, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(3, 15, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(4, 15, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(5, 15, 4));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(3, 15, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(4, 15, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(5, 15, 5));      
        
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(3, 16, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(4, 16, 3));
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(5, 16, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(3, 16, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(4, 16, 4));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(5, 16, 4));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(3, 16, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(4, 16, 5));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(5, 16, 5));        
        
        testCandidate.setChangedPosition(new int[] {3, 14, 3});
        testCandidate.setChangedPosition(new int[] {4, 14, 3});
        testCandidate.setChangedPosition(new int[] {5, 14, 3});
        testCandidate.setChangedPosition(new int[] {3, 14, 4});
        testCandidate.setChangedPosition(new int[] {4, 14, 4});
        testCandidate.setChangedPosition(new int[] {5, 14, 4});
        testCandidate.setChangedPosition(new int[] {3, 14, 5});
        testCandidate.setChangedPosition(new int[] {4, 14, 5});
        testCandidate.setChangedPosition(new int[] {5, 14, 5});   

        testCandidate.setChangedPosition(new int[] {3, 15, 3});
        testCandidate.setChangedPosition(new int[] {4, 15, 3});
        testCandidate.setChangedPosition(new int[] {5, 15, 3});
        testCandidate.setChangedPosition(new int[] {3, 15, 4});
        testCandidate.setChangedPosition(new int[] {4, 15, 4});
        testCandidate.setChangedPosition(new int[] {5, 15, 4});
        testCandidate.setChangedPosition(new int[] {3, 15, 5});
        testCandidate.setChangedPosition(new int[] {4, 15, 5});
        testCandidate.setChangedPosition(new int[] {5, 15, 5});  

        testCandidate.setChangedPosition(new int[] {3, 16, 3});
        testCandidate.setChangedPosition(new int[] {4, 16, 3});
        testCandidate.setChangedPosition(new int[] {5, 16, 3});
        testCandidate.setChangedPosition(new int[] {3, 16, 4});
        testCandidate.setChangedPosition(new int[] {4, 16, 4});
        testCandidate.setChangedPosition(new int[] {5, 16, 4});
        testCandidate.setChangedPosition(new int[] {3, 16, 5});
        testCandidate.setChangedPosition(new int[] {4, 16, 5});
        testCandidate.setChangedPosition(new int[] {5, 16, 5});          
        
        testCandidate.presentChunksForChangedPositions(testIsland);  
        float[] vertices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getVertices();
        int[] indices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getIndices();
        float[] normals = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getNormals();
        float[] uvCoordinates = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getUVCoordinates();
        
        assertEquals(1, ChunkRendererMock.getCallCounterForMethodName("renderChunk"));
        assertEquals(648, vertices.length);
        assertEquals(324, indices.length);
        assertEquals(648, normals.length);
        assertEquals(432, uvCoordinates.length);        
    }
    
    @Test
    public void testPresentChunksForChangedPositions_twoBlocksArePlacedOnTwoNeighboringChunks_correctDataIsPassedToChunkRenderer() {        
        ChunkPresenterImpl testCandidate = new ChunkPresenterImpl();
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setIsland(testIsland);
        
        Block testBlockOne = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GRASSY_EARTH);
        Block testBlockTwo = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(15, 20, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(16, 20, 3));
        
        testCandidate.setChangedPosition(new int[] {15, 20, 3});
        testCandidate.setChangedPosition(new int[] {16, 20, 3});
        
        testCandidate.presentChunksForChangedPositions(testIsland);
        float[] vertices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getVertices();
        int[] indices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getIndices();
        float[] normals = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getNormals();
        float[] uvCoordinates = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getUVCoordinates();
        
        float[] expectedUVRightBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockTwo.getBlockType());        
        
        assertEquals(2, ChunkRendererMock.getCallCounterForMethodName("renderChunk"));
        assertEquals(60, vertices.length);
        assertEquals(30, indices.length);
        assertEquals(60, normals.length);
        assertEquals(40, uvCoordinates.length);
        
        assertEquals(vertices[3], 0.5f, 0.001);
        assertEquals(vertices[4], 10.f, 0.001);
        assertEquals(vertices[5], 1.5f, 0.001);
        
        assertEquals(indices[0], 2);
        assertEquals(indices[1], 1);
        assertEquals(indices[2], 0);
        assertEquals(indices[3], 3);
        assertEquals(indices[4], 2);
        assertEquals(indices[5], 0);  
        
        assertEquals(0.f, normals[0], 0.001f);
        assertEquals(0.f, normals[1], 0.001f); 
        assertEquals(-1.f, normals[2], 0.001f); 
        
        assertEquals(expectedUVRightBlock[0], uvCoordinates[0], 0.001f);
        assertEquals(expectedUVRightBlock[1], uvCoordinates[1], 0.001f);
        assertEquals(expectedUVRightBlock[2], uvCoordinates[2], 0.001f);
        assertEquals(expectedUVRightBlock[3], uvCoordinates[3], 0.001f);
    } 
    
    @Test
    public void testPresentChunksForChangedPositions_rotatedStandardCubesArePlacedInLShape_correctDataIsPassedToChunkRenderer() {        
        ChunkPresenterImpl testCandidate = new ChunkPresenterImpl();
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setIsland(testIsland);
        
        Block testBlockOne = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        Block testBlockTwo = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_TRUNK);
        Block testBlockThree = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.GOLD_VEIN);
        
        testBlockTwo.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
        testBlockTwo.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        testBlockTwo.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        
        islandManipulationFacade.copyBlockTo(testBlockOne, new BlockPosition(3, 20, 3));
        islandManipulationFacade.copyBlockTo(testBlockTwo, new BlockPosition(4, 20, 3));
        islandManipulationFacade.copyBlockTo(testBlockThree, new BlockPosition(4, 20, 4));
        
        testCandidate.setChangedPosition(new int[] {3, 20, 3});
        testCandidate.setChangedPosition(new int[] {4, 20, 3});
        testCandidate.setChangedPosition(new int[] {4, 20, 4});
        
        testCandidate.presentChunksForChangedPositions(testIsland);
        float[] vertices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getVertices();
        int[] indices = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getIndices();
        float[] normals = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getNormals();
        float[] uvCoordinates = usedChunkRendererMock.getChunkDataPassedToRenderMethod().getUVCoordinates();
        
        assertEquals(1, ChunkRendererMock.getCallCounterForMethodName("renderChunk"));
        assertEquals(168, vertices.length);
        assertEquals(84, indices.length);
        assertEquals(168, normals.length);
        assertEquals(112, uvCoordinates.length);
        
        float[] expectedUVLeftBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockOne.getBlockType());
        float[] expectedUVRightBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockTwo.getBlockType());
        float[] expectedUVFrontBlock = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlockThree.getBlockType());
        
        // The order in which they appear is not deterministic
        if (vertices[0] == 1.5f) { // Left block
            assertEquals(10.f, vertices[1], 0.f);
            assertEquals(1.5f, vertices[2], 0.f);  
            
            assertEquals(0.f, normals[0], 0.001f);
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(-1.f, normals[2], 0.001f);
            
            assertEquals(expectedUVLeftBlock[0], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVLeftBlock[1], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVLeftBlock[2], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVLeftBlock[3], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVLeftBlock[4], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVLeftBlock[5], uvCoordinates[5], 0.001f);            
        } else if (vertices[2] == 1.5f) { // Right block
            assertEquals(10.f, vertices[1], 0.f);
            assertEquals(2.5f, vertices[0], 0.f); 
            
            assertEquals(0.f, normals[0], 0.001f);
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(-1.f, normals[2], 0.001f); 
            
            assertEquals(expectedUVRightBlock[40], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVRightBlock[41], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVRightBlock[42], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVRightBlock[43], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVRightBlock[44], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVRightBlock[45], uvCoordinates[5], 0.001f);               
        } else if (vertices[2] == 2.f) { // Front block
            assertEquals(10.f, vertices[1], 0.f);
            assertEquals(2.5f, vertices[0], 0.f); 

            assertEquals(1.f, normals[0], 0.001f); 
            assertEquals(0.f, normals[1], 0.001f);
            assertEquals(0.f, normals[2], 0.001f);
            
            assertEquals(expectedUVFrontBlock[0], uvCoordinates[0], 0.001f);
            assertEquals(expectedUVFrontBlock[1], uvCoordinates[1], 0.001f);
            assertEquals(expectedUVFrontBlock[2], uvCoordinates[2], 0.001f);
            assertEquals(expectedUVFrontBlock[3], uvCoordinates[3], 0.001f);
            assertEquals(expectedUVFrontBlock[4], uvCoordinates[4], 0.001f);
            assertEquals(expectedUVFrontBlock[5], uvCoordinates[5], 0.001f);               
        } else {
            assertTrue(false);
        }
        
        assertEquals(55, indices[81]);
    }    
}
