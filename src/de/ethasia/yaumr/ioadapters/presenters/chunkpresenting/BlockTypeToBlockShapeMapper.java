package de.ethasia.yaumr.ioadapters.presenters.chunkpresenting;

import de.ethasia.yaumr.core.blocks.BlockTypes;

/**
 *
 * @author R
 */
public class BlockTypeToBlockShapeMapper {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static BlockShape getBlockShapeForBlockType(BlockTypes blockType) {
        switch(blockType) {
            case GRASSY_EARTH:
            case EARTH:
            case EARTH_PLOWED:
            case EARTH_WATERED:
            case EARTH_PLOWED_WATERED:
            case EARTH_SEEDED:
            case EARTH_SEEDED_WATERED:
            case CLAY:
            case ROCK:
            case SAND:
            case BEDROCK:
            case LEAVES:
            case WALNUT_TRUNK:
            case OAK_TRUNK:
            case ASH_TRUNK:
            case BIRCH_TRUNK:
            case WALNUT_PLANKS:
            case OAK_PLANKS:
            case ASH_PLANKS:
            case BIRCH_PLANKS:
                return StandardCubeShape.getInstance();
            case WALNUT_STAIRS:
                break;
            case BIRCH_STAIRS:
                break;
            case OAK_STAIRS:
                break;
            case ASH_STAIRS:
                break;
            case WALNUT_ROOF:
                break;
            case OAK_ROOF:
                break;
            case ASH_ROOF:
                break;
            case BIRCH_ROOF:
                break;
            case STRAW_ROOF:
                break;
            case WALNUT_DOOR:
                break;
            case OAK_DOOR:
                break;
            case ASH_DOOR:
                break;
            case BIRCH_DOOR:
                break;
            case COPPER_VEIN:
            case CASSITERITE:
            case IRON_ORE:
            case SILVER_VEIN:
            case GOLD_VEIN:
                return StandardCubeShape.getInstance();
            case OCEAN_WATER:
                break;
            case INLAND_WATER:
                break;
            case AIR:
                return EmptyBlockShape.getInstance();
        }
        
        throw new UnsupportedOperationException("BlockShape for block type " + blockType + " not found. Please add it.");        
    }
    
    //</editor-fold>
}
