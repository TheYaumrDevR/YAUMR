package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockFaceTypes;
import de.ethasia.yaumr.core.blocks.Vector3;

/**
 *
 * @author R
 */
public class StandardCubeShape extends BlockShape {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static StandardCubeShape instance;
    
    private final Vector3[] blockHalfAxes = {
        Vector3.UNIT_X.scaleImmutable(0.25f),
        Vector3.UNIT_Y.scaleImmutable(0.25f),
        Vector3.UNIT_Z.scaleImmutable(0.25f)
    };
    
    private Vector3[] v;   
    private Vector3 cubeCenter;    
    private float[] lastUsedVertexBuffer;
    
    private Block blockToCreateDataFrom;
    private int chunkXOfBlock;
    private int chunkZOfBlock;
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private StandardCubeShape() {}
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    @Override
    public void setBlockToCreateDataFrom(Block value) {
        blockToCreateDataFrom = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public float[] getShapeVertices(int xPositionInChunk, int zPositionInChunk) { 
        if (null != blockToCreateDataFrom) {
            chunkXOfBlock = xPositionInChunk;
            chunkZOfBlock = zPositionInChunk;
            initializeCenterAndUntranslatedVertices();
            rotateCurrentVertices();
            translateVertices();
            createVertexBufferWithNotCoveredFaces();            
        } else {
            lastUsedVertexBuffer = new float[0];
        }
        
        return lastUsedVertexBuffer;
    }

    @Override
    public int[] getVertexIndices(int amountOfAlreadySetupIndicesInChunk) {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        int[] indexBuffer = new int[lastAmountOfUncoveredFaces * 6];
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            int indexFaceOffset = 0;
            int alreadyPlacedIndices = 0;

            if (!blockToCreateDataFrom.backFaceIsCovering()) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;
            }
            
            if (!blockToCreateDataFrom.rightFaceIsCovering()) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }
            
