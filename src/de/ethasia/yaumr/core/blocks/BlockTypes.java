package de.ethasia.yaumr.core.blocks;

import java.util.HashMap;
import java.util.Map;

/**
 * Lists all available block types and their metadata.
 * 
 * @author R
 */
public enum BlockTypes {
    GRASSY_EARTH(0) {

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
    
    EARTH(1) {

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
            
            result.put("earthBlocksDailyUpdate", Boolean.TRUE);
            
            return result;
        }
    },
    
    EARTH_PLOWED(2) {

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
            result.put("earthBlocksDailyUpdate", Boolean.TRUE);
            
            return result;
        }        
    },
    
    EARTH_WATERED(3) {

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
            
            result.put("earthBlocksDailyUpdate", Boolean.TRUE);
            
            return result;
        }        
    },
    
    EARTH_PLOWED_WATERED(4) {

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
            result.put("earthBlocksDailyUpdate", Boolean.TRUE);
            
            return result;
        }       
    },
    
    EARTH_SEEDED(5) {

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
    
    EARTH_SEEDED_WATERED(6) {

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
            
            result.put("earthBlocksDailyUpdate", Boolean.TRUE);
            
            return result;
        }
    },
    
    CLAY(7) {

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
    
    ROCK(8) {

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
    
    SAND(9) {

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
    
    BEDROCK(10) {

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
    
    LEAVES(11) {

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
    
    WALNUT_TRUNK(12) {

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
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    OAK_TRUNK(13) {

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
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    ASH_TRUNK(14) {

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
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    BIRCH_TRUNK(15) {

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
            return createCoveringDataForStandardBlock();
        }

        @Override
        public Map<String, Boolean> getAffectingAutomatonNames() {
            return getEmptyAffectingAutomatonNames();
        }        
    },
    
    WALNUT_PLANKS(16) {

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
    
    OAK_PLANKS(17) {

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
    
    ASH_PLANKS(18) {

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
    
    BIRCH_PLANKS(19) {

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
    
    WALNUT_STAIRS(20) {

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
    
    OAK_STAIRS(21) {

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
    
    ASH_STAIRS(22) {

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
    
    BIRCH_STAIRS(23) {

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
    
    WALNUT_ROOF(24) {

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
    
    OAK_ROOF(25) {

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
    
    ASH_ROOF(26) {

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
    
    BIRCH_ROOF(27) {

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
    
    STRAW_ROOF(28) {

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
    
    WALNUT_DOOR(29) {

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
    
    OAK_DOOR(30) {

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
    
    ASH_DOOR(31) {

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
    
    BIRCH_DOOR(32) {

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
    
    COPPER_VEIN(33) {

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
    
    CASSITERITE(34) {

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
    
    IRON_ORE(35) {

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
    
    SILVER_VEIN(36) {

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
    
    GOLD_VEIN(37) {
        
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
    
    OCEAN_WATER(38) { // This has an infinite volume and spreads into all gaps until it fills up to the sea level.
        
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
    
    INLAND_WATER(39) { // Non-infinite water. It spreads until it has no volume left.
        
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
    
    AIR(40) {
        
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
    
    private final int value;
    private BlockTypes(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    
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
