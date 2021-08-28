package model;

import gui.DamageLabel;
import gui.UnitLabel;
import service.Engine;
import service.UnitDatabase;

public interface Unit extends SimulationObject{
    void performBasicAttack(Engine engine, UnitDatabase unitDatabase);
    void performSpecialAttack(Engine engine, UnitDatabase unitDatabase);

    Side getSide();
    Unit getTarget();
    boolean isAlive();
    Statistics getStatistics();
    UnitLabel getUnitLabel();

    void setXCoordinate(int xCoordinate);
    void setYCoordinate(int YCoordinate);
    void setTarget(Unit unit);
    void setDead();
}
