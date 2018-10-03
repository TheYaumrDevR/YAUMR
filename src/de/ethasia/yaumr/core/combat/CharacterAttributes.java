package de.ethasia.yaumr.core.combat;

/**
 *
 * @author R
 */
public class CharacterAttributes {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int ATTRIBUTE_POINTS_PER_LEVEL = 4;
    public static final int ATTRIBUTE_POINTS_FOR_ONE_ATTACK_POINT = 4;
    public static final int MAXIMUM_LEVEL = 20;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int level;
    private int unassignedAttributePoints;
    private BaseCharacterClassBranches baseCharacterClass;
    
    private int baseMaximumHealthPoints;
    private int baseMaximumMagicPoints;
    
    private int currentHealthPoints;
    private int currentMagicPoints;
    
    private int baseStrength;
    private int baseIntelligence;
    private int basePerception;
    private int baseAgility;
    
    private int basePhysicalAttackPower;
    private int basePhysicalDefense;
    private int baseMagicAttackPower;
    private int baseMagicDefense;
    private int baseCriticalHitChanceValue;
    
    private float baseCriticalHitDamageMultiplier;

    private int baseSpeed;
    private int baseJumpHeight;
    
    private int additionalMaximumHealthPoints;
    private int additionalMaximumMagicPoints;
    
    private int additionalStrength;
    private int additionalIntelligence;
    private int additionalPerception;
    private int additionalAgility;
    
    private int additionalPhysicalAttackPower;
    private int additionalPhysicalDefense;
    private int additionalMagicAttackPower;
    private int additionalMagicDefense;
    private int additionalCriticalHitChanceValue;
    
    private float additionalCriticalHitDamageMultiplier;
    
    private int additionalSpeed;
    private int additionalJumpHeight;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public int getLevel() {
        return level;
    }
    
    public int getUnassignedAttributePoints() {
        return unassignedAttributePoints;
    }
    
    public BaseCharacterClassBranches getBaseCharacterClass() {
        return baseCharacterClass;
    }
    
    public int getBaseMaximumHealthPoints() {
        return baseMaximumHealthPoints;
    }
    
