package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.IslandMetaData;
import java.util.UUID;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public interface IslandRepository {
    
    public Stream<IslandMetaData> getMetadataOfAllAvailableIslands();
    public boolean createNewIsland(Island island, IslandMetaData islandMetaData);
    public Island findIslandByGuid(UUID guid);
}
