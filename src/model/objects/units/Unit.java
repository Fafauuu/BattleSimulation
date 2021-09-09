package model.objects.units;

import animations.Animation;
import gui.labels.UnitLabel;
import model.Side;
import model.Statistics;
import model.objects.SimulationObject;
import service.Engine;

public interface Unit extends SimulationObject {
    Animation performBasicAttack(Engine engine);
    Animation performSpecialAttack(Engine engine);

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
