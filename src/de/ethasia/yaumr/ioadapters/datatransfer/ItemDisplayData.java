package de.ethasia.yaumr.ioadapters.datatransfer;

/**
 * Represents a data transfer object which holds all information to show an item in the GUI.
 * 
 * @author R
 */
public class ItemDisplayData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private String itemImagePath;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setItemImagePath(String value) {
        itemImagePath = value;
    }
    
    public String getItemImagePath() {
        return itemImagePath;
    }
    
    //</editor-fold>
}
