package de.ethasia.yaumr.core.combat;

import java.util.Random;

public class Formulas {
    
    public static int calculateBaseDamageFromPhysicalAttack(CharacterAttributes attackerAttributes, CharacterAttributes defenderAttributes) {
        int attackPower = attackerAttributes.getBasePhysicalAttackPower() + attackerAttributes.getAdditionalPhysicalAttackPower();
        int defense = defenderAttributes.getBasePhysicalDefense() + defenderAttributes.getAdditionalPhysicalDefense();
        
        return (attackPower * attackPower) / (attackPower + defense);
    }
    
    public static int calculateBaseDamageFromMagicalAttack(CharacterAttributes attackerAttributes, CharacterAttributes defenderAttributes) {
        int magicAttackPower = attackerAttributes.getBaseMagicAttackPower() + attackerAttributes.getAdditionalMagicAttackPower();
        int magicDefense = defenderAttributes.getBaseMagicDefense() + defenderAttributes.getAdditionalMagicDefense();
        
        return (magicAttackPower * magicAttackPower) / (magicAttackPower + magicDefense);
    }    
    
    public static int multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(int damageNumber, double randomFactorMinimum) {
        if (randomFactorMinimum > 1) {
            randomFactorMinimum = 1;
        }
        
        Random generator = new Random();
        double randomDamageMultiplier = Formulas.mapDoubleBetweenZeroAndOneToNewMinAndMax(generator.nextDouble(), randomFactorMinimum, 1.f);
        
        return (int)Math.floor(randomDamageMultiplier * damageNumber);
    }
    
    public static double mapDoubleBetweenZeroAndOneToNewMinAndMax(double randomDoubleZeroToOne, double newMin, double newMax) {
        return randomDoubleZeroToOne * (newMax - newMin) + newMin;
    }
}
