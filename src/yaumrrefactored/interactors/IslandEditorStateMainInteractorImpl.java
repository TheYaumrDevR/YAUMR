package yaumrrefactored.interactors;

import de.ethasia.yaumr.base.YaumrGame;
import yaumrrefactored.interactors.interfaces.IslandEditorStateMainInteractor;
import yaumrrefactored.interactors.interfaces.IslandEditorStateMainWindowsPresenter;

/**
 * Manages the input of the IslandEditorState.
 * 
 * @author R
 */
public class IslandEditorStateMainInteractorImpl implements IslandEditorStateMainInteractor {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IslandEditorStateMainWindowsPresenter islandEditorWindowsPresenter;
    
    private boolean mainMenuIsVisible;
    private boolean helpMenuIsVisible;
    private boolean toolsDisplayIsVisible;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public IslandEditorStateMainInteractorImpl() {
        islandEditorWindowsPresenter = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorStateMainWindowsPresenter.class);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void toggleMainMenu() {
        if (mainMenuIsVisible) {
            islandEditorWindowsPresenter.hideMainMenu();
            mainMenuIsVisible = false;
        } else {
            hideEverything();
            islandEditorWindowsPresenter.showMainMenu();
            mainMenuIsVisible = true;
        }
    }
    
    @Override
    public void toggleHelpMenu() {
        if (helpMenuIsVisible) {
            islandEditorWindowsPresenter.hideHelpPanel();
            helpMenuIsVisible = false;
        } else {
            hideEverything();
            islandEditorWindowsPresenter.showHelpPanel();
            helpMenuIsVisible = true;            
        }        
    }
    
    @Override
    public void toggleToolsDisplay() {
        if (toolsDisplayIsVisible) {
            islandEditorWindowsPresenter.hideToolBox();
            toolsDisplayIsVisible = false;            
        } else {
            hideEverything();
            islandEditorWindowsPresenter.showToolbox();
            toolsDisplayIsVisible = true;               
        }         
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void hideEverything() {
        mainMenuIsVisible = false;
        helpMenuIsVisible = false;
        toolsDisplayIsVisible = false;
        
        islandEditorWindowsPresenter.hideAllChildren();
    }
    
    //</editor-fold>
}
