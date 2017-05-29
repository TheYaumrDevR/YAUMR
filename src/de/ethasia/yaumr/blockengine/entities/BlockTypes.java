package de.ethasia.yaumr.blockengine.entities;

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
    },
    WATERED_EARTH {
        
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
    },
    PLOWED_EARTH {
        
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
    },
    PLOWED_WATERED_EARTH {
        
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
    },
    PLOWED_SEEDED_EARTH {
        
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
    },
    PLOWED_SEEDED_WATERED_EARTH {
        
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
    },
    CLAY {
        
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
                0, 0.015625f, 0.1875f, 0, 0.25f, 0, 0.25f, 0.015625f // Bottom
            };
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
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
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
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
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
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
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
                0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
                1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
                0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
                -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
                0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
                0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
            };
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
    };
    
    private boolean isPickable;
    private BlockTypes droppedBlock;
    
    public abstract boolean hidesNeighborBlocks();
    public abstract float[] getUVCoordinates();
}
