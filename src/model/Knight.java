package model;

import gui.ObjectLabel;

public class Knight implements Unit{
    private final Side side;
    private int XCoordinate;
    private int YCoordinate;
    private Unit target;
    private int hp;
    private final int attack;
    private final int range;
    private boolean alive;
    private final ObjectLabel label;

    public Knight(Side side, int XCoordinate, int YCoordinate) {
        this.side = side;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.hp = 100;
        this.attack = 20;
        this.range = 1;
        this.alive = true;
        this.label = new ObjectLabel(this,"knight.png");
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public int getXCoordinate() {
        return XCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public Unit getTarget() {
        return target;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public ObjectLabel getLabel() {
        return label;
    }

    public void setXCoordinate(int XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public void setYCoordinate(int YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    @Override
    public void setTarget(Unit target) {
        this.target = target;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDead() {
        this.alive = false;
    }

    @Override
    public String toString() {
        return side.toString().charAt(0) + "_Knight[" + XCoordinate + "," + YCoordinate + "] ";
    }
}
