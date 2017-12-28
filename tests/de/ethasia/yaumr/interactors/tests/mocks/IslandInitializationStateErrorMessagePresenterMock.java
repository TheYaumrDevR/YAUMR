package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateErrorMessagePresenter;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author R
 */
public class IslandInitializationStateErrorMessagePresenterMock extends ClassMock implements IslandInitializationStateErrorMessagePresenter { 
    
    //<editor-fold defaultstate="collapsed" desc="Mocked Methods">
    
    @Override
    public void showErrorMessage(String errorMessage) {
        incrementMockCounterForCalledMethod("showErrorMessage");
    }    
    
    //</editor-fold>    
}
