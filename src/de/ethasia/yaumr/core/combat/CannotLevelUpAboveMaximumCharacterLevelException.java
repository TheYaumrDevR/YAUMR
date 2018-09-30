package de.ethasia.yaumr.core.combat;

public class CannotLevelUpAboveMaximumCharacterLevelException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CannotLevelUpAboveMaximumCharacterLevelException(int maximumCharacterLevel) {
        super("Cannot level over the maximum character level: " + maximumCharacterLevel);
    }
    
    //</editor-fold>
}