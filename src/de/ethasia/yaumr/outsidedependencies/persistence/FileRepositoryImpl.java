package de.ethasia.yaumr.outsidedependencies.persistence;

import de.ethasia.yaumr.ioadapters.gateways.filesystem.FileExistsException;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.UserDefinedFileAttributesWithPath;
import de.ethasia.yaumr.ioadapters.gateways.interfaces.FileRepository;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class FileRepositoryImpl implements FileRepository {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private List<String> lastSearchedFileAttributeNames;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FileRepository overrides">
    
    @Override
    public Stream<UserDefinedFileAttributesWithPath> getApplicationDefinedFileAttributesWithNames(List<String> attributeNames, Path directoryOfFiles) throws IOException {
        lastSearchedFileAttributeNames = attributeNames;
        Stream<UserDefinedFileAttributesWithPath> result = Files.walk(directoryOfFiles)
            .map(new Function<Path, UserDefinedFileAttributesWithPath>() {
                    
                @Override
                public UserDefinedFileAttributesWithPath apply(Path filePath) {
                    return mapUserSpecificFileAttributesForPath(filePath);
                }
            });
        
        return result;
    }

    @Override
    public void createFile(Path filePath) throws IOException, FileExistsException {
        if (Files.exists(filePath)) {
            throw new FileExistsException("The file at the given path already exists.");
        }
        
        Files.createFile(filePath);
    }

    @Override
    public void deleteFile(Path filePath) throws IOException {
        Files.delete(filePath);
    }

    @Override
    public byte[] getFileContent(Path filePath) {
        return null;
    }

    @Override
    public void writeCustomFileAttributesToFile(Path filePath, Map<String, ByteBuffer> attributes) throws IOException {
        UserDefinedFileAttributeView attributeView = Files.getFileAttributeView(filePath, UserDefinedFileAttributeView.class);
        
        for (String attributeName : attributes.keySet()) {
            attributeView.write(attributeName, attributes.get(attributeName));            
        }
    }

    @Override
    public void writeContentToFile(Path filePath, byte[] content) throws IOException {
        Files.write(filePath, content);
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private UserDefinedFileAttributesWithPath mapUserSpecificFileAttributesForPath(Path filePath) {
        UserDefinedFileAttributesWithPath result = new UserDefinedFileAttributesWithPath();
        
        if (Files.isRegularFile(filePath)) {
            UserDefinedFileAttributeView attributeView = Files.getFileAttributeView(filePath, UserDefinedFileAttributeView.class);
            Map<String, ByteBuffer> userDefinedAttributesWithNames = new HashMap<>();
            
            for (String attributeName : lastSearchedFileAttributeNames) {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(attributeView.size(attributeName));
                    attributeView.read(attributeName, byteBuffer);
                    userDefinedAttributesWithNames.put(attributeName, byteBuffer);
                } catch (IOException ex) {
                    break;
                }
            }
            
            result.setFilePath(filePath);
            result.setRawUserDefinedFileAttributes(userDefinedAttributesWithNames);
        }
        
        return result;
    }
    
    //</editor-fold>
}
