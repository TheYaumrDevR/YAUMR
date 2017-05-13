package de.ethasia.yaumr.entities;

/**
 * List all blocktypes available in the game with their basic properties.
 * 
 * @author Drawig
 */
public enum BlockTypes {
    GRASS,
    DIRT,
    WATERED_DIRT,
    PLOWED_DIRT,
    PLOWED_WATERED_DIRT,
    PLOWED_SEEDED_DIRT,
    PLOWED_SEEDED_WATERED_DIRT,
    CLAY,
    ROCK,
    SAND,
    BEDROCK,
    LEAVES,
    WALNUT_WOOD,
    OAK_WOOD,
    ASH_WOOD,
    BIRCH_WOOD,
    WALNUT_PLANKS,
    OAK_PLANKS,
    ASH_PLANKS,
    BIRCH_PLANKS,
    WALNUT_STAIRS,
    OAK_STAIRS,
    ASH_STAIRS,
    BIRCH_STAIRS,
    WALNUT_ROOF,
    OAK_ROOF,
    ASH_ROOF,
    BIRCH_ROOF,
    STRAW_ROOF,
    WALNUT_DOOR,
    OAK_DOOR,
    ASH_DOOR,
    BIRCH_DOOR,
    GLASS,
    COPPER_VEIN,
    CASSITERITE,
    IRON_ORE,
    SILVER_VEIN,
    GOLD_VEIN,
    AIR;
    
    private boolean isPickable;
    private BlockTypes droppedBlock;
}
