package de.ethasia.yaumr.ioadapters.gateways.filesystem;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Map;

/**
 *
 * @author R
 */
public class UserDefinedFileAttributesWithPath {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Path filePath;
    private Map<String, ByteBuffer> rawUserDefinedFileAttributes;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public void setFilePath(Path value) {
        filePath = value;
    }
    
    public void setRawUserDefinedFileAttributes(Map<String, ByteBuffer> value) {
        rawUserDefinedFileAttributes = value;
    }
    
    public Path getFilePath() {
        return filePath;
    }
    
    public Map<String, ByteBuffer> getRawUserDefinedFileAttributes() {
        return rawUserDefinedFileAttributes;
    }
    
    //</editor-fold>
}
