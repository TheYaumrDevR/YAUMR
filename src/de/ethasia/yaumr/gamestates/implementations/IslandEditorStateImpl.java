package de.ethasia.yaumr.gamestates.implementations;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.interfaces.IslandEditorState;

/**
 * See IslandEditorState.
 * 
 * @author R
 */
public class IslandEditorStateImpl extends YaumrGameState implements IslandEditorState {

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
    }     

    @Override
    public void onStartScreen() {
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
