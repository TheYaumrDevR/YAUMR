package de.ethasia.yaumr.core.combat;

public class NotEnoughAttributePointsToAssignException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NotEnoughAttributePointsToAssignException() {
        super("Cannot assign more attribute points than available.");
    }
    
    //</editor-fold>
}