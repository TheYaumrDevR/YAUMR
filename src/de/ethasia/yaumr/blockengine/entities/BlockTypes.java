package de.ethasia.yaumr.blockengine.entities;

import de.ethasia.yaumr.presenters.meshcreation.BlockShape;
import de.ethasia.yaumr.presenters.meshcreation.StandardCubeShape;

/**
 * List all blocktypes available in the game with their basic properties.
 * 
 * @author R
 */
public enum BlockTypes {
    GRASS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Back
                0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Right
                0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Front
                0.125f, 0.015625f, 0.0625f, 0.015625f, 0.0625f, 0, 0.125f, 0, // Left
                0.1875f, 0, 0.1875f, 0.015625f, 0.125f, 0.015625f, 0.125f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }

        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }
    },
    EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Back
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Right
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Front
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Left
                0.0625f, 0, 0.0625f, 0.015625f, 0, 0.015625f, 0, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
       
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    WATERED_EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Back
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Right
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Front
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Left
                0.3125f, 0, 0.3125f, 0.015625f, 0.25f, 0.015625f, 0.25f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    PLOWED_EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Back
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Right
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Front
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Left
                0.4375f, 0, 0.4375f, 0.015625f, 0.375f, 0.015625f, 0.375f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    PLOWED_WATERED_EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Back
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Right
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Front
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Left
                0.5f, 0, 0.5f, 0.015625f, 0.4375f, 0.015625f, 0.4375f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    PLOWED_SEEDED_EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Back
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Right
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Front
                0.0625f, 0.015625f, 0, 0.015625f, 0, 0, 0.0625f, 0, // Left
                0.5625f, 0, 0.5625f, 0.015625f, 0.5f, 0.015625f, 0.5f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    PLOWED_SEEDED_WATERED_EARTH {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Back
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Right
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Front
                0.375f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0, 0.375f, 0, // Left
                0.625f, 0, 0.625f, 0.015625f, 0.5625f, 0.015625f, 0.5625f, 0, // Top
                0, 0.015625f, 0, 0, 0.0625f, 0, 0.0625f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    CLAY {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.6874f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0, 0.6874f, 0, // Back
                0.6874f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0, 0.6874f, 0, // Right
                0.6874f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0, 0.6874f, 0, // Front
                0.6874f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0, 0.6874f, 0, // Left
                0.6874f, 0, 0.6874f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0, // Top
                0.625f, 0.015625f, 0.625f, 0, 0.6874f, 0, 0.6874f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    ROCK {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Back
                0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Right
                0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Front
                0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, // Left
                0.25f, 0, 0.25f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0, // Top
                0.1875f, 0.015625f, 0.1875f, 0, 0.25f, 0, 0.25f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    SAND {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.8125f, 0.015625f, 0.75f, 0.015625f, 0.75f, 0, 0.8125f, 0, // Back
                0.8125f, 0.015625f, 0.75f, 0.015625f, 0.75f, 0, 0.8125f, 0, // Right
                0.8125f, 0.015625f, 0.75f, 0.015625f, 0.75f, 0, 0.8125f, 0, // Front
                0.8125f, 0.015625f, 0.75f, 0.015625f, 0.75f, 0, 0.8125f, 0, // Left
                0.8125f, 0, 0.8125f, 0.015625f, 0.75f, 0.015625f, 0.75f, 0, // Top
                0.75f, 0.015625f, 0.75f, 0, 0.8125f, 0, 0.8125f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    BEDROCK {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.75f, 0.015625f, 0.6875f, 0.015625f, 0.6875f, 0, 0.75f, 0, // Back
                0.75f, 0.015625f, 0.6875f, 0.015625f, 0.6875f, 0, 0.75f, 0, // Right
                0.75f, 0.015625f, 0.6875f, 0.015625f, 0.6875f, 0, 0.75f, 0, // Front
                0.75f, 0.015625f, 0.6875f, 0.015625f, 0.6875f, 0, 0.75f, 0, // Left
                0.75f, 0, 0.75f, 0.015625f, 0.6875f, 0.015625f, 0.6875f, 0, // Top
                0.6875f, 0.015625f, 0.6875f, 0, 0.75f, 0, 0.75f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    LEAVES {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.875f, 0.015625f, 0.8125f, 0.015625f, 0.8125f, 0, 0.875f, 0, // Back
                0.875f, 0.015625f, 0.8125f, 0.015625f, 0.8125f, 0, 0.875f, 0, // Right
                0.875f, 0.015625f, 0.8125f, 0.015625f, 0.8125f, 0, 0.875f, 0, // Front
                0.875f, 0.015625f, 0.8125f, 0.015625f, 0.8125f, 0, 0.875f, 0, // Left
                0.875f, 0, 0.875f, 0.015625f, 0.8125f, 0.015625f, 0.8125f, 0, // Top
                0.8125f, 0.015625f, 0.8125f, 0, 0.875f, 0, 0.875f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    WALNUT_WOOD {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0.9375f, 0.015625f, 0.875f, 0.015625f, 0.875f, 0, 0.9375f, 0, // Back
                0.9375f, 0.015625f, 0.875f, 0.015625f, 0.875f, 0, 0.9375f, 0, // Right
                0.9375f, 0.015625f, 0.875f, 0.015625f, 0.875f, 0, 0.9375f, 0, // Front
                0.9375f, 0.015625f, 0.875f, 0.015625f, 0.875f, 0, 0.9375f, 0, // Left
                1.f, 0, 1.f, 0.015625f, 0.9375f, 0.015625f, 0.9375f, 0, // Top
                0.9375f, 0.015625f, 0.9375f, 0, 1.f, 0, 1.f, 0.015625f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    OAK_WOOD {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    ASH_WOOD {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    BIRCH_WOOD {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    WALNUT_PLANKS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    OAK_PLANKS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    ASH_PLANKS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    BIRCH_PLANKS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    WALNUT_STAIRS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }        
    },
    OAK_STAIRS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    ASH_STAIRS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    BIRCH_STAIRS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    WALNUT_ROOF {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    OAK_ROOF {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    ASH_ROOF {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    BIRCH_ROOF {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    STRAW_ROOF {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    WALNUT_DOOR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    OAK_DOOR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }        
    },
    ASH_DOOR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    BIRCH_DOOR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }         
    },
    GLASS {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    COPPER_VEIN {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    CASSITERITE {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };        
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    IRON_ORE {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };        
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    SILVER_VEIN {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };            
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    GOLD_VEIN {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return true;
        }

        @Override
        public float[] getUVCoordinates() {
            return new float [] {
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }        
    },
    AIR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            // Air blocks aren't rendered anyway.
            return new float[]{};
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }        
    };
    
    private boolean isPickable;
    private BlockTypes droppedBlock;
    
    public abstract boolean hidesNeighborBlocks();
    public abstract float[] getUVCoordinates();
    public abstract BlockShape getShape();
}
