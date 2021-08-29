package service;

import model.SimulationObject;
import model.StaticSimulationObject;
import model.Unit;

import java.util.List;

public interface ObjectPlacementService {
    void placeAllUnits(List<Unit> units);
    void placeUnit(Unit unit);
    void removeObjectAndReplaceWithStaticObject(SimulationObject simulationObject);
    void removeObjectAndReplaceWithStaticObject(int XCoordinate, int YCoordinate);
    void placeStaticObject(StaticSimulationObject staticSimulationObject);

}
