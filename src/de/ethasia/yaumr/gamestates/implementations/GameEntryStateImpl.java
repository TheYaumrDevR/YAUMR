package de.ethasia.yaumr.gamestates.implementations;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.interfaces.GameEntryState;
import de.ethasia.yaumr.gamestates.interfaces.WorldEditorBaseMenuState;

/**
 * Represents the controller for the state the game is in when launched.
 * 
 * @author R
 */
public class GameEntryStateImpl extends YaumrGameState implements GameEntryState {

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/GameEntryView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "entryMenu";
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        YaumrGame.getInstance().getCamera().setFrustumPerspective(45, 1280.f / 720.f, 0.1f, 1000);
        YaumrGame.getInstance().getFlyByCamera().setEnabled(false);
    }    
    
    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0, 0.863f, 0.663f, 1.0f));
    }    

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }    
    
    @Override
    public void gotoSaveGameSelectionState() {
    }

    @Override
    public void gotoWorldEditorBaseState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(WorldEditorBaseMenuState.class).startDisplaying();
    }

    @Override
    public void gotoOptionsState() {
    }

    @Override
    public void quitGame() {
        System.exit(0);
    }    
    
    //</editor-fold>
}
