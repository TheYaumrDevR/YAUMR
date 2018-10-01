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
    public void testCalculateBaseDamageFromPhysicalAttack_DefenseIsTwice_DamageIsTwoThirds() {
        CharacterAttributes attacker = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        CharacterAttributes defender = new CharacterAttributes(BaseCharacterClassBranches.ROGUE);
        
        attacker.levelUpBy(19);
        attacker.assignFreePointsToAgility(80);
        defender.setAdditionalPhysicalDefense(39);

        int damage = Formulas.calculateBaseDamageFromPhysicalAttack(attacker, defender);
        
        assertEquals(6, damage);
    }    
}
