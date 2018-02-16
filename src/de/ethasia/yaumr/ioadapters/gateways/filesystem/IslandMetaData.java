package de.ethasia.yaumr.ioadapters.gateways.filesystem;

import java.util.UUID;

/**
 * Represents the metadata of an Island as saved on the file system.
 * 
 * @author R
 */
public class IslandMetaData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private String islandName;
    private UUID islandGUID;
    private int islandEdgeLengthInBlocks;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setIslandGUID(UUID value) {
        islandGUID = value;
    }
    
    public void setIslandName(String value) {
        islandName = value;
    }
    
    public void setIslandEdgeLengthInBlocks(int value) {
        islandEdgeLengthInBlocks = value;
    }
    
    public UUID getIslandGUID() {
        return islandGUID;
    }
    
    public String getIslandName() {
        return islandName;
    }
    
    public int getIslandEdgeLengthInBlocks() {
        return islandEdgeLengthInBlocks;
    }
    
    //</editor-fold>
}
