package model;

public class Attack {
    private final AttackTypes attackType;
    private final int baseDamage;

    public Attack(AttackTypes attackType, int baseDamage) {
        this.attackType = attackType;
        this.baseDamage = baseDamage;
    }

    public AttackTypes getAttackType() {
        return attackType;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
