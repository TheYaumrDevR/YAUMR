package de.ethasia.yaumr.ioadapters.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.outsidedependencies.views.GameEntryStateImpl;
import de.ethasia.yaumr.outsidedependencies.views.ManageIslandsStateImpl;
import de.ethasia.yaumr.outsidedependencies.views.WorldEditorBaseMenuStateImpl;
import de.ethasia.yaumr.outsidedependencies.views.RefactoredIslandEditorState;

/**
 * Wires up GameState implementations to their interfaces.
 * 
 * @author R
 */
@AutowiringClass
public class GameStateRegistration {
    
    @AutowiringMethod
    public void registerGameStateImplementations() {
        ClassInstanceContainer classInstanceContainer = YaumrGame.getInstance().getClassInstanceContainer();
        
        classInstanceContainer.registerImplementation(GameEntryState.class, GameEntryStateImpl.class);
        classInstanceContainer.registerImplementation(WorldEditorBaseMenuState.class, WorldEditorBaseMenuStateImpl.class);
        classInstanceContainer.registerImplementation(ManageIslandsState.class, ManageIslandsStateImpl.class);
        classInstanceContainer.registerImplementation(IslandEditorState.class, RefactoredIslandEditorState.class);
    }
}
