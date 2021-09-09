package model;

public class Statistics {
    private final int maxHp;
    private int hp;
    private final int maxMana;
    private int mana;
    private final int armour;
    private final int magicResist;
    private final Attack basicAttack;
    private final Attack specialAttack;
    private int ammunition;

    public Statistics(int maxHp, int maxMana, int mana, int armour, int magicResist,
                      Attack basicAttack, Attack specialAttack, int ammunition) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMana = maxMana;
        this.mana = mana;
        this.armour = armour;
        this.magicResist = magicResist;
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
        this.ammunition = ammunition;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getArmour() {
        return armour;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public Attack getBasicAttack() {
        return basicAttack;
    }

    public Attack getSpecialAttack() {
        return specialAttack;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void reduceAmmunition(int number) {
        this.ammunition -= number;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
