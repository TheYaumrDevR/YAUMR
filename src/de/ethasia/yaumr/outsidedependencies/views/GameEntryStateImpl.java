package de.ethasia.yaumr.outsidedependencies.views;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;
import de.ethasia.yaumr.ioadapters.interfaces.WorldEditorBaseMenuState;

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
        YaumrGame.getInstance().getFlyByCamera().setMoveSpeed(2.5f);
        YaumrGame.getInstance().getFlyByCamera().setEnabled(false);
    }    
    
    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(GameEntryState.class, this);
        YaumrGame.getInstance().setGameState(this);
        YaumrGame.getInstance().getViewPort().setBackgroundColor(new ColorRGBA(0, 0.863f, 0.663f, 1.0f));
    }    

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(GameEntryState.class);
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
