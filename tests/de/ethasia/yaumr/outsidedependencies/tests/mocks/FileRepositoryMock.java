package de.ethasia.yaumr.outsidedependencies.tests.mocks;

import de.ethasia.yaumr.ioadapters.gateways.filesystem.FileExistsException;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.UserDefinedFileAttributesWithPath;
import de.ethasia.yaumr.ioadapters.gateways.interfaces.FileRepository;
import de.ethasia.yaumr.tests.helpers.ClassMock;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class FileRepositoryMock extends ClassMock implements FileRepository {

    @Override
    public Stream<UserDefinedFileAttributesWithPath> getApplicationDefinedFileAttributesWithNames(List<String> attributeNames, Path directoryOfFiles) throws IOException {
        incrementMockCounterForCalledMethod("getApplicationDefinedFileAttributesWithNames");
        return null;
    }

    @Override
    public void createFile(Path filePath) throws IOException, FileExistsException {
        incrementMockCounterForCalledMethod("createFile");
    }

    @Override
    public void deleteFile(Path filePath) throws IOException {
        incrementMockCounterForCalledMethod("deleteFile");
    }

    @Override
    public byte[] getFileContent(Path filePath) {
        incrementMockCounterForCalledMethod("getFileContent");
        return new byte[0];
    }

    @Override
    public void writeCustomFileAttributesToFile(Path filePath, Map<String, ByteBuffer> attributes) throws IOException {
        incrementMockCounterForCalledMethod("writeCustomFileAttributesToFile");
    }

    @Override
    public void writeContentToFile(Path filePath, byte[] content) throws IOException {
        incrementMockCounterForCalledMethod("writeContentToFile");
    }
    
}
