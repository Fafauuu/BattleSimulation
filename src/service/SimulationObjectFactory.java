package service;

import model.Knight;
import model.Side;

public interface SimulationObjectFactory {
    Knight createKnight(Side side, int XCoordinate, int YCoordinate);
}
