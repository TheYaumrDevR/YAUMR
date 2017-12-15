package yaumrrefactored.core.blocks;

/**
 *
 * @author R
 */
public class SimpleBlockFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static Block createConcreteBlockFromBlockType(BlockTypes blockType) {
        switch (blockType) {
            case ASH_DOOR:
            case BIRCH_DOOR:
            case WALNUT_DOOR:
            case OAK_DOOR:
                return new DoorBlock(blockType);
            case AIR:
            case ASH_STAIRS:
            case ASH_ROOF:
            case ASH_PLANKS:
            case ASH_TRUNK:
            case BEDROCK:
            case BIRCH_STAIRS:
            case BIRCH_ROOF:
            case BIRCH_PLANKS:
            case BIRCH_TRUNK:
            case CASSITERITE:
            case CLAY:
            case COPPER_VEIN:
            case EARTH:
            case EARTH_PLOWED:
            case EARTH_PLOWED_WATERED:
            case EARTH_SEEDED:
            case EARTH_WATERED:
            case EARTH_SEEDED_WATERED:
            case GOLD_VEIN:
            case GRASSY_EARTH:
            case INLAND_WATER:
            case IRON_ORE:
            case LEAVES:
            case OAK_STAIRS:
            case OAK_ROOF:
            case OAK_PLANKS:
            case OAK_TRUNK:
            case OCEAN_WATER:
            case ROCK:
            case SAND:
            case SILVER_VEIN:
            case STRAW_ROOF:
            case WALNUT_STAIRS:
            case WALNUT_ROOF:
            case WALNUT_PLANKS:
            case WALNUT_TRUNK:
                return new Block(blockType);
            default:
                throw new IllegalArgumentException("Internal error: BlockType is not supported by factory. Please add.");
        }
    }
    
    public static String getBlockSubTypeNameForBlockType(BlockTypes blockType) {
        switch (blockType) {
            case ASH_DOOR:
            case BIRCH_DOOR:
            case WALNUT_DOOR:
            case OAK_DOOR:
                return "door";
            case AIR:
            case ASH_STAIRS:
            case ASH_ROOF:
            case ASH_PLANKS:
            case ASH_TRUNK:
            case BEDROCK:
            case BIRCH_STAIRS:
            case BIRCH_ROOF:
            case BIRCH_PLANKS:
            case BIRCH_TRUNK:
            case CASSITERITE:
            case CLAY:
            case COPPER_VEIN:
            case EARTH:
            case EARTH_PLOWED:
            case EARTH_PLOWED_WATERED:
            case EARTH_SEEDED:
            case EARTH_WATERED:
            case EARTH_SEEDED_WATERED:
            case GOLD_VEIN:
            case GRASSY_EARTH:
            case INLAND_WATER:
            case IRON_ORE:
            case LEAVES:
            case OAK_STAIRS:
            case OAK_ROOF:
            case OAK_PLANKS:
            case OAK_TRUNK:
            case OCEAN_WATER:
            case ROCK:
            case SAND:
            case SILVER_VEIN:
            case STRAW_ROOF:
            case WALNUT_STAIRS:
            case WALNUT_ROOF:
            case WALNUT_PLANKS:
            case WALNUT_TRUNK:
                return "normal";
            default:
                throw new IllegalArgumentException("Internal error: Subclass for BlockType is unknown.");
        }        
    }
    
    //</editor-fold>
}
