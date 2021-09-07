package model.objects;

import gui.labels.ObjectLabel;

import java.awt.*;

public class Tree implements StaticSimulationObject {
    private final int XCoordinate;
    private final int YCoordinate;
    private final ObjectLabel label;

    public Tree(int XCoordinate, int YCoordinate) {
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.label = new ObjectLabel(this, "src/icons/tree.png", Color.GREEN);
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
        return "Tree[" + XCoordinate + "," + YCoordinate + "] ";
    }
}

