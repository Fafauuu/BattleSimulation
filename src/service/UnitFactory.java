package service;

import model.Knight;
import model.Side;

public interface UnitFactory {
    void createKnightsFormation(Side side, int startingX, int finalX, int startingY, int finalY);
    void createKnight(Side side, int XCoordinate, int YCoordinate);
}
