package model;

import gui.ObjectLabel;

public class Grass implements StaticSimulationObject, Passable, Coverable{
    private final int XCoordinate;
    private final int YCoordinate;
    private final ObjectLabel label;

    public Grass(int XCoordinate, int YCoordinate) {
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.label = new ObjectLabel(this,"src/icons/grass.png");
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
    public ObjectLabel getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Grass[" + XCoordinate + "," + YCoordinate + "] ";
    }
}
