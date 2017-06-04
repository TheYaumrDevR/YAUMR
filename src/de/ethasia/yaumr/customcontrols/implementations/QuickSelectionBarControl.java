package de.ethasia.yaumr.customcontrols.implementations;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.customcontrols.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.customcontrols.interfaces.QuickSelectionBarButton;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author R
 */
public class QuickSelectionBarControl extends AbstractController implements QuickSelectionBar {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final String SELECT_FIRST_ITEM_KEYACTION = "selectFirstItem";
    public static final String SELECT_SECOND_ITEM_KEYACTION = "selectSecondItem";
    public static final String SELECT_THIRD_ITEM_KEYACTION = "selectThirdItem";
    public static final String SELECT_FOURTH_ITEM_KEYACTION = "selectFourthItem";
    public static final String SELECT_FIFTH_ITEM_KEYACTION = "selectFifthItem";
    public static final String SELECT_SIXTH_ITEM_KEYACTION = "selectSixthItem";
    public static final String SELECT_SEVENTH_ITEM_KEYACTION = "selectSeventhItem";
    public static final String SELECT_EIGHTH_ITEM_KEYACTION = "selectEighthItem";
    public static final String SELECT_NINTH_ITEM_KEYACTION = "selectNinthItem";
    public static final String SELECT_TENTH_ITEM_KEYACTION = "selectTenthItem";
    
    private static final String FIRST_QUICKBAR_BUTTON_NAME = "#firstSelectionButton";
    private static final String SECOND_QUICKBAR_BUTTON_NAME = "#secondSelectionButton";
    private static final String THIRD_QUICKBAR_BUTTON_NAME = "#thirdSelectionButton";
    private static final String FOURTH_QUICKBAR_BUTTON_NAME = "#fourthSelectionButton";
    private static final String FIFTH_QUICKBAR_BUTTON_NAME = "#fifthSelectionButton";
    private static final String SIXTH_QUICKBAR_BUTTON_NAME = "#sixthSelectionButton";
    private static final String SEVENTH_QUICKBAR_BUTTON_NAME = "#seventhSelectionButton";
    private static final String EIGHTH_QUICKBAR_BUTTON_NAME = "#eighthSelectionButton";
    private static final String NINTH_QUICKBAR_BUTTON_NAME = "#ninthSelectionButton";
    private static final String TENTH_QUICKBAR_BUTTON_NAME = "#tenthSelectionButton";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private QuickSelectionBarButton firstQuickSelectionButton;
    private QuickSelectionBarButton secondQuickSelectionButton;
    private QuickSelectionBarButton thirdQuickSelectionButton;
    private QuickSelectionBarButton fourthQuickSelectionButton;
    private QuickSelectionBarButton fifthQuickSelectionButton;
    private QuickSelectionBarButton sixthQuickSelectionButton;
    private QuickSelectionBarButton seventhQuickSelectionButton;
    private QuickSelectionBarButton eighthQuickSelectionButton;
    private QuickSelectionBarButton ninthQuickSelectionButton;
    private QuickSelectionBarButton tenthQuickSelectionButton;
    
    private Map<Integer, QuickSelectionBarButton> buttonOnPosition;
    private Map<String, Integer> keyActionOnPosition;
    
    private QuickSelectionBarButton currentlySelectedButton;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters prmtrs) {
        super.bind(element); 
        
        buttonOnPosition = new HashMap<>();
        keyActionOnPosition = new HashMap<>();
        
        initializeKeyActions();
        
        firstQuickSelectionButton = element.findNiftyControl(FIRST_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class);
        secondQuickSelectionButton = element.findNiftyControl(SECOND_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class);
        thirdQuickSelectionButton = element.findNiftyControl(THIRD_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class);
        fourthQuickSelectionButton = element.findNiftyControl(FOURTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class);
        fifthQuickSelectionButton = element.findNiftyControl(FIFTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class);  
        sixthQuickSelectionButton = element.findNiftyControl(SIXTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class); 
        seventhQuickSelectionButton = element.findNiftyControl(SEVENTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class); 
        eighthQuickSelectionButton = element.findNiftyControl(EIGHTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class); 
        ninthQuickSelectionButton = element.findNiftyControl(NINTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class); 
        tenthQuickSelectionButton = element.findNiftyControl(TENTH_QUICKBAR_BUTTON_NAME, QuickSelectionBarButton.class); 

        buttonOnPosition.put(0, firstQuickSelectionButton);
        buttonOnPosition.put(1, secondQuickSelectionButton);
        buttonOnPosition.put(2, thirdQuickSelectionButton);
        buttonOnPosition.put(3, fourthQuickSelectionButton);
        buttonOnPosition.put(4, fifthQuickSelectionButton);
        buttonOnPosition.put(5, sixthQuickSelectionButton);
        buttonOnPosition.put(6, seventhQuickSelectionButton);
        buttonOnPosition.put(7, eighthQuickSelectionButton);
        buttonOnPosition.put(8, ninthQuickSelectionButton);
        buttonOnPosition.put(9, tenthQuickSelectionButton);
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }

    @Override
    public QuickSelectableEntity reactToKeyInput(String keyActionName) {
        Integer barPosition = keyActionOnPosition.get(keyActionName);
        
        if (null != barPosition) {
            QuickSelectionBarButton buttonToSelect = buttonOnPosition.get(barPosition);
            
            if (null != buttonToSelect) {
                if (null != currentlySelectedButton) {
                    currentlySelectedButton.setUnselected();
                }   
                
                buttonToSelect.setSelected();
                currentlySelectedButton = buttonToSelect;
                
                return buttonToSelect.getContainedItem();
            }
        }
        
        return null;
    }

    @Override
    public void addItemToPosition(QuickSelectableEntity item, int position) {
        QuickSelectionBarButton button = buttonOnPosition.get(position);
            
        if (null != button) {
            button.setItemToSelect(item);
        }
    }
    
    @Override
    public boolean isButtonSelected(int position) {
        QuickSelectionBarButton button = buttonOnPosition.get(position);
            
        if (null != button) {
            return currentlySelectedButton == button;
        }
        
        return false;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private void initializeKeyActions() {
        keyActionOnPosition.put(SELECT_FIRST_ITEM_KEYACTION, 0);
        keyActionOnPosition.put(SELECT_SECOND_ITEM_KEYACTION, 1);
        keyActionOnPosition.put(SELECT_THIRD_ITEM_KEYACTION, 2);
        keyActionOnPosition.put(SELECT_FOURTH_ITEM_KEYACTION, 3);
        keyActionOnPosition.put(SELECT_FIFTH_ITEM_KEYACTION, 4);
        keyActionOnPosition.put(SELECT_SIXTH_ITEM_KEYACTION, 5);
        keyActionOnPosition.put(SELECT_SEVENTH_ITEM_KEYACTION, 6);
        keyActionOnPosition.put(SELECT_EIGHTH_ITEM_KEYACTION, 7);
        keyActionOnPosition.put(SELECT_NINTH_ITEM_KEYACTION, 8);
        keyActionOnPosition.put(SELECT_TENTH_ITEM_KEYACTION, 9);
    }
    
    //</editor-fold>
}
