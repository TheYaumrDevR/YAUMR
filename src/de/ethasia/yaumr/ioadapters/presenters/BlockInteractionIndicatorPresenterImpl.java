package de.ethasia.yaumr.ioadapters.presenters;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.blocks.BlockPosition;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.ioadapters.interfaces.BlockInteractionIndicatorPresenter;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;

/**
 * Represents a Presenter which is responsible for displaying to the user which Block is pointed on.
 * 
 * @author R
 */
public class BlockInteractionIndicatorPresenterImpl implements BlockInteractionIndicatorPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockPosition lastPointingPosition;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void presentPointingIndicator(float pointingPositionX, float pointingPositionY, float pointingPositionZ) {
        IslandManipulationFacade islandManipulationFacade = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandManipulationFacade.class);
        BlockPosition blockPointingPosition = islandManipulationFacade.getBlockPositionOnCurrentIslandForInteractionVector(pointingPositionX, pointingPositionY, pointingPositionZ);
        IslandEditorState islandEditorState = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandEditorState.class);
        
        if (null == islandEditorState) {
            return;
        }
        
        if (null != blockPointingPosition) {
            if (null == lastPointingPosition || !lastPointingPosition.equals(blockPointingPosition)) {
                lastPointingPosition = blockPointingPosition;
                
                float blockXPoint = 0.25f + blockPointingPosition.x * 0.5f;
                float blockYPoint = 0.25f + blockPointingPosition.y * 0.5f;
                float blockZPoint = 0.25f + blockPointingPosition.z * 0.5f;
                
                InteractionVector indicatorPosition = new InteractionVector(blockXPoint, blockYPoint, blockZPoint);
                islandEditorState.displayBlockPointingIndicator(indicatorPosition);
            }
        } else if (null != lastPointingPosition) {
            lastPointingPosition = null;
            islandEditorState.removeBlockPointingIndicator();
        }
    }
    
    //</editor-fold>
}
