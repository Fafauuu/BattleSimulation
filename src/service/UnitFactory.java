package service;

import model.Side;

public interface UnitFactory {
    void createKnightsFormation(Side side, int startingX, int finalX, int startingY, int finalY);
    void createKnight(Side side, int XCoordinate, int YCoordinate);

    void createAxemanFormation(Side side, int startingX, int finalX, int startingY, int finalY);

    void createAxeman(Side side, int XCoordinate, int YCoordinate);
}
