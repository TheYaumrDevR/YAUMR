package de.ethasia.yaumr.ioadapters.gateways.filesystem.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.gateways.filesystem.IslandsOnFilesystem;
import de.ethasia.yaumr.ioadapters.gateways.interfaces.FileRepository;
import de.ethasia.yaumr.outsidedependencies.tests.mocks.FileRepositoryMock;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author R
 */
public class IslandFilesystemRepositoryTest {
    
    @BeforeClass
    public static void setUpClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.removeRegisteredImplementation(FileRepository.class); 
        dependencyResolver.registerImplementation(FileRepository.class, FileRepositoryMock.class);
    }
    
    @Test
    public void testGetMetadataOfAllAvailableIslands_mockDataProviderIsPresent_MetadataIsCorrectlyRetrieved() {
        IslandsOnFilesystem testCandidate = new IslandsOnFilesystem();
        testCandidate.getMetadataOfAllAvailableIslands();
    }
}
