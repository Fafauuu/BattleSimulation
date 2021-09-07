package model.objects.units;

import animations.Animation;
import animations.AxemanBasicAttackAnimation;
import animations.KnightBasicAttackAnimation;
import gui.labels.UnitLabel;
import model.Attack;
import model.AttackTypes;
import model.Side;
import model.Statistics;
import model.objects.Unit;
import service.Engine;
import service.UnitDatabase;

import java.util.ArrayList;
import java.util.List;

public class Axeman extends AUnit{

    public Axeman(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                1000,
                100,
                60,
                30,
                10,
                new Attack(AttackTypes.PHYSICAL, 60, 1),
                new Attack(AttackTypes.TRUE, 60, 1.9),
                true,
                0
        );

        this.label = new UnitLabel(this, "src/icons/axeman.png", setBackgroundColor(side));
    }

    @Override
    public Animation performBasicAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 30);

        engine.addDamageLabelToTarget(
                target,
                statistics.getBasicAttack().getAttackType(),
                damage
        );
        engine.updateFieldAfterAttack(this, target);
        return new AxemanBasicAttackAnimation(this, target);
    }

    @Override
    public Animation performSpecialAttack(Engine engine, UnitDatabase unitDatabase) {
        List<Unit> listOfTargets = new ArrayList<>(8);
        int damage = 0;
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 30);

        engine.addDamageLabelToTarget(
                target,
                statistics.getBasicAttack().getAttackType(),
                damage
        );
        engine.updateFieldAfterAttack(this, target);

        return new AxemanBasicAttackAnimation(this, target);
    }

    @Override
    public String toString() {
        return side.toString().charAt(0) + "_Axeman[" + XCoordinate + "," + YCoordinate + "] ";
    }
}
