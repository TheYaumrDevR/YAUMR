package de.ethasia.yaumr.customcontrols.implementations;

import de.ethasia.yaumr.base.YaumrGame;
import java.util.Map;

import de.ethasia.yaumr.blockengine.entities.base.QuickSelectableEntity;
import de.ethasia.yaumr.controllers.interfaces.BlockPlacementController;
import de.ethasia.yaumr.customcontrols.interfaces.DraggableItemBox;
import de.ethasia.yaumr.customcontrols.interfaces.DroppableItemBox;
import de.ethasia.yaumr.customcontrols.interfaces.InventoryGrid;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.Draggable;
import de.lessvoid.nifty.controls.Droppable;
import de.lessvoid.nifty.controls.DroppableDroppedEvent;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import java.util.HashMap;

/**
 *
 * @author R
 */
public class InventoryGridControl extends AbstractController implements InventoryGrid {
    
    //<editor-fold defaultstate="collapsed" desc="Part IDs">

    private static final String DISCARD_DROPPABLE_NAME = "#discardDroppable";
    
    private static final String ITEM_BOX1_NAME = "itemBox1";
    private static final String ITEM_BOX2_NAME = "itemBox2";
    private static final String ITEM_BOX3_NAME = "itemBox3";
    private static final String ITEM_BOX4_NAME = "itemBox4";
    private static final String ITEM_BOX5_NAME = "itemBox5";
    private static final String ITEM_BOX6_NAME = "itemBox6";
    private static final String ITEM_BOX7_NAME = "itemBox7";
    private static final String ITEM_BOX8_NAME = "itemBox8";
    private static final String ITEM_BOX9_NAME = "itemBox9";
    private static final String ITEM_BOX10_NAME = "itemBox10";
    
    private static final String ITEM_BOX11_NAME = "itemBox11";
    private static final String ITEM_BOX12_NAME = "itemBox12";
    private static final String ITEM_BOX13_NAME = "itemBox13";
    private static final String ITEM_BOX14_NAME = "itemBox14";
    private static final String ITEM_BOX15_NAME = "itemBox15";
    private static final String ITEM_BOX16_NAME = "itemBox16";
    private static final String ITEM_BOX17_NAME = "itemBox17";
    private static final String ITEM_BOX18_NAME = "itemBox18";
    private static final String ITEM_BOX19_NAME = "itemBox19";
    private static final String ITEM_BOX20_NAME = "itemBox20";    
    
    private static final String ITEM_BOX21_NAME = "itemBox21";
    private static final String ITEM_BOX22_NAME = "itemBox22";
    private static final String ITEM_BOX23_NAME = "itemBox23";
    private static final String ITEM_BOX24_NAME = "itemBox24";
    private static final String ITEM_BOX25_NAME = "itemBox25";
    private static final String ITEM_BOX26_NAME = "itemBox26";
    private static final String ITEM_BOX27_NAME = "itemBox27";
    private static final String ITEM_BOX28_NAME = "itemBox28";
    private static final String ITEM_BOX29_NAME = "itemBox29";
    private static final String ITEM_BOX30_NAME = "itemBox30"; 
    
    private static final String ITEM_BOX31_NAME = "itemBox31";
    private static final String ITEM_BOX32_NAME = "itemBox32";
    private static final String ITEM_BOX33_NAME = "itemBox33";
    private static final String ITEM_BOX34_NAME = "itemBox34";
    private static final String ITEM_BOX35_NAME = "itemBox35";
    private static final String ITEM_BOX36_NAME = "itemBox36";
    private static final String ITEM_BOX37_NAME = "itemBox37";
    private static final String ITEM_BOX38_NAME = "itemBox38";
    private static final String ITEM_BOX39_NAME = "itemBox39";
    private static final String ITEM_BOX40_NAME = "itemBox40";    
    
