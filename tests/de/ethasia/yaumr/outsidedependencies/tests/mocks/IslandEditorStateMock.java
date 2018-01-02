package de.ethasia.yaumr.outsidedependencies.tests.mocks;

import de.ethasia.yaumr.interactors.InteractionVector;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.tests.helpers.ClassMock;

/**
 *
 * @author R
 */
public class IslandEditorStateMock extends ClassMock implements IslandEditorState  {

    @Override
    public void showMainMenu() {
        incrementMockCounterForCalledMethod("showMainMenu");
    }

    @Override
    public void showHelpPanel() {
        incrementMockCounterForCalledMethod("showHelpPanel");
    }

    @Override
    public void showTerraformingToolbox() {
        incrementMockCounterForCalledMethod("showTerraformingToolbox");        
    }

    @Override
    public void hideAllVisibleGUIItems() {
        incrementMockCounterForCalledMethod("hideAllVisibleGUIItems");         
    }

    @Override
    public void activateMovementControls() {
        incrementMockCounterForCalledMethod("activateMovementControls");         
    }

    @Override
    public void deactivateMovementControls() {
        incrementMockCounterForCalledMethod("deactivateMovementControls");        
    }

    @Override
    public void displayBlockPointingIndicator(InteractionVector position) {
        incrementMockCounterForCalledMethod("displayBlockPointingIndicator");         
    }

    @Override
    public void removeBlockPointingIndicator() {
        incrementMockCounterForCalledMethod("removeBlockPointingIndicator");                 
    }

    @Override
    public void startDisplaying() {
        incrementMockCounterForCalledMethod("startDisplaying");          
    }
    
}
