package de.ethasia.yaumr.interactors.tests;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.Island;
import de.ethasia.yaumr.core.IslandManipulationFacadeImpl;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.LoadCurrentIslandOnScreenUseCaseImpl;
import de.ethasia.yaumr.interactors.interfaces.ChunkPresenter;
import de.ethasia.yaumr.interactors.interfaces.LoadCurrentIslandOnScreenUseCase;
import de.ethasia.yaumr.interactors.tests.mocks.ChunkPresenterMock;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoadCurrentIslandUseCaseTest {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static Island testIsland;    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setup Methods">
    
    @BeforeClass
    public static void setupClass() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        dependencyResolver.removeRegisteredImplementation(ChunkPresenter.class);
        dependencyResolver.registerImplementation(ChunkPresenter.class, ChunkPresenterMock.class);
    }    
    
    @Before
    public void setUpTest() {
        testIsland = new Island(18);
        IslandManipulationFacade islandManipulationFacade = new IslandManipulationFacadeImpl();
        islandManipulationFacade.setNewlyCreatedIsland(testIsland);
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();        
        dependencyResolver.removeSingletonInstance(IslandManipulationFacade.class);
        dependencyResolver.registerSingletonInstance(IslandManipulationFacade.class, islandManipulationFacade);        
        
        ChunkPresenterMock.resetMethodCallCounts();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Test Methods">
    
    @Test
    public void testLoadCurrentIsland_chunkPresenterMockIsCalledOnce() {
        LoadCurrentIslandOnScreenUseCase testCandidate = new LoadCurrentIslandOnScreenUseCaseImpl();
        
        testCandidate.loadCurrentIslandToView();
        
        assertEquals(1, ChunkPresenterMock.getCallCounterForMethodName("presentAllChunksInIsland"));
    }
    
    @Test
    public void testLoadCurrentIsland_chunkPresenterIsCalledWithCurrentIsland() {
        LoadCurrentIslandOnScreenUseCase testCandidate = new LoadCurrentIslandOnScreenUseCaseImpl();
        
        testCandidate.loadCurrentIslandToView();  
        
        assertEquals(testIsland, ChunkPresenterMock.getParameterPassedToPresentAllChunksInIsland());
    }
    
    //</editor-fold>
}
