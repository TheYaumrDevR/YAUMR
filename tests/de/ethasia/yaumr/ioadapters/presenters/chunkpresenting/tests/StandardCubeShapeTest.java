package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.tests;

import de.ethasia.yaumr.core.blocks.AxisRotationValues;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.BlockTypeToTextureCoordinatesMapper;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.StandardCubeShape;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author R
 */
public class StandardCubeShapeTest {
    
    @Test
    public void testGetShapeVertices_unrotatedUncoveredBlockIsPassed_correctVerticesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_PLOWED);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        float[] vertices = testCandidate.getShapeVertices(4, 8);
        
        assertEquals(72, vertices.length);
        assertEquals(2.f, vertices[0], 0.01f); // back face
        assertEquals(2.5f, vertices[3], 0.01f);
    }
    
    @Test
    public void testGetShapeVertices_unrotatedCoveredBlockIsPassed_correctVerticesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH_WATERED);
        testBlock.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisZ(AxisRotationValues.NINETY); // top face, bottom right position
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(true);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(true);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
                
        float[] vertices = testCandidate.getShapeVertices(7, 1);
        
        assertEquals(48, vertices.length);
        assertEquals(4.f, vertices[12], 0.01f);
        assertEquals(0.f, vertices[13], 0.01f);
        assertEquals(1.f, vertices[14], 0.01f);
    }
    
    @Test
    public void testGetShapeVertices_rotatedUncoveredBlockIsPassed_correctVerticesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_TRUNK);
        testBlock.rotateOnAxisX(AxisRotationValues.MINUS_NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisZ(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        float[] vertices = testCandidate.getShapeVertices(7, 1);
        
        assertEquals(72, vertices.length);
        // front face, bottom right position
        assertEquals(4.f, vertices[0], 0.01f);
        assertEquals(0.5f, vertices[1], 0.01f);
        assertEquals(0.5f, vertices[2], 0.01f);
    }    
    
    @Test
    public void testGetShapeVertices_rotatedCoveredBlockIsPassed_correctVerticesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.WALNUT_TRUNK);
        testBlock.rotateOnAxisX(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        testBlock.rotateOnAxisZ(AxisRotationValues.NINETY);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(true);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(true);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        float[] vertices = testCandidate.getShapeVertices(7, 1);
        
        assertEquals(48, vertices.length);
        // front face, bottom right position
        assertEquals(4.f, vertices[0], 0.01f);
        assertEquals(0.5f, vertices[1], 0.01f);
        assertEquals(1.f, vertices[2], 0.01f);
    }    
    
    @Test
    public void testGetVertexIndicesForLastCreatedVertices_unrotatedUncoveredBlockIsPassed_correctIndicesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        testCandidate.getShapeVertices(4, 8); 
        int[] indices = testCandidate.getVertexIndicesForLastCreatedVertices(36);
        
        assertEquals(36, indices.length);
        assertEquals(38, indices[0]);
        assertEquals(37, indices[1]);
        assertEquals(36, indices[2]);
    }
    
    @Test
    public void testGetVertexIndicesForLastCreatedVertices_unrotatedCoveredBlockIsPassed_correctIndicesAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(true);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        testCandidate.getShapeVertices(4, 8); 
        int[] indices = testCandidate.getVertexIndicesForLastCreatedVertices(36);
        
        assertEquals(30, indices.length);
        assertEquals(42, indices[6]);
        assertEquals(41, indices[7]);
        assertEquals(40, indices[8]);
    } 
    
    @Test
    public void testGetNormalsForLastCreatedVertices_unrotatedUncoveredBlockIsPresent_correctNormalsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ASH_TRUNK);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);
        
        testCandidate.getShapeVertices(4, 8); 
        float[] normals = testCandidate.getNormalsForLastCreatedVertices();
        
        assertEquals(72, normals.length);
        assertEquals(0, normals[0], 0.001f);
        assertEquals(0, normals[1], 0.001f);
        assertEquals(-1.f, normals[2], 0.001f);        
    }
    
    @Test
    public void testGetNormalsForLastCreatedVertices_unrotatedCoveredBlockIsPresent_correctNormalsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_TRUNK);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(true);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);   
        
        testCandidate.getShapeVertices(4, 8); 
        float[] normals = testCandidate.getNormalsForLastCreatedVertices();
        
        assertEquals(60, normals.length);
        assertEquals(1.f, normals[0], 0.001f);
        assertEquals(0.f, normals[1], 0.001f);
        assertEquals(0.f, normals[2], 0.001f);     
    }
    
    @Test
    public void testGetNormalsForLastCreatedVertices_rotatedCoveredBlockIsPresent_correctNormalsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_TRUNK);
        testBlock.rotateOnAxisX(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        testBlock.rotateOnAxisZ(AxisRotationValues.MINUS_NINETY);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(true);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(true);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);

        testCandidate.getShapeVertices(4, 8); 
        float[] normals = testCandidate.getNormalsForLastCreatedVertices();
        
        assertEquals(48, normals.length);
        assertEquals(1.f, normals[0], 0.001f);
        assertEquals(0.f, normals[1], 0.001f);
        assertEquals(0.f, normals[2], 0.001f);        
    }
    
    @Test
    public void testGetUVCoordinatesForLastCreatedVertices_unrotatedUncoveredBlockIsPresent_correctUVsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);  
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);  
        float[] uvCoordinatesForBlockType = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlock.getBlockType());
        
        testCandidate.getShapeVertices(4, 8); 
        float[] uvCoordinates = testCandidate.getUVCoordinatesForLastCreatedVertices();
        
        assertEquals(48, uvCoordinates.length);
        assertEquals(uvCoordinatesForBlockType[0], uvCoordinates[0], 0.f);
        assertEquals(uvCoordinatesForBlockType[1], uvCoordinates[1], 0.f);
        assertEquals(uvCoordinatesForBlockType[2], uvCoordinates[2], 0.f);
        assertEquals(uvCoordinatesForBlockType[3], uvCoordinates[3], 0.f);
    }
    
    @Test
    public void testGetUVCoordinatesForLastCreatedVertices_unrotatedCoveredBlockIsPresent_correctUVsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);  
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(true);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(true);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);  
        float[] uvCoordinatesForBlockType = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlock.getBlockType());
        
        testCandidate.getShapeVertices(4, 8); 
        float[] uvCoordinates = testCandidate.getUVCoordinatesForLastCreatedVertices();
        
        assertEquals(32, uvCoordinates.length);
        assertEquals(uvCoordinatesForBlockType[0], uvCoordinates[8], 0.f);
        assertEquals(uvCoordinatesForBlockType[1], uvCoordinates[9], 0.f);
        assertEquals(uvCoordinatesForBlockType[2], uvCoordinates[10], 0.f);
        assertEquals(uvCoordinatesForBlockType[3], uvCoordinates[11], 0.f);
    }   
    
    @Test
    public void testGetUVCoordinatesForLastCreatedVertices_rotatedCoveredBlockIsPresent_correctUVsAreCreated() {
        StandardCubeShape testCandidate = StandardCubeShape.getInstance();
        Block testBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);  
        testBlock.rotateOnAxisX(AxisRotationValues.NINETY);
        testBlock.rotateOnAxisY(AxisRotationValues.MINUS_NINETY);
        testBlock.rotateOnAxisZ(AxisRotationValues.MINUS_NINETY);
        testCandidate.setBlockToCreateDataFrom(testBlock);
        testCandidate.setBackFaceOfBlockIsCovered(false);
        testCandidate.setRightFaceOfBlockIsCovered(false);
        testCandidate.setFrontFaceOfBlockIsCovered(false);
        testCandidate.setLeftFaceOfBlockIsCovered(false);
        testCandidate.setTopFaceOfBlockIsCovered(false);
        testCandidate.setBottomFaceOfBlockIsCovered(false);  
        float[] uvCoordinatesForBlockType = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(testBlock.getBlockType());
        
        testCandidate.getShapeVertices(4, 8); 
        float[] uvCoordinates = testCandidate.getUVCoordinatesForLastCreatedVertices();
        
        assertEquals(48, uvCoordinates.length);
        assertEquals(uvCoordinatesForBlockType[0], uvCoordinates[8], 0.f);
        assertEquals(uvCoordinatesForBlockType[1], uvCoordinates[9], 0.f);
        assertEquals(uvCoordinatesForBlockType[2], uvCoordinates[10], 0.f);
        assertEquals(uvCoordinatesForBlockType[3], uvCoordinates[11], 0.f);
    }    
}
