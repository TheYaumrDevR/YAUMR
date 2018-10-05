package de.ethasia.yaumr.core.combat.tests;

import de.ethasia.yaumr.core.combat.BaseCharacterClassBranches;
import de.ethasia.yaumr.core.combat.CharacterAttributes;
import de.ethasia.yaumr.core.combat.Formulas;

import static org.junit.Assert.*;
import org.junit.Test;

public class FormulasTest {
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_defenseIsZero_damageIsAttack() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToStrength(80);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(20, damage);
    }
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_defenseIsEqual_damageIsHalfed() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToPerception(80);
        defender.setAdditionalPhysicalDefense(19);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(10, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_defenseIsTwice_damageIsOneThird() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        defender.setAdditionalPhysicalDefense(39);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(6, damage);
    } 
    
    @Test
    public void testCalculateBaseDamageFromPhysicalAttack_defenseIsQuadruple_damageIsTwoNinths() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        defender.setAdditionalPhysicalDefense(79);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(4, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_magicalDefenseIsZero_damageIsAttack() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(20, damage);
    }
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_magicalDefenseIsEqual_damageIsHalfed() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(19);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(10, damage);
    }    
    
    @Test
    public void testCalculateBaseDamageFromMAgicalAttack_magicalDefenseIsTwice_damageIsOneThird() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(39);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(6, damage);
    } 
    
    @Test
    public void testCalculateBaseDamageFromMagicalAttack_magicalDefenseIsQuadruple_damageIsTwoNinths() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        defender.setAdditionalMagicDefense(79);

        int damage = Formulas.calculateBaseDamageFromMagicalAttack(attacker, defender);
        
        assertEquals(4, damage);
    }    
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_oldMinIsMappedToNewMin() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(0, 0.5, 1);
        assertEquals(0.5, output, 0.01);
    }
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_oldMaxIsMappedToNewMax() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(1, 0.5, 1);
        assertEquals(1, output, 0.01);
    }   
    
    @Test
    public void testMapDoubleBetweenZeroAndOneToNewMinAndMax_zeroPointFiveIsMappedToNewMiddleValue() {
        double output = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(0.5, 0.5, 1);
        assertEquals(0.75, output, 0.01);
    }    
    
    @Test
    public void testMultiplyDamageNumberWithRandomFactorBetweenMinimumAndOne_lowestDamageIsFiftyPercent_damagesAreCorrect() {
        for (int i = 0; i < 1000; i++) {
            int randomizedDamage = Formulas.multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(20, 0.5);
            assertTrue(randomizedDamage >= 10);
        }
    }
    
    @Test
    public void testMultiplyDamageNumberWithRandomFactorBetweenMinimumAndOne_lowestDamageIsThreeFourths_damagesAreCorrect() {
        for (int i = 0; i < 1000; i++) {
            int randomizedDamage = Formulas.multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(20, 0.75);
            assertTrue(randomizedDamage >= 15);
        }
    }    
    
    @Test 
    public void testGetCriticalHitChanceFromCriticalHitChanceValue_characterIsLevelOne_thirtyIsThirtyPercent() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.setAdditionalCriticalHitChanceValue(30);
        
        float actual = Formulas.getCriticalHitChanceFromCriticalHitChanceValue(attacker);
        float expected = 0.3f;
        
        assertEquals(expected, actual, 0.01f);
    }
    
    @Test 
    public void testGetCriticalHitChanceFromCriticalHitChanceValue_characterIsLevelTwenty_1200IsSixtyPercent() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(19);
        attacker.setAdditionalCriticalHitChanceValue(1200);
        
        float actual = Formulas.getCriticalHitChanceFromCriticalHitChanceValue(attacker);
        float expected = 0.6f;
        
        assertEquals(expected, actual, 0.01f);
    }  
    
    @Test 
    public void testGetCriticalHitChanceFromCriticalHitChanceValue_characterIsLevelTen_700IsSeventyPercent() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(9);
        attacker.setAdditionalCriticalHitChanceValue(700);
        
        float actual = Formulas.getCriticalHitChanceFromCriticalHitChanceValue(attacker);
        float expected = 0.7f;
        
        assertEquals(expected, actual, 0.01f);
    } 

    @Test
    public void testIsCriticalHit_chanceIsHalf_roughlyHalfCriticalHitsAreGenerated() {
        int numberOfCriticalHits = 0;
        
        for (int i = 0; i < 1000; i++) {
            if (Formulas.isCriticalHit(0.5f)) {
                numberOfCriticalHits++;
            }
        }
        
        boolean criticalHitsAreInReasonableRange = numberOfCriticalHits > 461 && numberOfCriticalHits < 539;
        assertTrue(criticalHitsAreInReasonableRange);
    } 
    
    @Test
    public void testIsCriticalHit_chanceIsEightyPercent_roughlyThatManyAreCriticalHits() {
        int numberOfCriticalHits = 0;
        
        for (int i = 0; i < 1000; i++) {
            if (Formulas.isCriticalHit(0.8f)) {
                numberOfCriticalHits++;
            }
        }
        
        boolean criticalHitsAreInReasonableRange = numberOfCriticalHits > 757 && numberOfCriticalHits < 843;
        assertTrue(criticalHitsAreInReasonableRange);
    }     
    
    @Test
    public void testDecideIfCriticalHitAndReturnDamageMultiplier_criticalHitChanceIsOne_criticalHitMultiplierIsReturned() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        attacker.setAdditionalCriticalHitDamageMultiplier(1.f);
        attacker.setAdditionalCriticalHitChanceValue(100);
        
        float actualDamageMultiplier = Formulas.decideIfCriticalHitAndReturnDamageMultiplier(attacker);
        
        assertEquals(2.5f, actualDamageMultiplier, 0.01f);
    }
    
    @Test
    public void testDecideIfCriticalHitAndReturnDamageMultiplier_criticalHitChanceIsZero_neutralMultiplierIsReturned() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        attacker.setAdditionalCriticalHitDamageMultiplier(1.f);
        
        float actualDamageMultiplier = Formulas.decideIfCriticalHitAndReturnDamageMultiplier(attacker);
        
        assertEquals(1.f, actualDamageMultiplier, 0.01f);
    }    
    
    @Test
    public void testApplyFullPhysicalDamageToDefender_damageIsStableCritChanceIsZero_fullDamageIsCalculated() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        attacker.levelUpBy(9);
        attacker.assignFreePointsToAgility(40);
        attacker.setLowestDamageFromFullFactor(1.f);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        Formulas.applyFullPhysicalDamageToDefender(attacker, defender);
        
        assertEquals(90, defender.getCurrentHealthPoints());
    }
    
    @Test
    public void testApplyFullPhysicalDamageToDefender_damageIsStableCritChanceIsZeroDefenseIsEqual_halfDamageIsCalculated() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        attacker.setLowestDamageFromFullFactor(1.f);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        defender.setAdditionalPhysicalDefense(19);
        
        Formulas.applyFullPhysicalDamageToDefender(attacker, defender);
        
        assertEquals(90, defender.getCurrentHealthPoints());
    }    
    
    @Test
    public void testApplyFullPhysicalDamageToDefender_damageIsUnstableFiftyPercentNoCriticalHitChance_damageDoneIsBetweenHalfAndFull() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToStrength(80);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        
        Formulas.applyFullPhysicalDamageToDefender(attacker, defender);
        
        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints <= 90 && defenderHealthPoints >= 80;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    } 
    
    @Test
    public void testApplyFullPhysicalDamageToDefender_damageIsStableCriticalHitChanceIsHalf_damageDoneIsBetweenFullAndThriceFull() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToPerception(80);
        attacker.setAdditionalCriticalHitDamageMultiplier(1.5f);
        attacker.setLowestDamageFromFullFactor(1.f);
        attacker.setAdditionalCriticalHitChanceValue(1000);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        Formulas.applyFullPhysicalDamageToDefender(attacker, defender);
        
        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints == 80 || defenderHealthPoints == 40;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    }    
    
    @Test
    public void testApplyFullPhysicalDamageToDefender_damageIsUnstableHalfCriticalHitIsHalfDefenseIsEqual_calculationIsCorrect() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToPerception(80);
        attacker.setAdditionalCriticalHitChanceValue(1000);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        defender.setAdditionalPhysicalDefense(20);
        
        Formulas.applyFullPhysicalDamageToDefender(attacker, defender);

        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints > 84 || defenderHealthPoints < 96;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    }    
    
    @Test
    public void testApplyFullMagicalDamageToDefender_damageIsStableCritChanceIsZero_fullDamageIsCalculated() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(9);
        attacker.assignFreePointsToIntelligence(40);
        attacker.setLowestDamageFromFullFactor(1.f);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        
        Formulas.applyFullMagicalDamageToDefender(attacker, defender);
        
        assertEquals(90, defender.getCurrentHealthPoints());
    }
    
    @Test
    public void testApplyFullMagicalDamageToDefender_damageIsStableCritChanceIsZeroDefenseIsEqual_halfDamageIsCalculated() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        attacker.setLowestDamageFromFullFactor(1.f);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WARRIOR);
        defender.setAdditionalMagicDefense(19);
        
        Formulas.applyFullMagicalDamageToDefender(attacker, defender);
        
        assertEquals(90, defender.getCurrentHealthPoints());
    }    
    
    @Test
    public void testApplyFullMagicalDamageToDefender_damageIsUnstableFiftyPercentNoCriticalHitChance_damageDoneIsBetweenHalfAndFull() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.MARKSMAN);
        
        Formulas.applyFullMagicalDamageToDefender(attacker, defender);
        
        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints <= 90 && defenderHealthPoints >= 80;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    } 
    
    @Test
    public void testApplyFullMagicalDamageToDefender_damageIsStableCriticalHitChanceIsHalf_damageDoneIsBetweenFullAndThriceFull() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        attacker.setAdditionalCriticalHitDamageMultiplier(1.5f);
        attacker.setLowestDamageFromFullFactor(1.f);
        attacker.setAdditionalCriticalHitChanceValue(1000);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        Formulas.applyFullMagicalDamageToDefender(attacker, defender);
        
        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints == 80 || defenderHealthPoints == 40;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    }    
    
    @Test
    public void testApplyFullMagicalDamageToDefender_damageIsUnstableHalfCriticalHitIsHalfDefenseIsEqual_calculationIsCorrect() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        attacker.levelUpBy(19);
        attacker.assignFreePointsToIntelligence(80);
        attacker.setAdditionalCriticalHitChanceValue(1000);
        
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.WIZARD);
        defender.setAdditionalMagicDefense(20);
        
        Formulas.applyFullMagicalDamageToDefender(attacker, defender);

        int defenderHealthPoints = defender.getCurrentHealthPoints();
        boolean defenderHealthPointsAreInExpectedRange = defenderHealthPoints > 84 || defenderHealthPoints < 96;
        
        assertTrue(defenderHealthPointsAreInExpectedRange);
    }    
}
