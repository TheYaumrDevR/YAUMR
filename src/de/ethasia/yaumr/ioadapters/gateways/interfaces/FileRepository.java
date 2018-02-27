package de.ethasia.yaumr.ioadapters.gateways.interfaces;

import de.ethasia.yaumr.ioadapters.gateways.filesystem.FileExistsException;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.UserDefinedFileAttributesWithPath;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Provides methods for file operations.
 * 
 * @author R
 */
public interface FileRepository {
    
    public Stream<UserDefinedFileAttributesWithPath> getApplicationDefinedFileAttributesWithNames(List<String> attributeNames, Path directoryOfFiles) throws IOException;
    public void createFile(Path filePath) throws IOException, FileExistsException;
    public void deleteFile(Path filePath) throws IOException;
    public byte[] getFileContent(Path filePath);
    public void writeCustomFileAttributesToFile(Path filePath, Map<String, ByteBuffer> attributes) throws IOException;
    public void writeContentToFile(Path filePath, byte[] content) throws IOException;
}
