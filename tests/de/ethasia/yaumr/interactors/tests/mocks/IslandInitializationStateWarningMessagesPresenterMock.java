package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.interactors.interfaces.IslandInitializationStateWarningMessagesPresenter;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author R
 */
public class IslandInitializationStateWarningMessagesPresenterMock extends ClassMock implements IslandInitializationStateWarningMessagesPresenter {  
    
    //<editor-fold defaultstate="collapsed" desc="Mocked Methods">
    
    @Override
    public void showNonConfirmationWarning(String message) {
        incrementMockCounterForCalledMethod("showNonConfirmationWarning");
    }

    @Override
    public void showConfirmationWarning(String message) {
        incrementMockCounterForCalledMethod("showConfirmationWarning");
    }    
    
    //</editor-fold>
        
}
