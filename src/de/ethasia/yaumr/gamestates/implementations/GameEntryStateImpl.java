package de.ethasia.yaumr.gamestates.implementations;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.interfaces.GameEntryState;

/**
 * Represents the controller for the state the game is in when launched.
 * 
 * @author Drawig
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
