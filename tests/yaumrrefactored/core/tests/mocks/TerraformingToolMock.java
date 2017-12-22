package yaumrrefactored.core.tests.mocks;

import java.util.HashMap;
import java.util.Map;
import yaumrrefactored.core.TerraformingTool;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.interfaces.IslandManipulationFacade;

/**
 *
 * @author R
 */
public class TerraformingToolMock extends TerraformingTool {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static final Map<String, Integer> calledMethodNamesWithCount = new HashMap<>();
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void rotateOnX() {
        incrementMethodCallCount("rotateOnX");
    }

    @Override
    public void rotateOnY() {
        incrementMethodCallCount("rotateOnY");
    }

    @Override
    public void rotateOnZ() {
        incrementMethodCallCount("rotateOnZ");
    }

    @Override
    public void interactWithIsland(IslandManipulationFacade islandManipulationFacade, BlockPosition position) {
        incrementMethodCallCount("interactWithIsland");
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void incrementMethodCallCount(String methodName) {
        if (null != calledMethodNamesWithCount.get(methodName)) {
            Integer callcount = calledMethodNamesWithCount.get(methodName) + 1;
            calledMethodNamesWithCount.replace(methodName, callcount);
        } else {
            calledMethodNamesWithCount.put(methodName, 1);
        }
    }
    
    public static int getMethodCallCount(String methodName) {
        if (null != calledMethodNamesWithCount.get(methodName)) {
            Integer callcount = calledMethodNamesWithCount.get(methodName);
            return callcount;
        }
        
        return 0;
    }
    
    public static void resetCalledMethodCounters() {
        calledMethodNamesWithCount.clear();
    }
    
    //</editor-fold>
}