    private static final String DROPPABLE_BOX_NAME_PREFIX = "droppableBox";
    private static final String DROPPABLE_BOX1_NAME = DROPPABLE_BOX_NAME_PREFIX + "1";
    private static final String DROPPABLE_BOX2_NAME = DROPPABLE_BOX_NAME_PREFIX + "2";
    private static final String DROPPABLE_BOX3_NAME = DROPPABLE_BOX_NAME_PREFIX + "3";
    private static final String DROPPABLE_BOX4_NAME = DROPPABLE_BOX_NAME_PREFIX + "4";
    private static final String DROPPABLE_BOX5_NAME = DROPPABLE_BOX_NAME_PREFIX + "5";
    private static final String DROPPABLE_BOX6_NAME = DROPPABLE_BOX_NAME_PREFIX + "6";
    private static final String DROPPABLE_BOX7_NAME = DROPPABLE_BOX_NAME_PREFIX + "7";
    private static final String DROPPABLE_BOX8_NAME = DROPPABLE_BOX_NAME_PREFIX + "8";
    private static final String DROPPABLE_BOX9_NAME = DROPPABLE_BOX_NAME_PREFIX + "9";
    private static final String DROPPABLE_BOX10_NAME = DROPPABLE_BOX_NAME_PREFIX + "10";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Map<Integer, DraggableItemBox> itemBoxes;
    private Map<String, DroppableItemBox> droppableBoxes;
    
