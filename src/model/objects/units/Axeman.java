package model.objects.units;

import animations.Animation;
import animations.AxemanBasicAttackAnimation;
import animations.AxemanSpecialAttackAnimation;
import gui.labels.UnitLabel;
import model.Attack;
import model.AttackTypes;
import model.Side;
import model.Statistics;
import service.Engine;

import java.util.ArrayList;
import java.util.List;

public class Axeman extends AUnit{

    public Axeman(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                100,
                100,
                50,
                30,
                10,
                new Attack(AttackTypes.PHYSICAL, 60, 1),
                new Attack(AttackTypes.TRUE, 60, 1.9),
                0
        );

        this.label = new UnitLabel(this, "src/graphics/icons/axemanIcon.png", setBackgroundColor(side));
    }

    @Override
    public Animation performBasicAttack(Engine engine) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 50);

        engine.addDamageLabelToTarget(target, statistics.getBasicAttack().getAttackType(), damage);
        engine.updateFieldAfterAttack(this, target);
        return new AxemanBasicAttackAnimation(this, target);
    }

    @Override
    public Animation performSpecialAttack(Engine engine) {
        List<Unit> listOfEnemies = engine.setListOfEnemies(this);
        List<Unit> listOfTargets = new ArrayList<>(8);
        for (Unit enemy : listOfEnemies) {
            if (engine.countDistance(this, enemy) <= this.statistics.getSpecialAttack().getRange()){
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

        return new AxemanSpecialAttackAnimation(this, target);
    }

    @Override
    public String toString() {
        return "[" + XCoordinate + "," + YCoordinate + "] " + side.toString().charAt(0) + "_Axeman";
    }
}
