package de.ethasia.yaumr.interactors;

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
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof IslandMetaData) {
            return equals((IslandMetaData)o);
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return islandGUID.hashCode();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private boolean equals(IslandMetaData other) {
        return islandGUID.equals(other.islandGUID);
    }
    
    //</editor-fold>
}
