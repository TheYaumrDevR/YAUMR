package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockFaceTypes;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.Quaternion;
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
    
    private boolean backFaceOfBlockIsCovered;
    private boolean rightFaceOfBlockIsCovered;
    private boolean frontFaceOfBlockIsCovered;
    private boolean leftFaceOfBlockIsCovered;
    private boolean topFaceOfBlockIsCovered;
    private boolean bottomFaceOfBlockIsCovered;
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private StandardCubeShape() {}
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    @Override
    public void setBlockToCreateDataFrom(Block value) {
        blockToCreateDataFrom = value;
    }
    
    @Override
    public void setBackFaceOfBlockIsCovered(boolean value) {
        backFaceOfBlockIsCovered = value;
    }
    
    @Override
    public void setRightFaceOfBlockIsCovered(boolean value) {
        rightFaceOfBlockIsCovered = value;
    }
    
    @Override
    public void setFrontFaceOfBlockIsCovered(boolean value) {
        frontFaceOfBlockIsCovered = value;
    }
    
    @Override
    public void setLeftFaceOfBlockIsCovered(boolean value) {
        leftFaceOfBlockIsCovered = value;
    }
    
    @Override
    public void setTopFaceOfBlockIsCovered(boolean value) {
        topFaceOfBlockIsCovered = value;
    }
    
    @Override
    public void setBottomFaceOfBlockIsCovered(boolean value) {
        bottomFaceOfBlockIsCovered = value;
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
    public int[] getVertexIndicesForLastCreatedVertices(int indexBlockOffset) {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        int[] indexBuffer = new int[lastAmountOfUncoveredFaces * 6];
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            int indexFaceOffset = 0;
            int alreadyPlacedIndices = 0;

            if (!backFaceOfBlockIsCovered) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;
            }
            
            if (!rightFaceOfBlockIsCovered) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }
            
            if (!frontFaceOfBlockIsCovered) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }

            if (!leftFaceOfBlockIsCovered) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }   
            
            if (!topFaceOfBlockIsCovered) {
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);                
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            } 
            
            if (!bottomFaceOfBlockIsCovered) {                
                setIndicesToIndexBuffer(indexBuffer, alreadyPlacedIndices, indexFaceOffset, indexBlockOffset);
                
                alreadyPlacedIndices += 6;
                indexFaceOffset += 4;                
            }            
        }
        
        return indexBuffer;
    }
    
    @Override
    public int getHighestIndexNumberForCurrentBlock() {
        int highestIndexNumber = 0;
        
        if (!backFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }    
        
        if (!rightFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }

        if (!frontFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }

        if (!leftFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }  
        
        if (!topFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }

        if (!bottomFaceOfBlockIsCovered) {
            highestIndexNumber += 4;
        }        
        
        return highestIndexNumber;
    }

    @Override
    public float[] getNormalsForLastCreatedVertices() {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        float[] normalsBuffer = new float[lastAmountOfUncoveredFaces * 12];  
        int currentBufferOffset = 0;
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            if (!backFaceOfBlockIsCovered) {
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
                
            if (!rightFaceOfBlockIsCovered) {
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
                
            if (!frontFaceOfBlockIsCovered) {
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
                
            if (!leftFaceOfBlockIsCovered) {
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
                
            if (!topFaceOfBlockIsCovered) {
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
                
            if (!bottomFaceOfBlockIsCovered) {
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
        }
        
        return normalsBuffer;
    }

    @Override
    public float[] getUVCoordinatesForLastCreatedVertices() {
        int lastAmountOfUncoveredFaces = lastUsedVertexBuffer.length / 12; // 4 vectors * 3 vector elements
        float[] uvBuffer = new float[lastAmountOfUncoveredFaces * 8];  
        int currentBufferOffset = 0;
        
        if (lastAmountOfUncoveredFaces > 0 && null != blockToCreateDataFrom) {
            float[] uvCoordinates = BlockTypeToTextureCoordinatesMapper.getUVCoordinatesForBlockType(blockToCreateDataFrom.getBlockType());
            
            if (!backFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getBackFace(), uvCoordinates);                
                currentBufferOffset += 8;
            }
            
            if (!rightFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getRightFace(), uvCoordinates);                
                currentBufferOffset += 8;
            }

            if (!frontFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getFrontFace(), uvCoordinates);                
                currentBufferOffset += 8;
            }   
            
            if (!leftFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getLeftFace(), uvCoordinates);                
                currentBufferOffset += 8;
            }

            if (!topFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getTopFace(), uvCoordinates);                
                currentBufferOffset += 8;
            } 
            
            if (!bottomFaceOfBlockIsCovered) {
                addUVCoordinatesToNextFreeIndexBasedOnFaceType(uvBuffer, currentBufferOffset, blockToCreateDataFrom.getBottomFace(), uvCoordinates);                
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
            Quaternion rotation = blockToCreateDataFrom.getCurrentRotation();
            v[0].transform(rotation);
            v[1].transform(rotation);
            v[2].transform(rotation);
            v[3].transform(rotation);
            v[4].transform(rotation);
            v[5].transform(rotation);
            v[6].transform(rotation);
            v[7].transform(rotation);
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
            
            if (!backFaceOfBlockIsCovered) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getBackFace(), nextFreeBufferIndex);
            }
            
            if (!rightFaceOfBlockIsCovered) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getRightFace(), nextFreeBufferIndex);
            }

            if (!frontFaceOfBlockIsCovered) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getFrontFace(), nextFreeBufferIndex);
            } 
            
            if (!leftFaceOfBlockIsCovered) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getLeftFace(), nextFreeBufferIndex);
            }   

            if (!topFaceOfBlockIsCovered) {
                nextFreeBufferIndex = addVerticesToBufferForFaceType(blockToCreateDataFrom.getTopFace(), nextFreeBufferIndex);
            }
            
            if (!bottomFaceOfBlockIsCovered) {
                addVerticesToBufferForFaceType(blockToCreateDataFrom.getBottomFace(), nextFreeBufferIndex);
            }            
        }
    }
    
    private int getAmountOfUncoveredFaces() {
        int amountOfUncoveredFaces = 6;
        
        // Doing this with a face Iterator would be much cleaner, but Iterators are objects.
        // Thus creating a lot of them is performance heavy.
        if (backFaceOfBlockIsCovered) {
            amountOfUncoveredFaces--;
        }
        
        if (bottomFaceOfBlockIsCovered) {
            amountOfUncoveredFaces--;
        }
        
        if (frontFaceOfBlockIsCovered) {
            amountOfUncoveredFaces--;
        }
        
        if (leftFaceOfBlockIsCovered) {
            amountOfUncoveredFaces--;
        }
        
        if (rightFaceOfBlockIsCovered) {
            amountOfUncoveredFaces--;
        }
        
        if (topFaceOfBlockIsCovered) {
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
    
    private void setIndicesToIndexBuffer(int[] indexBuffer, int alreadyPlacedIndices, int indexFaceOffset, int indexBlockOffset) {
        indexBuffer[alreadyPlacedIndices] = 2 + indexFaceOffset + indexBlockOffset;
        indexBuffer[alreadyPlacedIndices + 1] = 1 + indexFaceOffset + indexBlockOffset;
        indexBuffer[alreadyPlacedIndices + 2] = 0 + indexFaceOffset + indexBlockOffset;
        indexBuffer[alreadyPlacedIndices + 3] = 3 + indexFaceOffset + indexBlockOffset;
        indexBuffer[alreadyPlacedIndices + 4] = 2 + indexFaceOffset + indexBlockOffset;
        indexBuffer[alreadyPlacedIndices + 5] = 0 + indexFaceOffset + indexBlockOffset;        
    }
    
    private void addUVCoordinatesToNextFreeIndexBasedOnFaceType(float[] uvBuffer, int nextFreeBufferIndex, BlockFaceTypes face, float[] wholeBlockUVData) {
        switch (face) {
            case BACK:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[0];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[1];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[2];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[3];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[4];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[5];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[6];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[7];                
                break;
            case RIGHT:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[8];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[9];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[10];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[11];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[12];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[13];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[14];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[15];                 
                break;
            case FRONT:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[16];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[17];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[18];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[19];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[20];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[21];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[22];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[23];                 
                break;
            case LEFT:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[24];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[25];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[26];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[27];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[28];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[29];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[30];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[31];                 
                break;
            case TOP:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[32];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[33];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[34];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[35];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[36];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[37];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[38];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[39];                 
                break;
            case BOTTOM:
                uvBuffer[nextFreeBufferIndex + 0] = wholeBlockUVData[40];
                uvBuffer[nextFreeBufferIndex + 1] = wholeBlockUVData[41];
                uvBuffer[nextFreeBufferIndex + 2] = wholeBlockUVData[42];
                uvBuffer[nextFreeBufferIndex + 3] = wholeBlockUVData[43];
                uvBuffer[nextFreeBufferIndex + 4] = wholeBlockUVData[44];
                uvBuffer[nextFreeBufferIndex + 5] = wholeBlockUVData[45];
                uvBuffer[nextFreeBufferIndex + 6] = wholeBlockUVData[46];
                uvBuffer[nextFreeBufferIndex + 7] = wholeBlockUVData[47];                 
                break;
        }
    }
    
    //</editor-fold>
}
