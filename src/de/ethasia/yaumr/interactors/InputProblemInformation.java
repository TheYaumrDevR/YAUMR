package de.ethasia.yaumr.interactors;

/**
 * Represents information returned to a client class which passes user input
 * when this user input is invalid.
 * 
 * @author R
 */
public class InputProblemInformation {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int SEVERITY_LEVEL_FATAL_ERROR = 1;
    private static final int SEVERITY_LEVEL_ERROR = 2;
    private static final int SEVERITY_LEVEL_WARNING = 3;
    private static final int SEVERITY_LEVEL_NOTICE = 4;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int severityLevel;
    private String userMessage;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public String getMessage() {
        return userMessage;
    }
    
    public boolean isFatalError() {
        return severityLevel == SEVERITY_LEVEL_FATAL_ERROR;
    }
    
    public boolean isError() {
        return severityLevel == SEVERITY_LEVEL_ERROR;
    }   
    
    public boolean isWarning() {
        return severityLevel == SEVERITY_LEVEL_WARNING;
    }  
    
    public boolean isNotice() {
        return severityLevel == SEVERITY_LEVEL_NOTICE;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Creation">
    
    private InputProblemInformation() {}
    
    public static InputProblemInformation createFatalError(String message) {
        return createProblemInformationWithMessageAndLevel(message, SEVERITY_LEVEL_FATAL_ERROR);
    }
    
    public static InputProblemInformation createError(String message) {
        return createProblemInformationWithMessageAndLevel(message, SEVERITY_LEVEL_ERROR);
    }
   
    public static InputProblemInformation createWarning(String message) {
        return createProblemInformationWithMessageAndLevel(message, SEVERITY_LEVEL_WARNING);
    }

    public static InputProblemInformation createNotice(String message) {
        return createProblemInformationWithMessageAndLevel(message, SEVERITY_LEVEL_NOTICE);
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static InputProblemInformation createProblemInformationWithMessageAndLevel(String message, int severityLevel) {
        InputProblemInformation result = new InputProblemInformation();     
        result.userMessage = message;
        result.severityLevel = severityLevel;
        
        return result;        
    }
    
    //</editor-fold>
}
