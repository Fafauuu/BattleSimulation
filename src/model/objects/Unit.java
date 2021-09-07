package model.objects;

import animations.Animation;
import gui.labels.UnitLabel;
import model.Side;
import model.Statistics;
import service.Engine;
import service.UnitDatabase;

public interface Unit extends SimulationObject{
    Animation performBasicAttack(Engine engine, UnitDatabase unitDatabase);
    Animation performSpecialAttack(Engine engine, UnitDatabase unitDatabase);

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
