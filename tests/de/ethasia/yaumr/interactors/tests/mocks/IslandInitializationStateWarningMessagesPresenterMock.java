package de.ethasia.yaumr.interactors.tests.mocks;

import de.ethasia.yaumr.tests.helpers.ClassMock;
import de.ethasia.yaumr.interactors.interfaces.WarningMessagePresenter;

/**
 *
 * @author R
 */
public class IslandInitializationStateWarningMessagesPresenterMock extends ClassMock implements WarningMessagePresenter {  
    
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
