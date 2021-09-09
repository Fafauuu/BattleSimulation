package model.objects.units;

import animations.*;
import gui.labels.UnitLabel;
import model.Attack;
import model.AttackTypes;
import model.Side;
import model.Statistics;
import service.Engine;

import java.util.ArrayList;
import java.util.List;

public class Mage extends AUnit{

    public Mage(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                100,
                100,
                0,
                20,
                10,
                new Attack(AttackTypes.MAGICAL, 20, 3),
                new Attack(AttackTypes.MAGICAL, 30, 3),
                0
        );

        this.label = new UnitLabel(this, "src/graphics/icons/mageIcon.png", setBackgroundColor(side));
    }

    @Override
    public Animation performBasicAttack(Engine engine) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMaxMana());

        engine.addDamageLabelToTarget(target, statistics.getBasicAttack().getAttackType(), damage);
        engine.updateFieldAfterAttack(this, target);
        return new MageBasicAttackAnimation(this, target);
    }

    @Override
    public Animation performSpecialAttack(Engine engine) {
        List<Unit> listOfEnemies = engine.setListOfEnemies(this);
        List<Unit> listOfTargets = new ArrayList<>(8);
        for (Unit enemy : listOfEnemies) {
            if (engine.countDistance(this.target, enemy) <= 1.5){
                listOfTargets.add(enemy);
            }
        }

        this.statistics.setMana(0);

        for (Unit target : listOfTargets) {
            int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getSpecialAttack());
            target.getStatistics().setHp(target.getStatistics().getHp() - damage);

            engine.addDamageLabelToTarget(target, statistics.getSpecialAttack().getAttackType(), damage);
            engine.updateFieldAfterAttack(this, target);
        }

        return new MageSpecialAttackAnimation(this, target);
    }

    @Override
    public String toString() {
        return "[" + XCoordinate + "," + YCoordinate + "] " + side.toString().charAt(0) + "_Mage";
    }
}
