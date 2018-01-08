package de.ethasia.yaumr.core.tests.mocks;

import java.util.HashMap;
import java.util.Map;
import de.ethasia.yaumr.core.TerraformingTool;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;

/**
 *
 * @author R
 */
public class TerraformingToolMock extends TerraformingTool {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Map<String, Integer> calledMethodNamesWithCount = new HashMap<>();
    
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
    
    @Override
    public String getTypeName() {
        return "TERRAFORMING_TOOL_MOCK";
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
