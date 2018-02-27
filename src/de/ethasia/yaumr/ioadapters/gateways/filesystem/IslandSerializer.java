package de.ethasia.yaumr.ioadapters.gateways.filesystem;

import de.ethasia.yaumr.interactors.IslandMetaData;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.blocks.Block;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author R
 */
public class IslandSerializer {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<byte[]> byteBlocks;
    private int currentByteBlockCount;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public IslandSerializer() {
        byteBlocks = new LinkedList<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void addByteBlocksForIslandMetadata(IslandMetaData islandMetaData) {
        byte[] edgeLengthByte = new byte[]{(byte)(islandMetaData.getIslandEdgeLengthInBlocks() & 0xFF)};
        byteBlocks.add(edgeLengthByte);
        currentByteBlockCount += edgeLengthByte.length;    
    }
    
    public void addByteBlocksForIslandBlockData(Island island) {
        int sameBlockRepeatCount = 1;
        Block previousBlock = null;
        
        for (int k = 0; k < island.getHorizontalEdgeLengthOfIslandInBlocks(); k++) {
            for (int i = 0; i < island.getHorizontalEdgeLengthOfIslandInBlocks(); i++) {
                for (int j = 0; j < Island.HEIGHT_IN_BLOCKS; j++) {
                    int[] blockPosition = new int[] {i, j, k};
                    Block block = island.getBlockAt(blockPosition);
                    
                    if (currentBlockAndPreviousBlockAreEqual(block, previousBlock)) {
                        sameBlockRepeatCount++;
                    } else {
                        if (sameBlockRepeatCount > 1) {
                            adjustRepeatCountForLastBlockByteData(sameBlockRepeatCount);
                            sameBlockRepeatCount = 1;
                        }
                        
                        if (null != block) {
                            addByteDataForBlock(block, blockPosition);
                        }
                    }
                    
                    previousBlock = block;
                }
            }
        }
    }
    
    public byte[] getByteBufferForAddedData() {
        byte[] result = new byte[currentByteBlockCount];
        int i = 0;
        
        for (byte[] byteBlock : byteBlocks) {
            for (int j = 0; j < byteBlock.length; j++) {
                result[i] = byteBlock[j];
                i++;
            }
        }
        
        return result;
    }
    
    public void resetStoredBytes() {
        byteBlocks.clear();
        currentByteBlockCount = 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private byte[] convertUUIDToBytes(UUID toConvert) {
        byte[] result = new byte[Long.BYTES * 2];
        long leastSignificantBits = toConvert.getLeastSignificantBits();
        long mostSignificantBits = toConvert.getMostSignificantBits();
        
        for (int i = 0; i < Long.BYTES - 1; i++) {
            byte oneByte = (byte)(leastSignificantBits & 0xFF); 
            leastSignificantBits >>= 1;
            result[i] = oneByte;
        }
        
        for (int i = Long.BYTES; i < Long.BYTES * 2 - 1; i++) {
            byte oneByte = (byte)(mostSignificantBits & 0xFF); 
            mostSignificantBits >>= 1;
            result[i] = oneByte;
        }        
        
        return result;
    }
    
    private boolean currentBlockAndPreviousBlockAreEqual(Block currentBlock, Block previousBlock) {
        if (currentBlock == null && previousBlock != null) {
            return false;
        }
        
        if (currentBlock != null && previousBlock == null) {
            return false;
        }
        
        if (currentBlock == null && previousBlock == null) {
            return false;
        }
        
        if (currentBlock == previousBlock) {
            return true;
        }
        
        if (currentBlock.getBlockType() == previousBlock.getBlockType()) {
            return currentBlock.getBackFace() == previousBlock.getBackFace()
                    && currentBlock.getRightFace() == previousBlock.getRightFace();
        }
        
        return false;
    }
    
    private void addByteDataForBlock(Block block, int[] blockPosition) {
        byte byte1 = (byte)((blockPosition[0] << 1) & 0xFF);
        byte leftMostZPositionBit = (byte)((blockPosition[2] >> 6) & 0x1);
        byte1 |= leftMostZPositionBit;
        
        byte byte2 = (byte)((blockPosition[2] << 2) & 0xFF);
        byte leftMostYPositionBits = (byte)((blockPosition[1] >> 4) & 0x3);
        byte2 |= leftMostYPositionBits;
        
        byte byte3 = (byte)((blockPosition[1] << 4) & 0xFF);
        
        byte byte4 = (byte)((block.getBlockType().getValue() >> 1) & 0xFF);
        
        byte byte5 = (byte)((block.getBlockType().getValue() << 7) & 0xFF);
        byte shiftedBackFaceBits = (byte)((block.getBackFace().getValue() << 4) & 0xFF);
        byte shiftedRightFaceBits = (byte)((block.getRightFace().getValue() << 1) & 0xFF);
        byte5 |= shiftedBackFaceBits;
        byte5 |= shiftedRightFaceBits;
        
        byte[] byteOneToFive = new byte[] {byte1, byte2, byte3, byte4, byte5};
        
        float blockRotationQuatX = block.getCurrentRotation() != null ? block.getCurrentRotation().getX() : 0.f;
        float blockRotationQuatY = block.getCurrentRotation() != null ? block.getCurrentRotation().getY() : 0.f;
        float blockRotationQuatZ = block.getCurrentRotation() != null ? block.getCurrentRotation().getZ() : 0.f;
        float blockRotationQuatW = block.getCurrentRotation() != null ? block.getCurrentRotation().getW() : 0.f;
        
        byte[] rotationQuatXData = ByteBuffer.allocate(Float.BYTES).putFloat(blockRotationQuatX).array();
        byte[] rotationQuatYData = ByteBuffer.allocate(Float.BYTES).putFloat(blockRotationQuatY).array();
        byte[] rotationQuatZData = ByteBuffer.allocate(Float.BYTES).putFloat(blockRotationQuatZ).array();
        byte[] rotationQuatWData = ByteBuffer.allocate(Float.BYTES).putFloat(blockRotationQuatW).array();
        
        byteBlocks.add(byteOneToFive);
        byteBlocks.add(rotationQuatXData);
        byteBlocks.add(rotationQuatYData);
        byteBlocks.add(rotationQuatZData);
        byteBlocks.add(rotationQuatWData);
        
        currentByteBlockCount += byteOneToFive.length 
                + rotationQuatXData.length 
                + rotationQuatYData.length 
                + rotationQuatZData.length 
                + rotationQuatWData.length;
    }
    
    private void adjustRepeatCountForLastBlockByteData(int repeatCount) {
        byte[] lastBlockBytesOneToFive = byteBlocks.get(byteBlocks.size() - 5);
        
        byte repeatCountFirstClusterBits = (byte)((repeatCount >> 2) & 0xFF);
        lastBlockBytesOneToFive[2] |= repeatCountFirstClusterBits;
        
        byte repeatCountSecondClusterBits = (byte)((repeatCount << 6) & 0xFF);
        lastBlockBytesOneToFive[3] |= repeatCountSecondClusterBits;
    }
    
    //</editor-fold>
}
