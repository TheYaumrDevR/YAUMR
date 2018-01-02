package de.ethasia.yaumr.outsidedependencies.views;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandCreationInteractor;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;
import de.ethasia.yaumr.ioadapters.interfaces.WorldEditorBaseMenuState;

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
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(ManageIslandsState.class);
    }

    @Override
    public void createNewIsland() {
        IslandCreationInteractor islandCreationInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandCreationInteractor.class);
        islandCreationInteractor.createNewIslandWithRegisteredSingletonFacadeInstance(256);
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
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(ManageIslandsState.class, this);
        YaumrGame.getInstance().setGameState(this);
    }
    
}
