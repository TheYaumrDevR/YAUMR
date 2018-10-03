package de.ethasia.yaumr.core.combat;

import java.util.Random;

public class Formulas {
    
    public static void applyFullPhysicalDamageToDefender(CharacterAttributes attackerAttributes, CharacterAttributes defenderAttributes) {
        int baseDamage = calculateBaseDamageFromPhysicalAttack(attackerAttributes, defenderAttributes);
        int finalDamage = getFinalDamageFromBaseDamage(baseDamage, attackerAttributes);

        defenderAttributes.subtractDamageFromHealth(finalDamage);
    }
    
    public static void applyFullMagicalDamageToDefender(CharacterAttributes attackerAttributes, CharacterAttributes defenderAttributes) {
        int baseDamage = calculateBaseDamageFromMagicalAttack(attackerAttributes, defenderAttributes);
        int finalDamage = getFinalDamageFromBaseDamage(baseDamage, attackerAttributes);

        defenderAttributes.subtractDamageFromHealth(finalDamage);
    }    
    
    private static int getFinalDamageFromBaseDamage(int baseDamage, CharacterAttributes attackerAttributes) {
        int unstableDamage = multiplyDamageNumberWithRandomFactorBetweenMinimumAndOne(baseDamage, attackerAttributes.getLowestDamageFromFullFactor());
        float criticalHitFactor = decideIfCriticalHitAndReturnDamageMultiplier(attackerAttributes);
        
        return (int)Math.floor(unstableDamage * criticalHitFactor);        
    }
    
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
        
        Random dice = new Random();
        double randomDamageMultiplier = mapDoubleBetweenZeroAndOneToNewMinAndMax(dice.nextDouble(), randomFactorMinimum, 1.f);
        
        return (int)Math.floor(randomDamageMultiplier * damageNumber);
    }
    
    public static double mapDoubleBetweenZeroAndOneToNewMinAndMax(double randomDoubleZeroToOne, double newMin, double newMax) {
        return randomDoubleZeroToOne * (newMax - newMin) + newMin;
    }
    
    public static float decideIfCriticalHitAndReturnDamageMultiplier(CharacterAttributes attackerAttributes) {
        float criticalHitDamageMultiplier = attackerAttributes.getBaseCriticalHitDamageMultiplier() + attackerAttributes.getAdditionalCriticalHitDamageMultiplier();
        float criticalHitChance = getCriticalHitChanceFromCriticalHitChanceValue(attackerAttributes);
        return isCriticalHit(criticalHitChance) ? criticalHitDamageMultiplier : 1.f;        
    }
    
    public static float getCriticalHitChanceFromCriticalHitChanceValue(CharacterAttributes attackerAttributes) {
        int criticalHitChanceValue = attackerAttributes.getBaseCriticalHitChanceValue() + attackerAttributes.getAdditionalCriticalHitChanceValue();
        int criticalHitChanceValueForOneHundretPercent = attackerAttributes.getLevel() * 100;
        
        if (criticalHitChanceValue >= criticalHitChanceValueForOneHundretPercent) {
            return 1;
        }
        
        return (float)criticalHitChanceValue / criticalHitChanceValueForOneHundretPercent;
    }
    
    public static boolean isCriticalHit(float criticalHitChanceZeroToOne) {
        Random dice = new Random();
        float randomNumber = dice.nextFloat();
        
        return randomNumber <= criticalHitChanceZeroToOne;
    }
}
