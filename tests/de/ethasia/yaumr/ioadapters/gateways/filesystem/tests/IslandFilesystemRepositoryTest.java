package de.ethasia.yaumr.ioadapters.gateways.filesystem.tests;

import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandFilesystemRepository;
import org.junit.Test;

/**
 *
 * @author R
 */
public class IslandFilesystemRepositoryTest {
    
    @Test
    public void testGetMetadataOfAllAvailableIslands_mockDataProviderIsPresent_MetadataIsCorrectlyRetrieved() {
        IslandFilesystemRepository testCandidate = new IslandFilesystemRepository();
        testCandidate.getMetadataOfAllAvailableIslands();
    }
}
