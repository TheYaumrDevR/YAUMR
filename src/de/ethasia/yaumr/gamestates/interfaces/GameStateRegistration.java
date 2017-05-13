package de.ethasia.yaumr.gamestates.interfaces;

import de.ethasia.yaumr.base.AutowiringClass;
import de.ethasia.yaumr.base.AutowiringMethod;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.gamestates.implementations.GameEntryStateImpl;

/**
 * Wires up GameState implementations to their interfaces.
 * 
 * @author Drawig
 */
@AutowiringClass
public class GameStateRegistration {
    
    @AutowiringMethod
    public void registerGameStateImplementations() {
        YaumrGame.getInstance().getClassInstanceContainer().registerImplementation(GameEntryState.class, GameEntryStateImpl.class);
    }
}
