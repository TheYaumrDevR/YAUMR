package de.ethasia.yaumr.gamestates.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.implementations.GameEntryStateImpl;
import de.ethasia.yaumr.gamestates.implementations.ManageIslandsStateImpl;
import de.ethasia.yaumr.gamestates.implementations.WorldEditorBaseMenuStateImpl;

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
    }
}
