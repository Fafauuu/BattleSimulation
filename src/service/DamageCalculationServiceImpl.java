package service;

import model.Attack;
import model.AttackTypes;
import model.objects.units.Unit;

public class DamageCalculationServiceImpl implements DamageCalculationService{

    @Override
    public int calculate(Unit attacker, Unit defender, Attack attack) {
        int attackDamageNegated
                = (int) Math.ceil(attack.getBaseDamage()
                * calculateDamageNegationPercentage(defender, attack));
        return attack.getBaseDamage() - attackDamageNegated;
    }

    private double calculateDamageNegationPercentage(Unit defender, Attack attack) {
        int defensiveStatisticValue;

        if (attack.getAttackType() == AttackTypes.PHYSICAL) {
            defensiveStatisticValue = defender.getStatistics().getArmour();
        } else if (attack.getAttackType() == AttackTypes.MAGICAL) {
            defensiveStatisticValue = defender.getStatistics().getMagicResist();
        } else defensiveStatisticValue = 0;

        return (double) defensiveStatisticValue / (defensiveStatisticValue + 100);
    }
}
