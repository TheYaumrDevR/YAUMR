package de.ethasia.yaumr.outsidedependencies.niftyguiextensions;

import de.ethasia.yaumr.ioadapters.datatransfer.ItemDisplayData;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBar;
import de.ethasia.yaumr.outsidedependencies.niftyguiextensions.interfaces.QuickSelectionBarButton;
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
    
    public static final String SELECT_FIRST_ITEM_KEYACTION = "selectItemOne";
    public static final String SELECT_SECOND_ITEM_KEYACTION = "selectItemTwo";
    public static final String SELECT_THIRD_ITEM_KEYACTION = "selectItemThree";
    public static final String SELECT_FOURTH_ITEM_KEYACTION = "selectItemFourth";
    public static final String SELECT_FIFTH_ITEM_KEYACTION = "selectItemFive";
    public static final String SELECT_SIXTH_ITEM_KEYACTION = "selectItemSix";
    public static final String SELECT_SEVENTH_ITEM_KEYACTION = "selectItemSeven";
    public static final String SELECT_EIGHTH_ITEM_KEYACTION = "selectItemEight";
    public static final String SELECT_NINTH_ITEM_KEYACTION = "selectItemNine";
    public static final String SELECT_TENTH_ITEM_KEYACTION = "selectItemTen";
    
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
    public void setItemDisplayData(ItemDisplayData[] displayData) {
        int numberOfShownItems = displayData.length > 10 ? 10 : displayData.length;
        
        for (int i = 0; i < numberOfShownItems; i++) {
            QuickSelectionBarButton affectedButton = buttonOnPosition.get(i);
            ItemDisplayData itemDisplayData = displayData[i];
            
            if (null != affectedButton) {
                affectedButton.setItemToDisplay(itemDisplayData);
            }
        }
    }
    
    @Override
    public int getItemIndexForKeyActionName(String keyActionName) {
        return keyActionOnPosition.get(keyActionName);
    }

    @Override
    public void highlightSelectionAtIndex(int itemIndex) {
        QuickSelectionBarButton buttonToSelect = buttonOnPosition.get(itemIndex);
            
        if (null != buttonToSelect) {
            if (null != currentlySelectedButton) {
                currentlySelectedButton.setUnselected();
            }   
                
            buttonToSelect.setSelected();
            currentlySelectedButton = buttonToSelect;
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
