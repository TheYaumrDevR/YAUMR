package de.ethasia.yaumr.outsidedependencies.views;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.GameEntryState;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;
import de.ethasia.yaumr.ioadapters.interfaces.WorldEditorBaseMenuState;

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
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(WorldEditorBaseMenuState.class, this);
        YaumrGame.getInstance().setGameState(this);
    }    

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(WorldEditorBaseMenuState.class);
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
