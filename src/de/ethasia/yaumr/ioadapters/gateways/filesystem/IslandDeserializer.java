package de.ethasia.yaumr.ioadapters.gateways.filesystem;

import de.ethasia.yaumr.interactors.IslandMetaData;

/**
 *
 * @author R
 */
public class IslandDeserializer {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public IslandMetaData getIslandMetaDataFromByteBlocks(byte[] byteBlocks) {
        if (byteBlocks.length < 18) {
            throw new IllegalArgumentException("File metadata is corrupt. ");
        }
        
        for (int i = 17; i < byteBlocks.length; i++) {
            
        }
        
        return null;
    }
    
    //</editor-fold>
}
