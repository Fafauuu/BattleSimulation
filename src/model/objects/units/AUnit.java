package model.objects.units;

import gui.labels.UnitLabel;
import model.Side;
import model.Statistics;

import java.awt.*;

public abstract class AUnit implements Unit {
    protected Side side;
    protected int XCoordinate;
    protected int YCoordinate;
    protected Unit target;
    protected boolean alive;
    protected Statistics statistics;
    protected UnitLabel label;

    protected Color setBackgroundColor(Side side) {
        Color backgroundColor;
        if (side == Side.RED) {
            backgroundColor = new Color(0xFF7C7C);
        } else {
            backgroundColor = new Color(0xA0C3EA);
        }
        return backgroundColor;
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
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public UnitLabel getUnitLabel() {
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

    public void setDead() {
        this.alive = false;
        this.label.setVisible(false);
    }

}
