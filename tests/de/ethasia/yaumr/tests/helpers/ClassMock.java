package de.ethasia.yaumr.tests.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides methods which make it possible to count how many times a certain
 * method has been called.
 * 
 * @author Noro
 */
public abstract class ClassMock {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Map<String, Integer> calledMethodNamesWithCallCount = new HashMap<>();
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static int getCallCounterForMethodName(String methodName) {
        if (null != calledMethodNamesWithCallCount.get(methodName)) {
            return calledMethodNamesWithCallCount.get(methodName);
        }
        
        return 0;
    }
    
    public static void resetMethodCallCounts() {
        calledMethodNamesWithCallCount = new HashMap<>();
    }
    
    //</editor-fold>
  
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    protected void incrementMockCounterForCalledMethod(String methodName) {
        if (calledMethodNamesWithCallCount.get(methodName) != null) {
            int newCallCount = calledMethodNamesWithCallCount.get(methodName) + 1;
            calledMethodNamesWithCallCount.put(methodName, newCallCount);
        } else {
            calledMethodNamesWithCallCount.put(methodName, 1);
        }
    }
    
    //</editor-fold>    
}
