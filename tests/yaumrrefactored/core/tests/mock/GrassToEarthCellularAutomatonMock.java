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
    
    private final Map<String, Integer> calledMethodNamesWithCallCount;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public GrassToEarthCellularAutomatonMock() {
        calledMethodNamesWithCallCount = new HashMap<>();
    }
    
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
    
    public int getCallCounterForMethodName(String methodName) {
        if (null != calledMethodNamesWithCallCount.get(methodName)) {
            return calledMethodNamesWithCallCount.get(methodName);
        }
        
        return 0;
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
