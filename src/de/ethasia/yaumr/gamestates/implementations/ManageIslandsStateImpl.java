package de.ethasia.yaumr.gamestates.implementations;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.interfaces.IslandEditorState;
import de.ethasia.yaumr.gamestates.interfaces.ManageIslandsState;
import de.ethasia.yaumr.gamestates.interfaces.WorldEditorBaseMenuState;

/**
 *
 * @author R
 */
public class ManageIslandsStateImpl extends YaumrGameState implements ManageIslandsState {

    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/IslandsEditorView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "manageIslandsMenu";
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void createNewIsland() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorState.class).startDisplaying();        
    }

    @Override
    public void editSelectedIsland() {
    }

    @Override
    public void deleteSelectedIsland() {
    }

    @Override
    public void goToPreviousState() {
        YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(WorldEditorBaseMenuState.class).startDisplaying();  
    }

    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().setGameState(this);
    }
    
}
