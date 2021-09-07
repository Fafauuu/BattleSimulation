package service;

import model.Attack;
import model.objects.Unit;

public interface DamageCalculationService {
    int calculate(Unit attacker, Unit defender, Attack attack);
}
