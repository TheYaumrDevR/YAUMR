package de.ethasia.yaumr.interactors;

import de.ethasia.yaumr.base.ClassInstanceContainer;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.core.BlockPlacementTerraformingTool;
import de.ethasia.yaumr.core.BlockRemovalTerraformingTool;
import de.ethasia.yaumr.core.blocks.Block;
import de.ethasia.yaumr.core.blocks.BlockTypes;
import de.ethasia.yaumr.core.blocks.SimpleBlockFactory;
import de.ethasia.yaumr.core.interfaces.IslandManipulationFacade;
import de.ethasia.yaumr.interactors.interfaces.IslandEditorStateSetupInteractor;
import de.ethasia.yaumr.interactors.interfaces.TerraformingToolsInteractor;

/**
 *
 * @author R
 */
public class IslandEditorStateSetupInteractorImpl implements IslandEditorStateSetupInteractor {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void setupInventoryInteractorsForIslandEditorState() {
        ClassInstanceContainer dependencyResolver = YaumrGame.getInstance().getClassInstanceContainer();
        TerraformingToolsInteractor toolsSelector = dependencyResolver.getImplementationInstance(TerraformingToolsInteractor.class);
        IslandManipulationFacade islandManipulationFacade = dependencyResolver.getSingletonInstance(IslandManipulationFacade.class);
        toolsSelector.resetToDimensions(1, 25);
        toolsSelector.setIslandManipulationFacade(islandManipulationFacade);
        
        Block earthBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.EARTH);
        Block bedrockBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BEDROCK);
        Block rockBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.ROCK);
        Block clayBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.CLAY);
        Block sandBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.SAND);
        Block walnutTrunkBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.WALNUT_TRUNK);
        Block birchTrunkBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.BIRCH_TRUNK);
        Block oakTrunkBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.OAK_TRUNK);
        Block leavesBlock = SimpleBlockFactory.createConcreteBlockFromBlockType(BlockTypes.LEAVES);
        
        BlockPlacementTerraformingTool addEarthBlockTool = new BlockPlacementTerraformingTool(earthBlock);
        BlockPlacementTerraformingTool addBedrockBlockTool = new BlockPlacementTerraformingTool(bedrockBlock);
        BlockPlacementTerraformingTool addRockBlockTool = new BlockPlacementTerraformingTool(rockBlock);
        BlockPlacementTerraformingTool addClayBlockTool = new BlockPlacementTerraformingTool(clayBlock);
        BlockPlacementTerraformingTool addSandBlockTool = new BlockPlacementTerraformingTool(sandBlock);
        BlockPlacementTerraformingTool addWalnutTrunkBlockTool = new BlockPlacementTerraformingTool(walnutTrunkBlock);
        BlockPlacementTerraformingTool addBirchTrunkBlockTool = new BlockPlacementTerraformingTool(birchTrunkBlock);
        BlockPlacementTerraformingTool addOakTrunkBlockTool = new BlockPlacementTerraformingTool(oakTrunkBlock);
        BlockPlacementTerraformingTool addLeavesBlockTool = new BlockPlacementTerraformingTool(leavesBlock);
        BlockRemovalTerraformingTool blockRemovalTool = new BlockRemovalTerraformingTool();
        
        toolsSelector.setToolToSelectionPosition(addEarthBlockTool, 0);
        toolsSelector.setToolToSelectionPosition(addBedrockBlockTool, 1);
        toolsSelector.setToolToSelectionPosition(addRockBlockTool, 2);
        toolsSelector.setToolToSelectionPosition(addClayBlockTool, 3);
        toolsSelector.setToolToSelectionPosition(addSandBlockTool, 4);
        toolsSelector.setToolToSelectionPosition(addWalnutTrunkBlockTool, 5);
        toolsSelector.setToolToSelectionPosition(addBirchTrunkBlockTool, 6);
        toolsSelector.setToolToSelectionPosition(addOakTrunkBlockTool, 7);
        toolsSelector.setToolToSelectionPosition(addLeavesBlockTool, 8);        
        toolsSelector.setToolToSelectionPosition(blockRemovalTool, 9);       
        
        dependencyResolver.removeSingletonInstance(TerraformingToolsInteractor.class);
        dependencyResolver.registerSingletonInstance(TerraformingToolsInteractor.class, toolsSelector);
    }    
    
    //</editor-fold>    
}
