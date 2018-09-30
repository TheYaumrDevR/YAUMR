package de.ethasia.yaumr.core.combat.tests;

import de.ethasia.yaumr.core.combat.BaseCharacterClassBranches;
import de.ethasia.yaumr.core.combat.CannotLevelUpAboveMaximumCharacterLevelException;
import de.ethasia.yaumr.core.combat.CharacterAttributes;
import de.ethasia.yaumr.core.combat.NotEnoughAttributePointsToAssignException;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharacterAttributesTest {
    
    @Test
    public void testLevelUpByAmount_CanLevelUp_LevelAndFreeAttributePointsAreAdded() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        
        testCandidate.levelUpBy(3);
        
        int expectedLevel = 4;
        int expectedFreeAttributePoints = CharacterAttributes.ATTRIBUTE_POINTS_PER_LEVEL * 4;
        
        assertEquals(expectedLevel, testCandidate.getLevel());
        assertEquals(expectedFreeAttributePoints, testCandidate.getUnassignedAttributePoints());
    }
    
    @Test
    public void testAssignFreePointsToStrength_ValidAmountIsAssigned_BaseStrengthAndUnassignedPointsUpdateProperly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        testCandidate.levelUpBy(4);
        
        testCandidate.assignFreePointsToStrength(20);
        
        int expectedStrength = 21;
        int expectedFreePoints = 0;
        
        assertEquals(expectedStrength, testCandidate.getBaseStrength());
        assertEquals(expectedFreePoints, testCandidate.getUnassignedAttributePoints());
    }
    
    @Test
    public void testAssignFreePointsToIntelligence_ValidAmountIsAssigned_BaseIntelligenceAndUnassignedPointsUpdateProperly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        testCandidate.levelUpBy(19);
        
        testCandidate.assignFreePointsToIntelligence(25);
        
        int expectedIntelligence = 26;
        int expectedFreePoints = 55;
        
        assertEquals(expectedIntelligence, testCandidate.getBaseIntelligence());
        assertEquals(expectedFreePoints, testCandidate.getUnassignedAttributePoints());
    }  
    
    @Test
    public void testAssignFreePointsToPerception_ValidAmountIsAssigned_BasePerceptionAndUnassignedPointsUpdateProperly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        testCandidate.levelUpBy(12);
        
        testCandidate.assignFreePointsToPerception(32);
        
        int expectedPerception = 33;
        int expectedFreePoints = 20;
        
        assertEquals(expectedPerception, testCandidate.getBasePerception());
        assertEquals(expectedFreePoints, testCandidate.getUnassignedAttributePoints());
    } 
    
    @Test
    public void testAssignFreePointsToAgility_ValidAmountIsAssigned_BaseAgilityAndUnassignedPointsUpdateProperly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        testCandidate.levelUpBy(17);
        
        testCandidate.assignFreePointsToAgility(59);
        
        int expectedAgility = 60;
        int expectedFreePoints = 13;
        
        assertEquals(expectedAgility, testCandidate.getBaseAgility());
        assertEquals(expectedFreePoints, testCandidate.getUnassignedAttributePoints());
    } 
    
    @Test(expected = CannotLevelUpAboveMaximumCharacterLevelException.class)
    public void testLevelUpByAmount_LevelsOverMaximum_ThrowsException() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        testCandidate.levelUpBy(CharacterAttributes.MAXIMUM_LEVEL);
    }    
    
    @Test(expected = NotEnoughAttributePointsToAssignException.class)
    public void testAssignFreePointsToStrength_MoreThanAvailableAmountIsAssigned_ThrowsException() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        testCandidate.levelUpBy(4);
        
        testCandidate.assignFreePointsToStrength(21);
    }
    
    @Test(expected = NotEnoughAttributePointsToAssignException.class)
    public void testAssignFreePointsToIntelligence_MoreThanAvailableAmountIsAssigned_ThrowsException() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        testCandidate.levelUpBy(19);
        
        testCandidate.assignFreePointsToIntelligence(82);
    }  
    
    @Test(expected = NotEnoughAttributePointsToAssignException.class)
    public void testAssignFreePointsToPerception_MoreThanAvailableAmountIsAssigned_ThrowsException() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        testCandidate.levelUpBy(12);
        
        testCandidate.assignFreePointsToPerception(55);
    } 
    
    @Test(expected = NotEnoughAttributePointsToAssignException.class)
    public void testAssignFreePointsToAgility_MoreThanAvailableAmountIsAssigned_ThrowsException() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        testCandidate.levelUpBy(17);
        
        testCandidate.assignFreePointsToAgility(76);
    }   
    
    @Test
    public void testAssignFreePointsToStrength_ValidAmountIsAssigned_BaseAttackPowerIsCalculatedCorrectly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        testCandidate.levelUpBy(6);
        
        testCandidate.assignFreePointsToStrength(28);
        
        int expectedAttackPower = 7;
        assertEquals(expectedAttackPower, testCandidate.getBasePhysicalAttackPower());
    }

    @Test
    public void testAssignFreePointsToIntelligence_ValidAmountIsAssigned_BaseMagicAttackPowerIsCalculatedCorrectly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        testCandidate.levelUpBy(17);
        
        testCandidate.assignFreePointsToIntelligence(30);
        
        int expectedMagicAttackPower = 7;
        assertEquals(expectedMagicAttackPower, testCandidate.getBaseMagicAttackPower());
    }   
    
    @Test
    public void testAssignFreePointsToPerception_ValidAmountIsAssigned_BaseAttackPowerIsCalculatedCorrectly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        testCandidate.levelUpBy(14);
        
        testCandidate.assignFreePointsToPerception(40);
        
        int expectedAttackPower = 10;
        assertEquals(expectedAttackPower, testCandidate.getBasePhysicalAttackPower());
    } 

    @Test
    public void testAssignFreePointsToAgility_ValidAmountIsAssigned_BaseAttackPowerIsCalculatedCorrectly() {
        CharacterAttributes testCandidate = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        testCandidate.levelUpBy(7);
        
        testCandidate.assignFreePointsToAgility(14);
        
        int expectedAttackPower = 3;
        assertEquals(expectedAttackPower, testCandidate.getBasePhysicalAttackPower());
    }    
}
