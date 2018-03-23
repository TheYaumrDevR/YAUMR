package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.interactors.interfaces.MessageConfirmationAction;

/**
 *
 * @author 
 */
public interface AppStateWithWarningMessages {
        
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void showConfirmationWarningMessage(String messageText, MessageConfirmationAction confirmationAction);
    public void showNonConfirmationWarningMessage(String messageText);
        
    //</editor-fold>
}
