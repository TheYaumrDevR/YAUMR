package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.TimedUpdateInteractor;

/**
 *
 * @author R
 */
public class TimedUpdateInteractorImpl implements TimedUpdateInteractor {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IslandManipulationFacade islandManipulationFacade;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TimedUpdateInteractorImpl() {
        islandManipulationFacade = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(IslandManipulationFacade.class);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void tick(float tpf) {
        long timeSinceLastUpdateInMS = (long)(tpf * 1000);
        
        if (null != islandManipulationFacade) {
            islandManipulationFacade.tick(timeSinceLastUpdateInMS);
        }
    }
    
    //</editor-fold>
}
