package model.objects.units;

import animations.Animation;
import animations.KnightBasicAttackAnimation;
import animations.KnightSpecialAttackAnimation;
import gui.labels.UnitLabel;
import model.Attack;
import model.AttackTypes;
import model.Side;
import model.Statistics;
import service.Engine;
import service.UnitDatabase;

public class Knight extends AUnit {

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
                20,
                new Attack(AttackTypes.PHYSICAL, 30, 1),
                new Attack(AttackTypes.MAGICAL, 60, 1),
                true,
                0
        );

        this.label = new UnitLabel(this, "src/icons/knight.png", setBackgroundColor(side));
    }


    @Override
    public Animation performBasicAttack(Engine engine, UnitDatabase unitDatabase) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
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
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getSpecialAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(0);
        label.getManaBarLabel().updateManaBar();

        engine.addDamageLabelToTarget(
                target,
                statistics.getSpecialAttack().getAttackType(),
                damage
        );
        engine.updateFieldAfterAttack(this, target);
        return new KnightSpecialAttackAnimation(this, target);
    }

    @Override
    public String toString() {
        return side.toString().charAt(0) + "_Knight[" + XCoordinate + "," + YCoordinate + "] ";
    }
}
