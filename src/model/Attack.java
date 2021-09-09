package model;

public class Attack {
    private final AttackTypes attackType;
    private  int baseDamage;
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

    public void setRange(double range) {
        this.range = range;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}
