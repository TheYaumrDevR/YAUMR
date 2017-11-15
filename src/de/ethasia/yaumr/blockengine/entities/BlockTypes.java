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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return true;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.0625f, 0.03125f, 0f, 0.03125f, 0f, 0.015625f, 0.0625f, 0.015625f, // Back
                0.0625f, 0.03125f, 0f, 0.03125f, 0f, 0.015625f, 0.0625f, 0.015625f, // Right
                0.0625f, 0.03125f, 0f, 0.03125f, 0f, 0.015625f, 0.0625f, 0.015625f, // Front
                0.0625f, 0.03125f, 0f, 0.03125f, 0f, 0.015625f, 0.0625f, 0.015625f, // Left
                0.125f, 0.015625f, 0.125f, 0.03125f, 0.0625f, 0.03125f, 0.0625f, 0.015625f, // Top
                0.0625f, 0.03125f, 0.0625f, 0.015625f, 0.125f, 0.015625f, 0.125f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        } 
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.25f, 0.03125f, 0.1875f, 0.03125f, 0.1875f, 0.015625f, 0.25f, 0.015625f, // Back
                0.25f, 0.03125f, 0.1875f, 0.03125f, 0.1875f, 0.015625f, 0.25f, 0.015625f, // Right
                0.25f, 0.03125f, 0.1875f, 0.03125f, 0.1875f, 0.015625f, 0.25f, 0.015625f, // Front
                0.25f, 0.03125f, 0.1875f, 0.03125f, 0.1875f, 0.015625f, 0.25f, 0.015625f, // Left
                0.3125f, 0.015625f, 0.3125f, 0.03125f, 0.25f, 0.03125f, 0.25f, 0.015625f, // Top
                0.25f, 0.03125f, 0.25f, 0.015625f, 0.3125f, 0.015625f, 0.3125f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }  
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.375f, 0.03125f, 0.3125f, 0.03125f, 0.3125f, 0.015625f, 0.375f, 0.015625f, // Back
                0.375f, 0.03125f, 0.3125f, 0.03125f, 0.3125f, 0.015625f, 0.375f, 0.015625f, // Right
                0.375f, 0.03125f, 0.3125f, 0.03125f, 0.3125f, 0.015625f, 0.375f, 0.015625f, // Front
                0.375f, 0.03125f, 0.3125f, 0.03125f, 0.3125f, 0.015625f, 0.375f, 0.015625f, // Left
                0.4375f, 0.015625f, 0.4375f, 0.03125f, 0.375f, 0.03125f, 0.375f, 0.015625f, // Top
                0.375f, 0.03125f, 0.375f, 0.015625f, 0.4375f, 0.015625f, 0.4375f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }   
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.1875f, 0.03125f, 0.125f, 0.03125f, 0.125f, 0.015625f, 0.1875f, 0.015625f, // Back
                0.1875f, 0.03125f, 0.125f, 0.03125f, 0.125f, 0.015625f, 0.1875f, 0.015625f, // Right
                0.1875f, 0.03125f, 0.125f, 0.03125f, 0.125f, 0.015625f, 0.1875f, 0.015625f, // Front
                0.1875f, 0.03125f, 0.125f, 0.03125f, 0.125f, 0.015625f, 0.1875f, 0.015625f, // Left
                0.1875f, 0.015625f, 0.1875f, 0.03125f, 0.125f, 0.03125f, 0.125f, 0.015625f, // Top
                0.125f, 0.03125f, 0.125f, 0.015625f, 0.1875f, 0.015625f, 0.1875f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.5f, 0.03125f, 0.4375f, 0.03125f, 0.4375f, 0.015625f, 0.5f, 0.015625f, // Back
                0.5f, 0.03125f, 0.4375f, 0.03125f, 0.4375f, 0.015625f, 0.5f, 0.015625f, // Right
                0.5f, 0.03125f, 0.4375f, 0.03125f, 0.4375f, 0.015625f, 0.5f, 0.015625f, // Front
                0.5f, 0.03125f, 0.4375f, 0.03125f, 0.4375f, 0.015625f, 0.5f, 0.015625f, // Left
                0.5f, 0.015625f, 0.5f, 0.03125f, 0.4375f, 0.03125f, 0.4375f, 0.015625f, // Top
                0.4375f, 0.03125f, 0.4375f, 0.015625f, 0.5f, 0.015625f, 0.5f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }  
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.5625f, 0.03125f, 0.5f, 0.03125f, 0.5f, 0.015625f, 0.5625f, 0.015625f, // Back
                0.5625f, 0.03125f, 0.5f, 0.03125f, 0.5f, 0.015625f, 0.5625f, 0.015625f, // Right
                0.5625f, 0.03125f, 0.5f, 0.03125f, 0.5f, 0.015625f, 0.5625f, 0.015625f, // Front
                0.5625f, 0.03125f, 0.5f, 0.03125f, 0.5f, 0.015625f, 0.5625f, 0.015625f, // Left
                0.5625f, 0.015625f, 0.5625f, 0.03125f, 0.5f, 0.03125f, 0.5f, 0.015625f, // Top
                0.5f, 0.03125f, 0.5f, 0.015625f, 0.5625f, 0.015625f, 0.5625f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }   
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
                0.625f, 0.03125f, 0.5625f, 0.03125f, 0.5625f, 0.015625f, 0.625f, 0.015625f, // Back
                0.625f, 0.03125f, 0.5625f, 0.03125f, 0.5625f, 0.015625f, 0.625f, 0.015625f, // Right
                0.625f, 0.03125f, 0.5625f, 0.03125f, 0.5625f, 0.015625f, 0.625f, 0.015625f, // Front
                0.625f, 0.03125f, 0.5625f, 0.03125f, 0.5625f, 0.015625f, 0.625f, 0.015625f, // Left
                0.625f, 0.015625f, 0.625f, 0.03125f, 0.5625f, 0.03125f, 0.5625f, 0.015625f, // Top
                0.5625f, 0.03125f, 0.5625f, 0.015625f, 0.625f, 0.015625f, 0.625f, 0.03125f // Bottom
            };
        }
        
        @Override
        public BlockShape getShape() {
            return StandardCubeShape.getInstance();
        }  
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
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
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
        }        
    },
    AIR {
        
        @Override
        public boolean hidesNeighborBlocks() {
            return false;
        }

        @Override
        public float[] getUVCoordinates() {
            // Air blocks aren't rendered.
            return new float[]{};
        }
        
        @Override
        public BlockShape getShape() {
            return null;
        }   
        
        @Override
        public boolean fallsDownWhenAirBelow() {
            return false;
        }        
    };
    
    private boolean isPickable;
    private BlockTypes droppedBlock;
    
    public abstract boolean hidesNeighborBlocks();
    public abstract boolean fallsDownWhenAirBelow();
    public abstract float[] getUVCoordinates();
    public abstract BlockShape getShape();
}
