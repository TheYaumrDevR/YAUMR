package yaumrrefactored.core.tests.mock;

import java.util.HashMap;
import java.util.Map;
import yaumrrefactored.core.blocks.Block;
import yaumrrefactored.core.blocks.BlockPosition;
import yaumrrefactored.core.Island;

/**
 *
 * @author R
 */
public class IslandMock extends Island {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Map<String, Integer> calledMethodNamesWithCallCount;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public IslandMock(int edgeLengthOfHorizontalPlaneInBlocks) {
        super(edgeLengthOfHorizontalPlaneInBlocks);
        calledMethodNamesWithCallCount = new HashMap<>();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mocked Methods">
    
    @Override
    public boolean placeBlockAt(Block block, BlockPosition position) {
        incrementMockCounterForCalledMethod("placeBlockAt");
        return true;
    } 
    
    @Override
    public boolean removeBlockAt(BlockPosition position) {
        incrementMockCounterForCalledMethod("removeBlockAt");
        return true;
    }
    
    @Override
    public boolean copyBlockTo(Block blockToCopy, BlockPosition position) {
        incrementMockCounterForCalledMethod("copyBlockTo");
        return true;
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
