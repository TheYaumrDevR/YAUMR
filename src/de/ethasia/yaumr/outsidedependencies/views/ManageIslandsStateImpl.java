package de.ethasia.yaumr.outsidedependencies.views;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.interactors.IslandMetaData;
import de.ethasia.yaumr.interactors.interfaces.IslandCreationInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandDeletionInteractor;
import de.ethasia.yaumr.interactors.interfaces.IslandListPopulatingInteractor;
import de.ethasia.yaumr.interactors.interfaces.MessageConfirmationAction;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithIslandList;
import de.ethasia.yaumr.ioadapters.interfaces.AppStateWithWarningMessages;
import de.ethasia.yaumr.ioadapters.interfaces.IslandEditorState;
import de.ethasia.yaumr.ioadapters.interfaces.ManageIslandsState;
import de.ethasia.yaumr.ioadapters.interfaces.WorldEditorBaseMenuState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.SizeValueType;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author R
 */
public class ManageIslandsStateImpl extends YaumrGameState implements ManageIslandsState, 
        AppStateWithIslandList, 
        AppStateWithWarningMessages {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String CREATION_CONFIRMATION_PANEL_NAME = "#confirmationWindow";
    private static final String ISLAND_SIZE_TEXTFIELD_NAME = "#islandSizeInput";
    private static final String WARNING_MESSAGE_WINDOW_NAME = "#warningMessageWindow";
    private static final String WARNING_MESSAGE_TEXT_NAME = "#warningMessageText";
    private static final String WARNING_MESSAGE_WINDOW_CONTAINER_NAME = "#warningMessageWindowContainer";
    private static final String WARNING_MESSAGE_BODY_PANEL_NAME = "#warningMessageWindowBody";
    private static final String WARNING_MESSAGE_HEADER_PANEL_NAME = "#warningMessageWindowHeader";
    
    private static final String ISLAND_SELECTION_LIST_NAME = "#availableIslandsList";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Element creationConfirmationPanel;
    private TextField islandSizeTextField;
    
    private Element warningMessageWindowContainer;
    private Element warningMessagePanel;
    private Element warningMessageBodyPanel;
    private Element warningMessageHeaderPanel;    
    private Label warningMessageText;
    private MessageConfirmationAction currentWarningWindowConfirmationAction;
    
    private ListBox<IslandMetaData> availableIslandList;
    
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
        
        warningMessageWindowContainer = screen.findElementById(WARNING_MESSAGE_WINDOW_CONTAINER_NAME);
        warningMessagePanel = screen.findElementById(WARNING_MESSAGE_WINDOW_NAME);
        warningMessageBodyPanel = screen.findElementById(WARNING_MESSAGE_BODY_PANEL_NAME);
        warningMessageHeaderPanel = screen.findElementById(WARNING_MESSAGE_HEADER_PANEL_NAME);
        warningMessageText = screen.findNiftyControl(WARNING_MESSAGE_TEXT_NAME, Label.class);
        
        availableIslandList = screen.findNiftyControl(ISLAND_SELECTION_LIST_NAME, ListBox.class);
    }

    @Override
    public void onStartScreen() {
        closeAllPanels();
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        
        if (null != availableIslandList) {
            availableIslandList.clear();
            IslandListPopulatingInteractor islandListPopulatingInteractor = dependencyResolver.getImplementationInstance(IslandListPopulatingInteractor.class);
            
            if (null != islandListPopulatingInteractor) {
                islandListPopulatingInteractor.loadAllAvailableIslandsIntoIslandList();
            }
        }
        
        dependencyResolver.removeSingletonInstance(AppStateWithWarningMessages.class);
        dependencyResolver.registerSingletonInstance(AppStateWithWarningMessages.class, this);
    }

    @Override
    public void onEndScreen() {
        closeAllPanels();
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
        if (null != availableIslandList && !buttonsAreBlocked) {
            List<IslandMetaData> islandSelection = availableIslandList.getSelection();
            if (islandSelection.size() > 0) {
                IslandMetaData selectedMetaData = islandSelection.get(0);
                ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
                IslandDeletionInteractor islandDeletionInteractor = dependencyResolver.getImplementationInstance(IslandDeletionInteractor.class);
                islandDeletionInteractor.deleteIsland(selectedMetaData);
            }
        }
    }
    
    @Override
    public void showConfirmationWarningMessage(String messageText, MessageConfirmationAction confirmationAction) {
        if (warningMessageWindowElementsArePresent()) {
            islandSizeInputIsBlocked = true;
            currentWarningWindowConfirmationAction = confirmationAction;
                      
            TextRenderer textWidthMeasurer = new TextRenderer(nifty);
            textWidthMeasurer.setFont(nifty.createFont("Interface/Fonts/MSUIGothic.fnt"));
            textWidthMeasurer.setText(messageText);
            
            int windowXOffset = (warningMessageWindowContainer.getWidth() - textWidthMeasurer.getTextWidth()) / 2;
            warningMessagePanel.setConstraintX(new SizeValue(windowXOffset, SizeValueType.Pixel));
            
            int windowYOffset = (warningMessageWindowContainer.getHeight() - warningMessagePanel.getHeight()) / 2;
            warningMessagePanel.setConstraintY(new SizeValue(windowYOffset, SizeValueType.Pixel));            
            
            warningMessageWindowContainer.layoutElements();
            
            warningMessageText.setText(messageText);
            
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
    public void showNonConfirmationWarningMessage(String messageText) {
    }    
    
    @Override
    public void showErrorMessage(String message) {
        if (errorMessageWindowElementsArePresent()) {
            islandSizeInputIsBlocked = true;
        }
        
        super.showErrorMessage(message);
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
        
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        dependencyResolver.removeSingletonInstance(AppStateWithIslandList.class);
        dependencyResolver.registerSingletonInstance(AppStateWithIslandList.class, this);
    }
    
    @Override
    public void showIslandList(Stream<IslandMetaData> islandList) {
        if (null != availableIslandList) {
            availableIslandList.clear();
            
            islandList.forEach(new Consumer<IslandMetaData>() {
                
                @Override
                public void accept(IslandMetaData islandMetaData) {
                    availableIslandList.addItem(islandMetaData);
                }
            });
        }
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
        
        if (null != currentWarningWindowConfirmationAction) {
            currentWarningWindowConfirmationAction.onMessageConfirmed();
        }
    }
    
    //</editor-fold>
}
