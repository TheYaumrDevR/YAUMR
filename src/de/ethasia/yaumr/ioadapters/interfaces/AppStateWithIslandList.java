package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.interactors.IslandMetaData;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public interface AppStateWithIslandList {
    
    public void showIslandList(Stream<IslandMetaData> islandList);
}
