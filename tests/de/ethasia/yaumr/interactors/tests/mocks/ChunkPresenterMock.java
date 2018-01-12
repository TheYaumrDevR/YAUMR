package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author R
 */
public class ChunkPresenterMock extends ClassMock implements ChunkPresenter {

    @Override
    public void setChangedPosition(int[] position) {
        incrementMockCounterForCalledMethod("setChangedPosition");
    }

    @Override
    public void presentChunksForChangedPositions(Island island) {
        incrementMockCounterForCalledMethod("presentChunksForChangedPositions");
    }
}
