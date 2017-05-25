package de.ethasia.yaumr.gamestates.implementations;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.gamestates.interfaces.IslandEditorState;

/**
 * See IslandEditorState.
 * 
 * @author R
 */
public class IslandEditorStateImpl extends YaumrGameState implements IslandEditorState {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BlockPlacementController blockPlacementController;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/IslandCreationView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "islandCreationGUI";
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        YaumrGame.getInstance().getFlyByCamera().setEnabled(true);
        YaumrGame.getInstance().getFlyByCamera().setDragToRotate(false);
        
        blockPlacementController = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockPlacementController.class);
    }   
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        blockPlacementController.update(tpf);
    }

    @Override
    public void onStartScreen() {
        blockPlacementController.initialize(niftyScreen);
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0.529f, 0.808f, 0.922f, 1.0f));
    }    
    
    //</editor-fold>
}
