package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsQuickbarPresenter;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author R
 */
public class TerraformingToolsQuickbarPresenterMock extends ClassMock implements TerraformingToolsQuickbarPresenter  {
    
    @Override
    public void highlightQuickbarPosition(int position) {
        incrementMockCounterForCalledMethod("highlightQuickbarPosition");
    }

    @Override
    public void showItemsOnQuickbar(TerraformingTool[] tools) {
        incrementMockCounterForCalledMethod("showItemsOnQuickbar");
    }
}