    private Element rootPanel;
    private Droppable discardDroppable;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen, Element element, Parameters prmtrs) {
        super.bind(element);
        
        itemBoxes = new HashMap<>();
        droppableBoxes = new HashMap<>();
        
        rootPanel = element;
        
        discardDroppable = element.findNiftyControl(DISCARD_DROPPABLE_NAME, Droppable.class);
        if (null != discardDroppable) {
            TerraformingInventoryThrowAwayDropFilter discardDroppableFilter = new TerraformingInventoryThrowAwayDropFilter();
            discardDroppableFilter.setOwner(this);
            discardDroppable.addFilter(discardDroppableFilter);
        }
        
        itemBoxes.put(1, element.findNiftyControl(ITEM_BOX1_NAME, DraggableItemBox.class));
        itemBoxes.put(2, element.findNiftyControl(ITEM_BOX2_NAME, DraggableItemBox.class));
        itemBoxes.put(3, element.findNiftyControl(ITEM_BOX3_NAME, DraggableItemBox.class));
        itemBoxes.put(4, element.findNiftyControl(ITEM_BOX4_NAME, DraggableItemBox.class));
        itemBoxes.put(5, element.findNiftyControl(ITEM_BOX5_NAME, DraggableItemBox.class));
        itemBoxes.put(6, element.findNiftyControl(ITEM_BOX6_NAME, DraggableItemBox.class));
        itemBoxes.put(7, element.findNiftyControl(ITEM_BOX7_NAME, DraggableItemBox.class));
        itemBoxes.put(8, element.findNiftyControl(ITEM_BOX8_NAME, DraggableItemBox.class));
        itemBoxes.put(9, element.findNiftyControl(ITEM_BOX9_NAME, DraggableItemBox.class));
        itemBoxes.put(10, element.findNiftyControl(ITEM_BOX10_NAME, DraggableItemBox.class));
        
        itemBoxes.put(11, element.findNiftyControl(ITEM_BOX11_NAME, DraggableItemBox.class));
        itemBoxes.put(12, element.findNiftyControl(ITEM_BOX12_NAME, DraggableItemBox.class));
        itemBoxes.put(13, element.findNiftyControl(ITEM_BOX13_NAME, DraggableItemBox.class));
        itemBoxes.put(14, element.findNiftyControl(ITEM_BOX14_NAME, DraggableItemBox.class));
        itemBoxes.put(15, element.findNiftyControl(ITEM_BOX15_NAME, DraggableItemBox.class));
        itemBoxes.put(16, element.findNiftyControl(ITEM_BOX16_NAME, DraggableItemBox.class));
        itemBoxes.put(17, element.findNiftyControl(ITEM_BOX17_NAME, DraggableItemBox.class));
        itemBoxes.put(18, element.findNiftyControl(ITEM_BOX18_NAME, DraggableItemBox.class));
        itemBoxes.put(19, element.findNiftyControl(ITEM_BOX19_NAME, DraggableItemBox.class));
        itemBoxes.put(20, element.findNiftyControl(ITEM_BOX20_NAME, DraggableItemBox.class));
        
        itemBoxes.put(21, element.findNiftyControl(ITEM_BOX21_NAME, DraggableItemBox.class));
        itemBoxes.put(22, element.findNiftyControl(ITEM_BOX22_NAME, DraggableItemBox.class));
        itemBoxes.put(23, element.findNiftyControl(ITEM_BOX23_NAME, DraggableItemBox.class));
        itemBoxes.put(24, element.findNiftyControl(ITEM_BOX24_NAME, DraggableItemBox.class));
        itemBoxes.put(25, element.findNiftyControl(ITEM_BOX25_NAME, DraggableItemBox.class));
        itemBoxes.put(26, element.findNiftyControl(ITEM_BOX26_NAME, DraggableItemBox.class));
        itemBoxes.put(27, element.findNiftyControl(ITEM_BOX27_NAME, DraggableItemBox.class));
        itemBoxes.put(28, element.findNiftyControl(ITEM_BOX28_NAME, DraggableItemBox.class));
        itemBoxes.put(29, element.findNiftyControl(ITEM_BOX29_NAME, DraggableItemBox.class));
        itemBoxes.put(30, element.findNiftyControl(ITEM_BOX30_NAME, DraggableItemBox.class));  
        
        itemBoxes.put(31, element.findNiftyControl(ITEM_BOX31_NAME, DraggableItemBox.class));
        itemBoxes.put(32, element.findNiftyControl(ITEM_BOX32_NAME, DraggableItemBox.class));
        itemBoxes.put(33, element.findNiftyControl(ITEM_BOX33_NAME, DraggableItemBox.class));
        itemBoxes.put(34, element.findNiftyControl(ITEM_BOX34_NAME, DraggableItemBox.class));
        itemBoxes.put(35, element.findNiftyControl(ITEM_BOX35_NAME, DraggableItemBox.class));
        itemBoxes.put(36, element.findNiftyControl(ITEM_BOX36_NAME, DraggableItemBox.class));
        itemBoxes.put(37, element.findNiftyControl(ITEM_BOX37_NAME, DraggableItemBox.class));
        itemBoxes.put(38, element.findNiftyControl(ITEM_BOX38_NAME, DraggableItemBox.class));
        itemBoxes.put(39, element.findNiftyControl(ITEM_BOX39_NAME, DraggableItemBox.class));
        itemBoxes.put(40, element.findNiftyControl(ITEM_BOX40_NAME, DraggableItemBox.class));  
        
        InventoryGridQuickbarDropFilter droppableDropFilter = new InventoryGridQuickbarDropFilter();
        droppableDropFilter.setOwner(this);
        
        droppableBoxes.put(DROPPABLE_BOX1_NAME, element.findNiftyControl(DROPPABLE_BOX1_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX2_NAME, element.findNiftyControl(DROPPABLE_BOX2_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX3_NAME, element.findNiftyControl(DROPPABLE_BOX3_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX4_NAME, element.findNiftyControl(DROPPABLE_BOX4_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX5_NAME, element.findNiftyControl(DROPPABLE_BOX5_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX6_NAME, element.findNiftyControl(DROPPABLE_BOX6_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX7_NAME, element.findNiftyControl(DROPPABLE_BOX7_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX8_NAME, element.findNiftyControl(DROPPABLE_BOX8_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX9_NAME, element.findNiftyControl(DROPPABLE_BOX9_NAME, DroppableItemBox.class));
        droppableBoxes.put(DROPPABLE_BOX10_NAME, element.findNiftyControl(DROPPABLE_BOX10_NAME, DroppableItemBox.class));
        
        droppableBoxes.get(DROPPABLE_BOX1_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX2_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX3_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX4_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX5_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX6_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX7_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX8_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX9_NAME).setDropFilter(droppableDropFilter);
        droppableBoxes.get(DROPPABLE_BOX10_NAME).setDropFilter(droppableDropFilter);
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public boolean inputEvent(NiftyInputEvent nie) {
        return false;
    }    
    
    @Override
    public boolean inventoryGridIsVisible() {
        if (null != rootPanel) {
            return rootPanel.isVisible();
        }
        
        return false;
    }
    
    @Override
    public void hideInventoryGrid() {
        if (null != rootPanel) {
            rootPanel.hide();
        }
    }
    
    @Override
    public void showInventoryGrid() {
        if (null != rootPanel) {
            rootPanel.show();
        }
    }
    
    @Override
    public void setItemOnPosition(QuickSelectableEntity item, int position) {
        if (position > 0 && position < 41) {
            if (null != itemBoxes.get(position)) {
                itemBoxes.get(position).setDisplayedItem(item);
            }
        }
    }
    
    @Override
    public void onDragAndDropOperationCompleted(Droppable dropSource, Draggable drgbl, Droppable droppedAt) {
        BlockPlacementController blockPlacementController = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockPlacementController.class);
        
        for (Integer key : itemBoxes.keySet()) {
            if (itemBoxes.get(key).getId().equals(drgbl.getId())) {
                droppableBoxes.get(droppedAt.getId()).setDisplayedItem(itemBoxes.get(key).getDisplayedItem());
                
                int dropPosition = Integer.parseInt(droppedAt.getId().replaceFirst(DROPPABLE_BOX_NAME_PREFIX, "")) - 1;
                blockPlacementController.setSelectableItemAtPosition(itemBoxes.get(key).getDisplayedItem(), dropPosition);
                
                return;
            }
        }
        
        if (null != droppableBoxes.get(dropSource.getId()) && !dropSource.getId().equals(droppedAt.getId())) {
            QuickSelectableEntity itemAtDropLocation = droppableBoxes.get(droppedAt.getId()).getDisplayedItem();
            QuickSelectableEntity itemAtDropSource = droppableBoxes.get(dropSource.getId()).getDisplayedItem();
            
            if (null != itemAtDropSource) {
                droppableBoxes.get(droppedAt.getId()).setDisplayedItem(itemAtDropSource);
                
                int dropPosition = Integer.parseInt(droppedAt.getId().replaceFirst(DROPPABLE_BOX_NAME_PREFIX, "")) - 1;
                blockPlacementController.setSelectableItemAtPosition(itemAtDropSource, dropPosition);
            
                if (null != itemAtDropLocation) {
                    droppableBoxes.get(dropSource.getId()).setDisplayedItem(itemAtDropLocation);
                    
                    dropPosition = Integer.parseInt(dropSource.getId().replaceFirst(DROPPABLE_BOX_NAME_PREFIX, "")) - 1;
                    blockPlacementController.setSelectableItemAtPosition(itemAtDropLocation, dropPosition);
                } else {
                    droppableBoxes.get(dropSource.getId()).clearDisplayedItem();
                    dropPosition = Integer.parseInt(dropSource.getId().replaceFirst(DROPPABLE_BOX_NAME_PREFIX, "")) - 1;
                    blockPlacementController.setSelectableItemAtPosition(null, dropPosition);
                }             
            }       
        }
    }
    
    @Override
    public void onDragAndDropItemRemovedFromGrid(Droppable dropSource, Draggable draggable, Droppable droppedAt) {
        if (null != dropSource && null != droppableBoxes.get(dropSource.getId())) {
            BlockPlacementController blockPlacementController = YaumrGame.getInstance().getClassInstanceContainer().getSingletonInstance(BlockPlacementController.class);            
            droppableBoxes.get(dropSource.getId()).clearDisplayedItem();
            
            int dropPosition = Integer.parseInt(dropSource.getId().replaceFirst(DROPPABLE_BOX_NAME_PREFIX, "")) - 1;
            blockPlacementController.setSelectableItemAtPosition(null, dropPosition);            
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @NiftyEventSubscriber(pattern="droppableBox[0-9]+")
    public void itemDroppedToQuickSelection(String id, final DroppableDroppedEvent event) {
        System.out.println("event fired");
        
        for (Integer key : itemBoxes.keySet()) {
            if (itemBoxes.get(key).getId().equals(event.getDraggable().getId())) {
                
            }
        }
    }
    
    //</editor-fold>
}
