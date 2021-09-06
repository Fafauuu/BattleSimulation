package model;

import animations.Animation;
import animations.KnightBasicAttackAnimation;
import gui.UnitLabel;
import service.Engine;
import service.UnitDatabase;

import java.awt.*;

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
            backgroundColor = new Color(0xFF7C7C);
        } else {
            backgroundColor = new Color(0xA0C3EA);
        }
        return backgroundColor;
    }


    @Override
    public Animation performBasicAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.calculateBasicAttackDamage(
                this, target, statistics.getBasicAttack().getAttackType());

        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 40);

        engine.addDamageLabelToTarget(
                target,
                statistics.getBasicAttack().getAttackType(),
                damage
        );
        engine.updateFieldAfterAttack(this, target);
        return new KnightBasicAttackAnimation(this, target);
    }

    @Override
    public Animation performSpecialAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.calculateSpecialAttackDamage(
                this, target, statistics.getSpecialAttack().getAttackType());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(0);
        label.getManaBarLabel().updateManaBar();

        engine.addDamageLabelToTarget(
                target,
                statistics.getSpecialAttack().getAttackType(),
                damage
        );
        engine.updateFieldAfterAttack(this, target);
        return new KnightBasicAttackAnimation(this, target);
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
