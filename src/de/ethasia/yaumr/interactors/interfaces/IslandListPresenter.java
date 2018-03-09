package de.ethasia.yaumr.interactors.interfaces;

import de.ethasia.yaumr.interactors.IslandMetaData;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public interface IslandListPresenter {
    
    public void showAvailableIslandList(Stream<IslandMetaData> metaDataOfIslands);
}