            if (!blockToCreateDataFrom.frontFaceIsCovering()) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }

            if (!blockToCreateDataFrom.leftFaceIsCovering()) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }   
            
            if (!blockToCreateDataFrom.topFaceIsCovering()) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            } 
            
            if (!blockToCreateDataFrom.bottomFaceIsCovering()) {                
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, amountOfAlreadySetupIndicesInChunk);
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }            
        }
        
        return indexBuffer;
    }

    @Override
    public float[] getNormals() {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        float[] normalsBuffer = new float[lastAmountOfUncoveredFaces * 12];  
        int currentBufferOffset = 0;
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            if (null == blockToCreateDataFrom.getCurrentRotation()) {
                if (!blockToCreateDataFrom.backFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = 0.f;
                    normalsBuffer[currentBufferOffset + 1] = 0.f;
                    normalsBuffer[currentBufferOffset + 2] = -1.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = 0.f;
                    normalsBuffer[currentBufferOffset + 4] = 0.f;
                    normalsBuffer[currentBufferOffset + 5] = -1.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = 0.f;
                    normalsBuffer[currentBufferOffset + 7] = 0.f;
                    normalsBuffer[currentBufferOffset + 8] = -1.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = 0.f;
                    normalsBuffer[currentBufferOffset + 10] = 0.f;
                    normalsBuffer[currentBufferOffset + 11] = -1.f;
                    
                    currentBufferOffset += 12;
                }
                
                if (!blockToCreateDataFrom.rightFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = 1.f;
                    normalsBuffer[currentBufferOffset + 1] = 0.f;
                    normalsBuffer[currentBufferOffset + 2] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = 1.f;
                    normalsBuffer[currentBufferOffset + 4] = 0.f;
                    normalsBuffer[currentBufferOffset + 5] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = 1.f;
                    normalsBuffer[currentBufferOffset + 7] = 0.f;
                    normalsBuffer[currentBufferOffset + 8] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = 1.f;
                    normalsBuffer[currentBufferOffset + 10] = 0.f;
                    normalsBuffer[currentBufferOffset + 11] = 0.f;
                    
                    currentBufferOffset += 12;                    
                }
                
                if (!blockToCreateDataFrom.frontFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = 0.f;
                    normalsBuffer[currentBufferOffset + 1] = 0.f;
                    normalsBuffer[currentBufferOffset + 2] = 1.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = 0.f;
                    normalsBuffer[currentBufferOffset + 4] = 0.f;
                    normalsBuffer[currentBufferOffset + 5] = 1.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = 0.f;
                    normalsBuffer[currentBufferOffset + 7] = 0.f;
                    normalsBuffer[currentBufferOffset + 8] = 1.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = 0.f;
                    normalsBuffer[currentBufferOffset + 10] = 0.f;
                    normalsBuffer[currentBufferOffset + 11] = 1.f;
                    
                    currentBufferOffset += 12;                    
                }
                
                if (!blockToCreateDataFrom.leftFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = -1.f;
                    normalsBuffer[currentBufferOffset + 1] = 0.f;
                    normalsBuffer[currentBufferOffset + 2] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = -1.f;
                    normalsBuffer[currentBufferOffset + 4] = 0.f;
                    normalsBuffer[currentBufferOffset + 5] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = -1.f;
                    normalsBuffer[currentBufferOffset + 7] = 0.f;
                    normalsBuffer[currentBufferOffset + 8] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = -1.f;
                    normalsBuffer[currentBufferOffset + 10] = 0.f;
                    normalsBuffer[currentBufferOffset + 11] = 0.f;
                    
                    currentBufferOffset += 12;                     
                }
                
                if (!blockToCreateDataFrom.topFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = 0.f;
                    normalsBuffer[currentBufferOffset + 1] = 1.f;
                    normalsBuffer[currentBufferOffset + 2] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = 0.f;
                    normalsBuffer[currentBufferOffset + 4] = 1.f;
                    normalsBuffer[currentBufferOffset + 5] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = 0.f;
                    normalsBuffer[currentBufferOffset + 7] = 1.f;
                    normalsBuffer[currentBufferOffset + 8] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = 0.f;
                    normalsBuffer[currentBufferOffset + 10] = 1.f;
                    normalsBuffer[currentBufferOffset + 11] = 0.f;
                    
                    currentBufferOffset += 12;                     
                }
                
                if (!blockToCreateDataFrom.bottomFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = 0.f;
                    normalsBuffer[currentBufferOffset + 1] = -1.f;
                    normalsBuffer[currentBufferOffset + 2] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 3] = 0.f;
                    normalsBuffer[currentBufferOffset + 4] = -1.f;
                    normalsBuffer[currentBufferOffset + 5] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 6] = 0.f;
                    normalsBuffer[currentBufferOffset + 7] = -1.f;
                    normalsBuffer[currentBufferOffset + 8] = 0.f;
                    
                    normalsBuffer[currentBufferOffset + 9] = 0.f;
                    normalsBuffer[currentBufferOffset + 10] = -1.f;
                    normalsBuffer[currentBufferOffset + 11] = 0.f;
                    
                    currentBufferOffset += 12;                    
                }
            } else {
                Vector3 backFaceNormal = new Vector3(0.f, 0.f, -1.f);
                Vector3 rightFaceNormal = new Vector3(1.f, 0.f, 0.f);
                Vector3 frontFaceNormal = new Vector3(0.f, 0.f, 1.f);
                Vector3 leftFaceNormal = new Vector3(-1.f, 0.f, 0.f);
                Vector3 topFaceNormal = new Vector3(0.f, 1.f, 0.f);
                Vector3 bottomFaceNormal = new Vector3(0.f, -1.f, 0.f);
                
                rotatePoint(backFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                rotatePoint(rightFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                rotatePoint(frontFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                rotatePoint(leftFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                rotatePoint(topFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                rotatePoint(bottomFaceNormal, blockToCreateDataFrom.getCurrentRotation());
                
                if (!blockToCreateDataFrom.backFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = backFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = backFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = backFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = backFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = backFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = backFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = backFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = backFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = backFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = backFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = backFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = backFaceNormal.getZ();
                    
                    currentBufferOffset += 12;
                }
                
                if (!blockToCreateDataFrom.rightFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = rightFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = rightFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = rightFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = rightFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = rightFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = rightFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = rightFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = backFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = backFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = rightFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = rightFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = rightFaceNormal.getZ();
                    
                    currentBufferOffset += 12;                    
                }
                
                if (!blockToCreateDataFrom.frontFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = frontFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = frontFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = frontFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = frontFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = frontFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = frontFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = frontFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = frontFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = frontFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = frontFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = frontFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = frontFaceNormal.getZ();
                    
                    currentBufferOffset += 12;                    
                }
                
                if (!blockToCreateDataFrom.leftFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = leftFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = leftFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = leftFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = leftFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = leftFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = leftFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = leftFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = leftFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = leftFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = leftFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = leftFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = leftFaceNormal.getZ();
                    
                    currentBufferOffset += 12;                     
                }
                
                if (!blockToCreateDataFrom.topFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = topFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = topFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = topFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = topFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = topFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = topFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = topFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = topFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = topFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = topFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = topFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = topFaceNormal.getZ();
                    
                    currentBufferOffset += 12;                     
                }
                
                if (!blockToCreateDataFrom.bottomFaceIsCovering()) {
                    normalsBuffer[currentBufferOffset] = bottomFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 1] = bottomFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 2] = bottomFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 3] = bottomFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 4] = bottomFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 5] = bottomFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 6] = bottomFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 7] = bottomFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 8] = bottomFaceNormal.getZ();
                    
                    normalsBuffer[currentBufferOffset + 9] = bottomFaceNormal.getX();
                    normalsBuffer[currentBufferOffset + 10] = bottomFaceNormal.getY();
                    normalsBuffer[currentBufferOffset + 11] = bottomFaceNormal.getZ();
                    
                    currentBufferOffset += 12;                    
                }                
            }
        }
        
        return normalsBuffer;
    }

    @Override
    public float[] getUVCoordinates() {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        float[] uvBuffer = new float[lastAmountOfUncoveredFaces * 8];  
        int currentBufferOffset = 0;
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            float[] uvCoordinates = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(blockToCreateDataFrom.getBlockType());
            
            if (!blockToCreateDataFrom.backFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[0];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[1];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[2];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[3];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[4];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[5];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[6];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[7];
                
                currentBufferOffset += 8;
            }
            
            if (!blockToCreateDataFrom.rightFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[8];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[9];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[10];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[11];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[12];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[13];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[14];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[15];
                
                currentBufferOffset += 8;
            }

            if (!blockToCreateDataFrom.frontFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[16];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[17];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[18];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[19];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[20];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[21];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[22];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[23];
                
                currentBufferOffset += 8;
            }   
            
            if (!blockToCreateDataFrom.leftFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[24];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[25];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[26];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[27];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[28];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[29];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[30];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[31];
                
                currentBufferOffset += 8;
            }

            if (!blockToCreateDataFrom.topFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[32];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[33];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[34];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[35];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[36];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[37];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[38];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[39];
                
                currentBufferOffset += 8;
            } 
            
            if (!blockToCreateDataFrom.bottomFaceIsCovering()) {
                uvBuffer[currentBufferOffset + 0] = uvCoordinates[40];
                uvBuffer[currentBufferOffset + 1] = uvCoordinates[41];
                uvBuffer[currentBufferOffset + 2] = uvCoordinates[42];
                uvBuffer[currentBufferOffset + 3] = uvCoordinates[43];
                uvBuffer[currentBufferOffset + 4] = uvCoordinates[44];
                uvBuffer[currentBufferOffset + 5] = uvCoordinates[45];
                uvBuffer[currentBufferOffset + 6] = uvCoordinates[46];
                uvBuffer[currentBufferOffset + 7] = uvCoordinates[47];
                
                currentBufferOffset += 8;
            }            
        }        
        
        return uvBuffer;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static StandardCubeShape getInstance() {
        if (null == instance) {
            instance = new StandardCubeShape();
        }
        
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initializeCenterAndUntranslatedVertices() {
        Vector3 origin = new Vector3(0.f, 0.f, 0.f);
        v = new Vector3[] {
            origin.subtractImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).subtract(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).subtract(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.addImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).add(blockHalfAxes[2]),
            origin.subtractImmutable(blockHalfAxes[0]).add(blockHalfAxes[1]).add(blockHalfAxes[2])
        };
        
        cubeCenter = new Vector3(0.5f * chunkXOfBlock + 0.25f, 
            0.5f * blockToCreateDataFrom.getYPositionOnParentIsland() + 0.25f, 
            0.5f * chunkZOfBlock + 0.25f);        
    }
    
    private void rotateCurrentVertices() {
        if (null != blockToCreateDataFrom.getCurrentRotation()) {
            rotatePoint(v[0], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[1], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[2], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[3], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[4], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[5], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[6], blockToCreateDataFrom.getCurrentRotation());
            rotatePoint(v[7], blockToCreateDataFrom.getCurrentRotation());
        }
    }
    
    private void translateVertices() {
        v[0].add(cubeCenter);
        v[1].add(cubeCenter);
        v[2].add(cubeCenter);
        v[3].add(cubeCenter);
        v[4].add(cubeCenter);
        v[5].add(cubeCenter);
        v[6].add(cubeCenter);
        v[7].add(cubeCenter);        
    }
    
    private void createVertexBufferWithNotCoveredFaces() {
        int amountOfUncoveredFaces = getAmountOfUncoveredFaces();
        lastUsedVertexBuffer = new float[4 * 3 * amountOfUncoveredFaces];
        
        if (amountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            int nextFreeBufferIndex = 0;
            
            if (!blockToCreateDataFrom.backFaceIsCovering()) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getBackFace(), nextFreeBufferIndex);
            }
            
            if (!blockToCreateDataFrom.rightFaceIsCovering()) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getRightFace(), nextFreeBufferIndex);
            }

            if (!blockToCreateDataFrom.frontFaceIsCovering()) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getFrontFace(), nextFreeBufferIndex);
            } 
            
            if (!blockToCreateDataFrom.leftFaceIsCovering()) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getLeftFace(), nextFreeBufferIndex);
            }   

            if (!blockToCreateDataFrom.topFaceIsCovering()) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getTopFace(), nextFreeBufferIndex);
            }
            
            if (!blockToCreateDataFrom.bottomFaceIsCovering()) {
                addVerticesToBufferForFaceType(blockToCreateDataFrom.getBottomFace(), nextFreeBufferIndex);
            }            
        }
    }
    
    private int getAmountOfUncoveredFaces() {
        int amountOfUncoveredFaces = 6;
        
        // Doing this with a face Iterator would be much cleaner, but Iterators are objects.
        // Thus creating a lot of them is performance heavy.
        if (blockToCreateDataFrom.backFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }
        
        if (blockToCreateDataFrom.bottomFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }
        
        if (blockToCreateDataFrom.frontFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }
        
        if (blockToCreateDataFrom.leftFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }
        
        if (blockToCreateDataFrom.rightFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }
        
        if (blockToCreateDataFrom.topFaceIsCovering()) {
            amountOfUncoveredFaces--;
        }     
        
        return amountOfUncoveredFaces;
    }
    
    private int addVerticesToBufferForFaceType(BlockFaceTypes faceType, int nextFreeBufferIndex) {
        switch(faceType) {
            case BACK: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[0].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[0].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[0].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[1].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[1].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[1].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[2].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[2].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[2].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[3].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[3].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[3].getZ();                
                break;
            }
            
            case RIGHT: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[1].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[1].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[1].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[4].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[4].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[4].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[6].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[6].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[6].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[2].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[2].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[2].getZ();                
                break;                
            }
            
            case FRONT: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[4].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[4].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[4].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[5].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[5].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[5].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[7].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[7].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[7].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[6].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[6].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[6].getZ();                
                break;                  
            }
            
            case LEFT: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[5].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[5].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[5].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[0].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[0].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[0].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[3].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[3].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[3].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[7].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[7].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[7].getZ();  
                break;
            }
            
            case TOP: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[2].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[2].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[2].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[6].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[6].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[6].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[7].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[7].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[7].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[3].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[3].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[3].getZ();                 
                break;
            }
            
            case BOTTOM: {
                lastUsedVertexBuffer[nextFreeBufferIndex] = v[0].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 1] = v[0].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 2] = v[0].getZ();
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 3] = v[5].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 4] = v[5].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 5] = v[5].getZ();  
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 6] = v[4].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 7] = v[4].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 8] = v[4].getZ();    
                
                lastUsedVertexBuffer[nextFreeBufferIndex + 9] = v[1].getX();
                lastUsedVertexBuffer[nextFreeBufferIndex + 10] = v[1].getY();
                lastUsedVertexBuffer[nextFreeBufferIndex + 11] = v[1].getZ();                
                break;
            }
        }
        
        return nextFreeBufferIndex + 12;
    }
    
    private void setIndicesToIndexBuffer(int[] indexBuffer, int alreadyPlacedIndices, int indexFaceOffset, int amountOfAlreadySetupIndicesInChunk) {
        indexBuffer[alreadyPlacedIndices] = 2 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;
        indexBuffer[alreadyPlacedIndices + 1] = 1 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;
        indexBuffer[alreadyPlacedIndices + 2] = 0 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;
        indexBuffer[alreadyPlacedIndices + 3] = 3 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;
        indexBuffer[alreadyPlacedIndices + 4] = 2 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;
        indexBuffer[alreadyPlacedIndices + 5] = 0 + indexFaceOffset + amountOfAlreadySetupIndicesInChunk;        
    }
    
    //</editor-fold>
}
