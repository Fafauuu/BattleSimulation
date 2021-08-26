package model;

public interface Unit extends SimulationObject{
    Side getSide();
    Unit getTarget();
    int getMaxHp();
    int getHp();
    int getAttack();
    int getRange();
    boolean isAlive();

    void setXCoordinate(int xCoordinate);
    void setYCoordinate(int YCoordinate);
    void setTarget(Unit unit);
    void setHp(int hp);
    void setDead();
}
