package de.ethasia.yaumr.outsidedependencies.views;

import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.interfaces.IslandCreationInteractor;
import de.ethasia.yaumr.ioadapters.interfaces.ConfirmationActionTypes;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;
import de.ethasia.yaumr.ioadapters.interfaces.WorldEditorBaseMenuState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.SizeValueType;

/**
 *
 * @author R
 */
public class ManageIslandsStateImpl extends YaumrGameState implements ManageIslandsState {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String CREATION_CONFIRMATION_PANEL_NAME = "#confirmationWindow";
    private static final String ISLAND_SIZE_TEXTFIELD_NAME = "#islandSizeInput";
    private static final String ERROR_MESSAGE_WINDOW_CONTAINER_NAME = "#errorMessageWindowContainer";
    private static final String ERROR_MESSAGE_WINDOW_NAME = "#errorMessageWindow";
    private static final String ERROR_MESSAGE_BODY_PANEL_NAME = "#errorMessageWindowBody";
    private static final String ERROR_MESSAGE_HEADER_PANEL_NAME = "#errorMessageWindowHeader";
    private static final String ERROR_MESSAGE_TEXT_NAME = "#errorMessageText";
    private static final String WARNING_MESSAGE_WINDOW_NAME = "#warningMessageWindow";
    private static final String WARNING_MESSAGE_TEXT_NAME = "#warningMessageText";
    private static final String WARNING_MESSAGE_WINDOW_CONTAINER_NAME = "#warningMessageWindowContainer";
    private static final String WARNING_MESSAGE_BODY_PANEL_NAME = "#warningMessageWindowBody";
    private static final String WARNING_MESSAGE_HEADER_PANEL_NAME = "#warningMessageWindowHeader";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Element creationConfirmationPanel;
    private TextField islandSizeTextField;
    
    private Element errorMessageWindowContainer;
    private Element errorMessagePanel;
    private Element errorMessageBodyPanel;
    private Element errorMessageHeaderPanel;
    private Label errorMessageText;
    
    private Element warningMessageWindowContainer;
    private Element warningMessagePanel;
    private Element warningMessageBodyPanel;
    private Element warningMessageHeaderPanel;    
    private Label warningMessageText;
    private ConfirmationActionTypes currentWarningWindowConfirmationAction;
    
    private boolean buttonsAreBlocked;
    private boolean islandSizeInputIsBlocked;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected String getNiftyViewFileName() {
        return "Interface/ViewDefinitions/IslandsEditorView.xml";
    }

    @Override
    protected String getNiftyScreenName() {
        return "manageIslandsMenu";
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        super.bind(nifty, screen);
        
        creationConfirmationPanel = screen.findElementById(CREATION_CONFIRMATION_PANEL_NAME);
        islandSizeTextField = screen.findNiftyControl(ISLAND_SIZE_TEXTFIELD_NAME, TextField.class);
        
        errorMessageWindowContainer = screen.findElementById(ERROR_MESSAGE_WINDOW_CONTAINER_NAME);
        errorMessagePanel = screen.findElementById(ERROR_MESSAGE_WINDOW_NAME);
        errorMessageBodyPanel = screen.findElementById(ERROR_MESSAGE_BODY_PANEL_NAME);
        errorMessageHeaderPanel = screen.findElementById(ERROR_MESSAGE_HEADER_PANEL_NAME);
        errorMessageText = screen.findNiftyControl(ERROR_MESSAGE_TEXT_NAME, Label.class);
        
        warningMessageWindowContainer = screen.findElementById(WARNING_MESSAGE_WINDOW_CONTAINER_NAME);
        warningMessagePanel = screen.findElementById(WARNING_MESSAGE_WINDOW_NAME);
        warningMessageBodyPanel = screen.findElementById(WARNING_MESSAGE_BODY_PANEL_NAME);
        warningMessageHeaderPanel = screen.findElementById(WARNING_MESSAGE_HEADER_PANEL_NAME);
        warningMessageText = screen.findNiftyControl(WARNING_MESSAGE_TEXT_NAME, Label.class);
    }

    @Override
    public void onStartScreen() {
        closeAllPanels();
    }

    @Override
    public void onEndScreen() {
        YaumrGame.getInstance().getClassInstanceContainer().removeSingletonInstance(ManageIslandsState.class);
    }

    @Override
    public void createNewIsland() {
        if (null != creationConfirmationPanel && !buttonsAreBlocked) {
            buttonsAreBlocked = true;
            creationConfirmationPanel.show();
        }       
    }

    @Override
    public void editSelectedIsland() {
    }

    @Override
    public void deleteSelectedIsland() {
    }
    
    @Override
    public void showConfirmationWarningMessage(String message, ConfirmationActionTypes actionType) {
        if (warningMessageWindowElementsArePresent()) {
            islandSizeInputIsBlocked = true;
            currentWarningWindowConfirmationAction = actionType;
                      
            TextRenderer textWidthMeasurer = new TextRenderer(nifty);
            textWidthMeasurer.setFont(nifty.createFont("Interface/Fonts/MSUIGothic.fnt"));
            textWidthMeasurer.setText(message);
            
            int windowXOffset = (warningMessageWindowContainer.getWidth() - textWidthMeasurer.getTextWidth()) / 2;
            warningMessagePanel.setConstraintX(new SizeValue(windowXOffset, SizeValueType.Pixel));
            
            int windowYOffset = (warningMessageWindowContainer.getHeight() - warningMessagePanel.getHeight()) / 2;
            warningMessagePanel.setConstraintY(new SizeValue(windowYOffset, SizeValueType.Pixel));            
            
            warningMessageWindowContainer.layoutElements();
            
            warningMessageText.setText(message);
            
            warningMessagePanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            warningMessageBodyPanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            
            int headerPanelOffset = (warningMessageBodyPanel.getWidth() - warningMessageHeaderPanel.getWidth()) / 2;
            warningMessageHeaderPanel.setConstraintX(new SizeValue(headerPanelOffset, SizeValueType.Pixel));              
            
            warningMessageBodyPanel.layoutElements();
            warningMessagePanel.layoutElements();
            
            warningMessagePanel.show();
        }        
    }
    
