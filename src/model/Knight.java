package model;

import gui.DamageLabel;
import gui.UnitLabel;
import service.Engine;
import service.UnitDatabase;

import java.awt.*;
import java.util.Timer;

public class Knight implements Unit {
    private final Side side;
    private int XCoordinate;
    private int YCoordinate;
    private Unit target;
    private boolean alive;
    private final Statistics statistics;
    private final UnitLabel label;

    public Knight(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                100,
                100,
                40,
                50,
                0,
                1,
                new Attack(AttackTypes.PHYSICAL, 30),
                new Attack(AttackTypes.TRUE, 80),
                true,
                0
        );

        this.label = new UnitLabel(this, "src/icons/knight.png", setBackgroundColor(side));
    }

    private Color setBackgroundColor(Side side) {
        Color backgroundColor;
        if (side == Side.RED) {
            backgroundColor = Color.RED;
        } else {
            backgroundColor = Color.CYAN;
        }
        return backgroundColor;
    }


    @Override
    public void performBasicAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.calculateBasicAttackDamage(
                this, target, statistics.getBasicAttack().getAttackType());

        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 40);

        addDamageLabelToTarget(
                target,
                statistics.getBasicAttack().getAttackType(),
                damage,
                engine.getTimer()
        );
    }

    @Override
    public void performSpecialAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.calculateSpecialAttackDamage(
                this, target, statistics.getSpecialAttack().getAttackType());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(0);
        label.getManaBarLabel().updateManaBar();

        addDamageLabelToTarget(
                target,
                statistics.getSpecialAttack().getAttackType(),
                damage,
                engine.getTimer()
        );
    }

    public void addDamageLabelToTarget(Unit target, AttackTypes attackType, int damage, Timer animationTimer) {
        DamageLabel damageLabel = new DamageLabel(target, attackType, damage);

        target.getUnitLabel().addDamageLabel(damageLabel, animationTimer);
    }


    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public int getXCoordinate() {
        return XCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public Unit getTarget() {
        return target;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public UnitLabel getUnitLabel() {
        return label;
    }

    public void setXCoordinate(int XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public void setYCoordinate(int YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    @Override
    public void setTarget(Unit target) {
        this.target = target;
    }

    public void setDead() {
        this.alive = false;
        this.label.setVisible(false);
    }

    @Override
    public String toString() {
        return side.toString().charAt(0) + "_Knight[" + XCoordinate + "," + YCoordinate + "] ";
    }
}