    private int getBaseMaximumMagicPoints() {
        return baseMaximumMagicPoints;
    }
    
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }
    
    public int getCurrentMagicPoints() {
        return currentMagicPoints;
    }   
    
    public int getBaseStrength() {
        return baseStrength;
    }
    
    public int getBaseIntelligence() {
        return baseIntelligence;
    }
    
    public int getBasePerception() {
        return basePerception;
    }
    
    public int getBaseAgility() {
        return baseAgility;
    }
    
    public int getBasePhysicalAttackPower() {
        return basePhysicalAttackPower;
    }
    
    public int getBasePhysicalDefense() {
        return basePhysicalDefense;
    }
    
    public int getBaseMagicAttackPower() {
        return baseMagicAttackPower;
    }
    
    public int getBaseMagicDefense() {
        return baseMagicDefense;
    }
    
    public int getBaseCriticalHitChanceValue() {
        return baseCriticalHitChanceValue;
    }
    
    public float getBaseCriticalHitDamageMultiplier() {
        return baseCriticalHitDamageMultiplier;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }
    
    public int getBaseJumpHeight() {
        return baseJumpHeight;
    }   
    
    public int getAdditionalMaximumHealthPoints() {
        return additionalMaximumHealthPoints;
    }
    
    public int getAdditionalMaximumMagicPoints() {
        return additionalMaximumMagicPoints;
    }
    
    public int getAdditionalStrength() {
        return additionalStrength;
    }
    
    public int getAdditionalIntelligence() {
        return additionalIntelligence;
    }
    
    public int getAdditionalPerception() {
        return additionalPerception;
    }
    
    public int getAdditionalAgility() {
        return additionalAgility;
    }
    
    public int getAdditionalPhysicalAttackPower() {
        return additionalPhysicalAttackPower;
    }
    
    public int getAdditionalPhysicalDefense() {
        return additionalPhysicalDefense;
    }
    
    public int getAdditionalMagicAttackPower() {
        return additionalMagicAttackPower;
    }
    
    public int getAdditionalMagicDefense() {
        return additionalMagicDefense;
    }
    
    public int getAdditionalCriticalHitChanceValue() {
        return additionalCriticalHitChanceValue;
    }
    
    public float getAdditionalCriticalHitDamageMultiplier() {
        return additionalCriticalHitDamageMultiplier;
    }
    
    public int getAdditionalSpeed() {
        return additionalSpeed;
    }
    
    public int getAdditionalJumpHeight() {
        return additionalJumpHeight;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    public void setAdditionalPhysicalDefense(int value) {
        additionalPhysicalDefense = value;
    }
    
    public void setAdditionalMagicDefense(int value) {
        additionalMagicDefense = value;
    }    
    
    public void setAdditionalCriticalHitChanceValue(int value) {
        additionalCriticalHitChanceValue = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CharacterAttributes(BaseCharacterClassBranches baseCharacterClass) {
        level = 1;
        unassignedAttributePoints = ATTRIBUTE_POINTS_PER_LEVEL;
        this.baseCharacterClass = baseCharacterClass;
        
        baseMaximumHealthPoints = 100;
        baseMaximumMagicPoints = 100;
        
        currentHealthPoints = 100;
        currentMagicPoints = 100;
        
        baseStrength = 1;
        baseIntelligence = 1;
        basePerception = 1;
        baseAgility = 1;
        
        baseCriticalHitDamageMultiplier = 1.5f;
        
        baseSpeed = 1;
        baseJumpHeight = 1;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void levelUpBy(int amount) {
        checkValidityOfLevelUpOperationAndThrowExceptionIfInvalid(amount);
        
        level += amount;
        unassignedAttributePoints += ATTRIBUTE_POINTS_PER_LEVEL * amount;
    }
    
    public void assignFreePointsToStrength(int amount) {
        checkIfEnoughFreeAttributePointsAreLeftAndThrowExceptionIfNot(amount);
        
        unassignedAttributePoints -= amount;
        baseStrength += amount;
        
        recalculateDerivedAttributes();
    }
    
    public void assignFreePointsToIntelligence(int amount) {
        checkIfEnoughFreeAttributePointsAreLeftAndThrowExceptionIfNot(amount);
        
        unassignedAttributePoints -= amount;
        baseIntelligence += amount;   
        
        recalculateDerivedAttributes();
    }
    
    public void assignFreePointsToPerception(int amount) {
        checkIfEnoughFreeAttributePointsAreLeftAndThrowExceptionIfNot(amount);
        
        unassignedAttributePoints -= amount;
        basePerception += amount;    
        
        recalculateDerivedAttributes();
    }
    
    public void assignFreePointsToAgility(int amount) {
        checkIfEnoughFreeAttributePointsAreLeftAndThrowExceptionIfNot(amount);
        
        unassignedAttributePoints -= amount;
        baseAgility += amount;  
        
        recalculateDerivedAttributes();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void recalculateDerivedAttributes() {
        recalculateClassBasedDerivedAttributes();
    }
    
    private void recalculateClassBasedDerivedAttributes() {
        switch (baseCharacterClass) {
            case WARRIOR:
                basePhysicalAttackPower = baseStrength / ATTRIBUTE_POINTS_FOR_ONE_ATTACK_POINT;
                break;
            case WIZARD:
                baseMagicAttackPower = baseIntelligence / ATTRIBUTE_POINTS_FOR_ONE_ATTACK_POINT;
                break;
            case MARKSMAN:
                basePhysicalAttackPower = basePerception / ATTRIBUTE_POINTS_FOR_ONE_ATTACK_POINT;
                break;
            case ROGUE:
                basePhysicalAttackPower = baseAgility / ATTRIBUTE_POINTS_FOR_ONE_ATTACK_POINT;
                break;
        } 
        
        ensureThatBaseAttackPowersAreAtLeastOne();
    }
    
    private void ensureThatBaseAttackPowersAreAtLeastOne() {
        if (basePhysicalAttackPower < 1) {
            basePhysicalAttackPower = 1;
        }
        
        if (baseMagicAttackPower < 1) {
            baseMagicAttackPower = 1;
        }        
    }
    
    private void checkValidityOfLevelUpOperationAndThrowExceptionIfInvalid(int levelUpAmount) {
        if (level + levelUpAmount > MAXIMUM_LEVEL) {
            throw new CannotLevelUpAboveMaximumCharacterLevelException(MAXIMUM_LEVEL);
        }
    }
    
    private void checkIfEnoughFreeAttributePointsAreLeftAndThrowExceptionIfNot(int amountOfPointsToAssign) {
        if (amountOfPointsToAssign > unassignedAttributePoints) {
            throw new NotEnoughAttributePointsToAssignException();
        }
    }
    
    //</editor-fold>
}
