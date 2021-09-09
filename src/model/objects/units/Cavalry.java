package model.objects.units;

import animations.Animation;
import animations.CavalryBasicAttackAnimation;
import animations.CavalrySpecialAttackAnimation;
import animations.EmptyAnimation;
import exceptions.CantMoveObjectException;
import gui.labels.UnitLabel;
import model.*;
import service.Engine;

import java.util.List;

public class Cavalry extends AUnit {

    public Cavalry(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.alive = true;

        this.statistics = new Statistics(
                100,
                100,
                100,
                50,
                40,
                new Attack(AttackTypes.PHYSICAL, 20, 1),
                new Attack(AttackTypes.PHYSICAL, 100, 3),
                0
        );

        this.label = new UnitLabel(this, "src/graphics/icons/cavalryIcon.png", setBackgroundColor(side));
    }

    @Override
    public Animation performBasicAttack(Engine engine) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getBasicAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(statistics.getMana() + 20);

        engine.addDamageLabelToTarget(target, statistics.getBasicAttack().getAttackType(), damage);
        engine.updateFieldAfterAttack(this, target);

        if (target.getStatistics().getHp() <= 0) {
            this.statistics.setMana(this.statistics.getMaxMana());
            engine.updateFieldAfterAttack(this, target);
        }
        return new CavalryBasicAttackAnimation(this, target);
    }

    @Override
    public Animation performSpecialAttack(Engine engine) {
        double initialDistance;

        while (engine.countDistance(this, target) > this.statistics.getBasicAttack().getRange()) {
            List<Actions> movementActions = engine.setFirstAndSecondDirection(this);
             initialDistance = engine.countDistance(this, target);
            try {
                charge(engine, movementActions.get(0));
            } catch (CantMoveObjectException e) {
                try {
                    charge(engine, movementActions.get(1));
                    if (engine.countDistance(this, target) > initialDistance){
                        return new EmptyAnimation(this, target);
                    }
                } catch (CantMoveObjectException ex) {
                    return new EmptyAnimation(this, target);
                }
            }
        }
        dealSpecialDamage(engine);
        return new CavalrySpecialAttackAnimation(this, target);
    }

    private void dealSpecialDamage(Engine engine) {
        int damage = engine.getDamageCalculationService().calculate(this, target, statistics.getSpecialAttack());
        target.getStatistics().setHp(target.getStatistics().getHp() - damage);
        this.statistics.setMana(0);
        label.getManaBarLabel().updateManaBar();

        engine.addDamageLabelToTarget(target, statistics.getSpecialAttack().getAttackType(), damage);
        engine.updateFieldAfterAttack(this, target);

        if (target.getStatistics().getHp() <= 0) {
            this.getStatistics().setMana(this.getStatistics().getMaxMana());
            engine.updateFieldAfterAttack(this, target);
        }
    }

    private void charge(Engine engine, Actions action) throws CantMoveObjectException {

        int xModifier = 0;
        int yModifier = 0;
        switch (action) {
            case MOVE_UP:
                xModifier = -1;
                yModifier = 0;
                break;
            case MOVE_DOWN:
                xModifier = 1;
                yModifier = 0;
                break;
            case MOVE_LEFT:
                xModifier = 0;
                yModifier = -1;
                break;
            case MOVE_RIGHT:
                xModifier = 0;
                yModifier = 1;
                break;
        }
        engine.move(this, xModifier, yModifier);
    }

    @Override
    public String toString() {
        return "[" + XCoordinate + "," + YCoordinate + "] " + side.toString().charAt(0) + "_Cavalry";
    }
}
