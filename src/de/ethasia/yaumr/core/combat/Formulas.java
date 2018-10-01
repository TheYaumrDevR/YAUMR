package de.ethasia.yaumr.core.combat;

public class Formulas {
    
    public static int calculateBaseDamageFromPhysicalAttack(CharacterAttributes attackerAttributes, CharacterAttributes defenderAttributes) {
        int attackPower = attackerAttributes.getBasePhysicalAttackPower() + attackerAttributes.getAdditionalPhysicalAttackPower();
        int defense = defenderAttributes.getBasePhysicalDefense() + defenderAttributes.getAdditionalPhysicalDefense();
        
        return (attackPower * attackPower) / (attackPower + defense);
    }
}
