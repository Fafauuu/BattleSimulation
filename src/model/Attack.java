package model;

public class Attack {
    private final AttackTypes attackType;
    private final int baseDamage;
    private double range;

    public Attack(AttackTypes attackType, int baseDamage, double range) {
        this.attackType = attackType;
        this.baseDamage = baseDamage;
        this.range = range;
    }

    public AttackTypes getAttackType() {
        return attackType;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getRange() {
        return range;
    }
}
