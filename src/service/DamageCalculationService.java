package service;

import model.Attack;
import model.objects.units.Unit;

public interface DamageCalculationService {
    int calculate(Unit attacker, Unit defender, Attack attack);
}
