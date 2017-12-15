package yaumrrefactored.core.blocks;

import java.util.HashMap;
import java.util.Map;

/**
 * Lists all available block types and their metadata.
 * 
 * @author R
 */
public enum BlockTypes {
    GRASSY_EARTH {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }         
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            Map<String, Boolean> result = new HashMap<>();
            
            result.put("grassPlowedEarthToEarth", Boolean.TRUE);
            
            return result;
        }        
    },
    
    EARTH {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }        

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }
    },
    
    EARTH_PLOWED {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }   
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }  
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            Map<String, Boolean> result = new HashMap<>();
            
            result.put("grassPlowedEarthToEarth", Boolean.TRUE);
            
            return result;
        }        
    },
    
    EARTH_WATERED {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    EARTH_PLOWED_WATERED {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }  
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            Map<String, Boolean> result = new HashMap<>();
            
            result.put("grassPlowedEarthToEarth", Boolean.TRUE);
            
            return result;
        }        
    },
    
    EARTH_SEEDED {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }      
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    EART_SEEDED_WATERED {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        } 
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }
    },
    
    CLAY {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ROCK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }        
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    SAND {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            Map<String, Boolean> result = new HashMap<>();
            
            result.put("fallingSandyBlock", Boolean.TRUE);
            
            return result;
        }        
    },
    
    BEDROCK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    LEAVES {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }        

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_TRUNK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }    
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_TRUNK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }      
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_TRUNK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }        
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_TRUNK {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }     
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_PLANKS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   

        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_PLANKS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }     
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_PLANKS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_PLANKS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }       
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_STAIRS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return true;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStairBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_STAIRS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return true;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStairBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_STAIRS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return true;
        } 
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStairBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_STAIRS {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisX() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return true;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStairBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_ROOF {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }    
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_ROOF {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }    
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_ROOF {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        } 

        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_ROOF {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }        
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    STRAW_ROOF {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }      
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_DOOR {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        } 
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_DOOR {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        }    
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_DOOR {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }  
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_DOOR {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        }    
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return true;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        } 

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    COPPER_VEIN {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }    
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    CASSITERITE {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }      
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }   
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }  
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    IRON_ORE {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }   
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    SILVER_VEIN {

        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }       
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }   
        
        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    GOLD_VEIN {
        
        @Override
        public boolean isDisplaced() {
            return false;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return true;
        }  
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return createCoveringDataForStandardBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OCEAN_WATER { // This has an infinite volume and spreads into all gaps until it fills up to the sea level.
        
        @Override
        public boolean isDisplaced() {
            return true;
        }
        
        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getAffectingAutomatonNamesForFluids();
        }
    },
    
    INLAND_WATER { // Non-infinite water. It spreads until it has no volume left.
        
        @Override
        public boolean isDisplaced() {
            return true;
        }
        
        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }

        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getAffectingAutomatonNamesForFluids();
        }
    },
    
    AIR {
        
        @Override
        public boolean isDisplaced() {
            return true;
        }

        @Override
        public boolean displacesDisplacableBlock() {
            return false;
        } 
        
        @Override
        public boolean canRotateAroundAxisX() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisY() {
            return false;
        }
        
        @Override
        public boolean canRotateAroundAxisZ() {
            return false;
        }

        @Override
        public BlockFacesOfBlock getFacesWithCoveringData() {
            return new BlockFacesOfBlock();
        } 
        
        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    };
    
    public abstract boolean isDisplaced();
    public abstract boolean displacesDisplacableBlock();
    public abstract boolean canRotateAroundAxisX();
    public abstract boolean canRotateAroundAxisY();
    public abstract boolean canRotateAroundAxisZ();
    public abstract BlockFacesOfBlock getFacesWithCoveringData();
    public abstract Map<String, Boolean> getAffectingAutomatonNames();
    
    private static BlockFacesOfBlock createCoveringDataForStandardBlock() {
            BlockFacesOfBlock faceCoveringData = new BlockFacesOfBlock();
            
            faceCoveringData.setBackFaceIsCovering(true);
            faceCoveringData.setFrontFaceIsCovering(true);
            faceCoveringData.setRightFaceIsCovering(true);
            faceCoveringData.setLeftFaceIsCovering(true);
            faceCoveringData.setTopFaceIsCovering(true);
            faceCoveringData.setBottomFaceIsCovering(true);
            
            return faceCoveringData; 
    }
    
    private static BlockFacesOfBlock createCoveringDataForStairBlock() {
            BlockFacesOfBlock faceCoveringData = new BlockFacesOfBlock();
            
            faceCoveringData.setBackFaceIsCovering(true);
            faceCoveringData.setBottomFaceIsCovering(true);
            
            return faceCoveringData; 
    }
    
    private static Map<String, Boolean> getEmptyAffectingAutomatonNames() {
        return new HashMap<>();
    }
    
    private static Map<String, Boolean> getAffectingAutomatonNamesForFluids() {
        Map<String, Boolean> result = new HashMap<>();
            
        result.put("fluidsFlowCA", Boolean.TRUE);
            
        return result;
    }
}