    @Override
    public void showErrorMessage(String message) {
        if (errorMessageWindowElementsArePresent()) {
            islandSizeInputIsBlocked = true;
                      
            TextRenderer textWidthMeasurer = new TextRenderer(nifty);
            textWidthMeasurer.setFont(nifty.createFont("Interface/Fonts/MSUIGothic.fnt"));
            textWidthMeasurer.setText(message);
            
            int windowXOffset = (errorMessageWindowContainer.getWidth() - textWidthMeasurer.getTextWidth()) / 2;
            errorMessagePanel.setConstraintX(new SizeValue(windowXOffset, SizeValueType.Pixel));
            
            int windowYOffset = (errorMessageWindowContainer.getHeight() - errorMessagePanel.getHeight()) / 2;
            errorMessagePanel.setConstraintY(new SizeValue(windowYOffset, SizeValueType.Pixel));            
            
            errorMessageWindowContainer.layoutElements();
            
            errorMessageText.setText(message);
            
            errorMessagePanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            errorMessageBodyPanel.setWidth(textWidthMeasurer.getTextWidth() + 20);
            
            int headerPanelOffset = (errorMessageBodyPanel.getWidth() - errorMessageHeaderPanel.getWidth()) / 2;
            errorMessageHeaderPanel.setConstraintX(new SizeValue(headerPanelOffset, SizeValueType.Pixel));              
            
            errorMessageBodyPanel.layoutElements();
            errorMessagePanel.layoutElements();
            
            errorMessagePanel.show();
        }
    }

    @Override
    public void goToPreviousState() {
        if (!buttonsAreBlocked) {
            YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(WorldEditorBaseMenuState.class).startDisplaying();              
        }
    }

    @Override
    public void startDisplaying() {
        YaumrGame.getInstance().getClassInstanceContainer().registerSingletonInstance(ManageIslandsState.class, this);
        YaumrGame.getInstance().setGameState(this);
    }       
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="UI Callbacks">
    
    public void proceedWithIslandCreation() {
        if (!islandSizeInputIsBlocked) {
            buttonsAreBlocked = false;
        
            if (null != islandSizeTextField) {
                IslandCreationInteractor islandCreationInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandCreationInteractor.class);
            
                if (islandCreationInteractor.createNewIslandWithRegisteredSingletonFacadeInstance(islandSizeTextField.getDisplayedText())) {
                    YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorState.class).startDisplaying();                
                }                
            }            
        }
    }
    
    public void closeCreateIslandConfirmation() {
        if (!islandSizeInputIsBlocked) {
            buttonsAreBlocked = false;
        
            if (null != creationConfirmationPanel) {
                creationConfirmationPanel.hide();
            }            
        }
    }
    
    public void closeAllPanels() {
        buttonsAreBlocked = false;
        islandSizeInputIsBlocked = false;
        
        if (null != creationConfirmationPanel) {
            creationConfirmationPanel.hide();
        }

        if (null != errorMessagePanel) {
            errorMessagePanel.hide();
        }
        
        if (null != warningMessagePanel) {
            warningMessagePanel.hide();
        }
    }
    
    public void onErrorMessageConfirmed() {
        islandSizeInputIsBlocked = false;
        
        if (null != errorMessagePanel) {
            errorMessagePanel.hide();
        }        
    }
    
    public void onWarningMessageDeclined() {
        islandSizeInputIsBlocked = false;
        
        if (null != warningMessagePanel) {
            warningMessagePanel.hide();
        }
    }
    
    public void onWarningMessageConfirmed() {
        proceedAfterWarningMessageConfirmationWithSpecifiedAction();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private boolean errorMessageWindowElementsArePresent() {
        return null != errorMessageText 
                && null != errorMessagePanel
                && null != errorMessageBodyPanel
                && null != errorMessageHeaderPanel
                && null != errorMessageWindowContainer;
    }
    
    private boolean warningMessageWindowElementsArePresent() {
        return null != warningMessageText
                && null != warningMessagePanel
                && null != warningMessageBodyPanel
                && null != warningMessageHeaderPanel
                && null != warningMessageWindowContainer;
    }
    
    private void proceedAfterWarningMessageConfirmationWithSpecifiedAction() {
        islandSizeInputIsBlocked = false;
        if (null != warningMessagePanel) {
            warningMessagePanel.hide();
        }
        
        switch (currentWarningWindowConfirmationAction) {
            case CREATE_ISLAND_CONFIRMED:
                tryToCreateNewIslandAndProceed();
                break;
        }
    }
    
    private void tryToCreateNewIslandAndProceed() {
        if (null != islandSizeTextField) {
            IslandCreationInteractor islandCreationInteractor = YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandCreationInteractor.class);
            
            if (islandCreationInteractor.createNewIslandWithFacadeInstanceWithoutUserConfirmationChecks(islandSizeTextField.getDisplayedText())) {
                closeAllPanels(); 
                YaumrGame.getInstance().getClassInstanceContainer().getImplementationInstance(IslandEditorState.class).startDisplaying();                
            }                
        }        
    }
    
    //</editor-fold>
}
