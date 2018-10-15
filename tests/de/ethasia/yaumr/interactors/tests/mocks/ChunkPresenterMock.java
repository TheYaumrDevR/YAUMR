package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.tests.helpers.ClassMock;
import java.util.HashMap;

/**
 *
 * @author R
 */
public class ChunkPresenterMock extends ClassMock implements ChunkPresenter {
    
    private static Island lastIslandPassedToPresentAllChunksInIsland;
    
    public static Island getParameterPassedToPresentAllChunksInIsland() {
        return lastIslandPassedToPresentAllChunksInIsland;
    }
    
    public static void resetMethodCallCounts() {
        lastIslandPassedToPresentAllChunksInIsland = null;
        calledMethodNamesWithCallCount = new HashMap<>();
    }

    @Override
    public void setChangedPosition(int[] position) {
        incrementMockCounterForCalledMethod("setChangedPosition");
    }

    @Override
    public void presentChunksForChangedPositions(Island island) {
        incrementMockCounterForCalledMethod("presentChunksForChangedPositions");
    }
    
    @Override
    public void presentAllChunksInIsland(Island island) {
        incrementMockCounterForCalledMethod("presentAllChunksInIsland");
        lastIslandPassedToPresentAllChunksInIsland = island;
    }
}
