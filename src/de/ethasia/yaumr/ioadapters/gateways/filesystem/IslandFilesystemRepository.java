package de.ethasia.yaumr.ioadapters.gateways.filesystem;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.IslandMetaData;
import de.ethasia.yaumr.interactors.interfaces.ErrorMessagePresenter;
import de.ethasia.yaumr.interactors.interfaces.IslandRepository;
import de.ethasia.yaumr.ioadapters.gateways.interfaces.FileRepository;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class IslandFilesystemRepository implements IslandRepository {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String ISLAND_NAME_FILE_ATTRIBUTE_NAME = "user.islandName";
    private static final String ISLAND_GUID_FILE_ATTRIBUTE_NAME = "user.islandGUID";
    
    private static final Path ISLAND_FILES_BASE_PATH = Paths.get(
            System.getProperty("user.home"), 
            YaumrGame.GAME_NAME_BASE,
            "Islands");  
    
    private static final Path CREATED_APP_DATA_DIRECTORY_PATH = Paths.get(
            System.getProperty("user.home"), 
            YaumrGame.GAME_NAME_BASE);  
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private FileRepository fileRepository;
    private Map<UUID, Path> oldIslandGUIDToFilePath;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    static {
        if (!Files.exists(CREATED_APP_DATA_DIRECTORY_PATH)) {
            try {
                Files.createDirectory(CREATED_APP_DATA_DIRECTORY_PATH);
            } catch (IOException ex) {
                showErrorMessage("Could not create application data directory, reason: " + ex.getMessage());
            }
        }
        
        if (!Files.exists(ISLAND_FILES_BASE_PATH)) {
            try {
                Files.createDirectory(ISLAND_FILES_BASE_PATH);
            } catch (IOException ex) {
                showErrorMessage("Could not create island data directory, reason: " + ex.getMessage());                
            }
        }
    }
    
    public IslandFilesystemRepository() {
        oldIslandGUIDToFilePath = new HashMap<>();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IslandRepository overrides">
    
    @Override
    public Stream<IslandMetaData> getMetadataOfAllAvailableIslands() {
        oldIslandGUIDToFilePath.clear();
        
        List<String> fileAttributesToLookFor = new LinkedList<>();
        fileAttributesToLookFor.add(ISLAND_NAME_FILE_ATTRIBUTE_NAME);
        fileAttributesToLookFor.add(ISLAND_GUID_FILE_ATTRIBUTE_NAME);
        Stream<UserDefinedFileAttributesWithPath> metadataOfAllIslandFiles;
        
        try {
            metadataOfAllIslandFiles = fileRepository.getApplicationDefinedFileAttributesWithNames(fileAttributesToLookFor, ISLAND_FILES_BASE_PATH);
        } catch (IOException ex) {
            // Show error window. 
            return null;
        }
        
        Stream<IslandMetaData> result = metadataOfAllIslandFiles.map(new Function<UserDefinedFileAttributesWithPath, IslandMetaData>() {
                    
            @Override
            public IslandMetaData apply(UserDefinedFileAttributesWithPath fileAttributesWithPath) {
                return mapFileAttributesToIslandMetaData(fileAttributesWithPath);
            }
        });
        
        return result;
    }

    @Override
    public void createNewIsland(Island island, IslandMetaData islandMetaData) {
        IslandSerializer serializer = new IslandSerializer();
        serializer.addByteBlocksForIslandBlockData(island);
        
        Map<String, ByteBuffer> islandMetadataForFileAttributes = new HashMap<>();
        islandMetadataForFileAttributes.put(ISLAND_NAME_FILE_ATTRIBUTE_NAME, Charset.forName("UTF-8").encode(islandMetaData.getIslandName()));
        islandMetadataForFileAttributes.put(ISLAND_GUID_FILE_ATTRIBUTE_NAME, getByteBufferFromUUID(islandMetaData.getIslandGUID()));
        
        byte[] serializedData = serializer.getByteBufferForAddedData();
        
        Path savePath = getFreeFilePathForIslandName(islandMetaData.getIslandName());
        
        try {
            fileRepository.createFile(savePath);
            fileRepository.writeCustomFileAttributesToFile(savePath, islandMetadataForFileAttributes);
            fileRepository.writeContentToFile(savePath, serializedData);
        } catch (IOException ex) {
            showErrorMessage("Could not save island, reason: " + ex.getMessage());
        } catch (FileExistsException ex) {
            showErrorMessage("Could not save island, because " + savePath.toString() + " already exists.");
        }
    }

    @Override
    public Island findIslandByGuid(UUID guid) {
        return null;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private IslandMetaData mapFileAttributesToIslandMetaData(UserDefinedFileAttributesWithPath fileAttributesWithPath) {
        Map<String, ByteBuffer> fileAttributes = fileAttributesWithPath.getRawUserDefinedFileAttributes();
        Path filePath = fileAttributesWithPath.getFilePath();
        
        UUID islandGUID = getUUIDFromByteBuffer(fileAttributes.get(ISLAND_GUID_FILE_ATTRIBUTE_NAME));
        
        IslandMetaData islandMetaData = new IslandMetaData();
        islandMetaData.setIslandName(getIslandNameFromByteBuffer(fileAttributes.get(ISLAND_NAME_FILE_ATTRIBUTE_NAME)));
        islandMetaData.setIslandGUID(islandGUID);  
        
        oldIslandGUIDToFilePath.put(islandGUID, filePath);
        
        return islandMetaData;
    }
    
    private String getIslandNameFromByteBuffer(ByteBuffer bytes) {
        if (null != bytes) {
            return StandardCharsets.UTF_8.decode(bytes).toString();
        }
        
        return "";
    }
    
    private UUID getUUIDFromByteBuffer(ByteBuffer bytes) {
        if (null != bytes) {
            long firstUUIDPart = bytes.getLong();
            long secondUUIDPart = bytes.getLong();
            
            return new UUID(firstUUIDPart, secondUUIDPart);
        }
        
        return null;
    }
    
    private ByteBuffer getByteBufferFromUUID(UUID guid) {
        ByteBuffer result = ByteBuffer.allocate(16);
        
        result.putLong(guid.getMostSignificantBits());
        result.putLong(guid.getLeastSignificantBits());
        
        return result;
    }
    
    private Path getFreeFilePathForIslandName(String islandName) {
        Path filePath = Paths.get(ISLAND_FILES_BASE_PATH.toString(), islandName + ".is");
        int i = 1;
        
        while (Files.exists(filePath)) {
            filePath = Paths.get(ISLAND_FILES_BASE_PATH.toString(), islandName + " (" + i + ").is");
            i++;
        }
        
        return filePath;
    }
    
    private static void showErrorMessage(String message) {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        ErrorMessagePresenter errorMessagePresenter = dependencyResolver.getImplementationInstance(ErrorMessagePresenter.class);
        errorMessagePresenter.showErrorMessage(message);         
    }
    
    //</editor-fold>
}
