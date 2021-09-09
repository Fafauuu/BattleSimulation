package model.objects.units;

import animations.*;
import gui.labels.UnitLabel;
import model.Attack;
import model.AttackTypes;
import model.Side;
import model.Statistics;
import service.Engine;

public class Archer extends AUnit{

    public Archer(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                100,
                100,
                75,
                20,
                10,
                new Attack(AttackTypes.PHYSICAL, 30, 5),
                new Attack(AttackTypes.PHYSICAL, 70, 5),
                7
        );

        this.label = new UnitLabel(this, "src/graphics/icons/archerIcon.png", setBackgroundColor(side));
    }

    @Override
    public Animation performBasicAttack(Engine engine) {
        engine.countDistance(this, target);
        Animation animation;
        int manaGain;
        int ammunitionLoss;

        if (engine.countDistance(this, target) > 1 && statistics.getAmmunition() > 0){
            animation = new ArcherBasicAttackAnimation(this, target);
            manaGain = 25;
            ammunitionLoss = 1;
            statistics.getBasicAttack().setBaseDamage(30);
        } else {
            animation = new ArcherMeleeAttackAnimation(this, target);
            manaGain = 0;
            ammunitionLoss = 0;
            statistics.getBasicAttack().setBaseDamage(15);
        }

        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + manaGain);
        statistics.reduceAmmunition(ammunitionLoss);

        if (statistics.getAmmunition() == 0){
            setMeleeRange();
        }

        engine.addDamageLabelToTarget(target, statistics.getBasicAttack().getAttackType(), damage);
        engine.updateFieldAfterAttack(this, target);
        return animation;
    }

    @Override
    public Animation performSpecialAttack(Engine engine) {

        if (engine.countDistance(this, target) > 1 && statistics.getAmmunition() > 0){
            int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getSpecialAttack());
            target.getStatistics().setHp(target.getStatistics().getHp() - damage);
            this.statistics.setMana(0);
            statistics.reduceAmmunition(1);

            if (statistics.getAmmunition() == 0){
                setMeleeRange();
            }

            engine.addDamageLabelToTarget(target, statistics.getSpecialAttack().getAttackType(), damage);
            engine.updateFieldAfterAttack(this, target);
            return new ArcherSpecialAttackAnimation(this, target);

        } else {
            return performBasicAttack(engine);
        }
    }

    private void setMeleeRange() {
        statistics.getBasicAttack().setRange(1);
        statistics.getSpecialAttack().setRange(1);
    }

    @Override
    public String toString() {
        return "[" + XCoordinate + "," + YCoordinate + "] " + side.toString().charAt(0) + "_Archer";
    }
}
