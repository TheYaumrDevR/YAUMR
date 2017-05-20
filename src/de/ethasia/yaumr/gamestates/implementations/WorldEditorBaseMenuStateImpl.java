package de.ethasia.yaumr.gamestates.implementations;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.interfaces.GameEntryState;
import de.ethasia.yaumr.gamestates.interfaces.ManageIslandsState;
import de.ethasia.yaumr.gamestates.interfaces.WorldEditorBaseMenuState;

/**
 * Implementation of WorldEditorBaseMenuState.
 * 
 * @author R
 */
public class WorldEditorBaseMenuStateImpl extends YaumrGameState implements WorldEditorBaseMenuState {

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/WorldEditorBaseView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "worldEditorBaseMenu";
    }
    
    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().setGameState(this);
    }    

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void gotoWorldEditorState() {
    }

    @Override
    public void gotoIslandEditorState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(ManageIslandsState.class).startDisplaying();
    }

    @Override
    public void gotoPreviousState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(GameEntryState.class).startDisplaying();        
    }    
    
    //</editor-fold>    
}
