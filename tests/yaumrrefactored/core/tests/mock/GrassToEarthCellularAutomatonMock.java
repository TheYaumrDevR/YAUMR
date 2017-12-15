package yaumrrefactored.core.tests.mock;

import java.util.HashMap;
import java.util.Map;
import yaumrrefactored.core.BlockPosition;
import yaumrrefactored.core.GrassToEarthCellularAutomatonImpl;

/**
 *
 * @author R
 */
public class GrassToEarthCellularAutomatonMock extends GrassToEarthCellularAutomatonImpl {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Map<String, Integer> calledMethodNamesWithCallCount = new HashMap<>();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mocked Methods">
    
    @Override
    public void tick(long timeSinceLastTickInMS) {
        incrementMockCounterForCalledMethod("tick");
    }  
    
    @Override
    public void setChangedPosition(BlockPosition changedPosition) {
        incrementMockCounterForCalledMethod("setChangedPosition");
    }    
    
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
    
    private void incrementMockCounterForCalledMethod(String methodName) {
        if (calledMethodNamesWithCallCount.get(methodName) != null) {
            int newCallCount = calledMethodNamesWithCallCount.get(methodName) + 1;
            calledMethodNamesWithCallCount.put(methodName, newCallCount);
        } else {
            calledMethodNamesWithCallCount.put(methodName, 1);
        }
    }
    
    //</editor-fold>    
}
