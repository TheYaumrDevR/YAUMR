package de.ethasia.yaumr.core.combat.tests;

import de.ethasia.yaumr.core.combat.BaseCharacterClassBranches;
import de.ethasia.yaumr.core.combat.CharacterAttributes;
import de.ethasia.yaumr.core.combat.Formulas;

import static org.junit.Assert.*;
import org.junit.Test;

public class FormulasTest {
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_DefenseIsZero_DamageIsAttack() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToStrength(80);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(20, damage);
    }
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_DefenseIsEqual_DamageIsHalfed() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToPerception(80);
        defender.setAdditionalPhysicalDefense(19);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(10, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_DefenseIsTwice_DamageIsOneThird() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        defender.setAdditionalPhysicalDefense(39);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(6, damage);
    } 
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_DefenseIsQuadruple_DamageIsTwoNinths() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        defender.setAdditionalPhysicalDefense(79);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(4, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_MagicalDefenseIsZero_DamageIsAttack() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(20, damage);
    }
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_MagicalDefenseIsEqual_DamageIsHalfed() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(19);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(10, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromMAgicalAttack_MagicalDefenseIsTwice_DamageIsOneThird() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(39);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(6, damage);
    } 
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_MagicalDefenseIsQuadruple_DamageIsTwoNinths() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(79);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(4, damage);
    }    
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_OldMinIsMappedToNewMin() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(0, 0.5, 1);
        assertEquals(0.5, output, 0.01);
    }
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_OldMaxIsMappedToNewMax() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(1, 0.5, 1);
        assertEquals(1, output, 0.01);
    }   
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_ZeroPointFiveIsMappedToNewMiddleValue() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(0.5, 0.5, 1);
        assertEquals(0.75, output, 0.01);
    }    
    
    @Test
    public void testMultiplyDamageNumberWithRandomFactorBetweenMinimumAndOne_LowestDamageIsFiftyPercent_DamagesAreCorrect() {
        for (int i = 0; i < 1000; i++) {
            int randomizedDamage = Formulas.multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(20, 0.5);
            assertTrue(randomizedDamage >= 10);
        }
    }
    
    @Test
    public void testMultiplyDamageNumberWithRandomFactorBetweenMinimumAndOne_LowestDamageIsThreeFourths_DamagesAreCorrect() {
        for (int i = 0; i < 1000; i++) {
            int randomizedDamage = Formulas.multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(20, 0.75);
            assertTrue(randomizedDamage >= 15);
        }
    }    
}
