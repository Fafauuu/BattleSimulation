package service;

import model.SimulationObject;
import model.StaticSimulationObject;
import model.Unit;

public interface ObjectPlacementService {
    void placeUnit(Unit unit);
    void removeObjectAndReplaceWithStaticObject(SimulationObject simulationObject);
    void removeObjectAndReplaceWithStaticObject(int XCoordinate, int YCoordinate);
    void placeStaticObject(StaticSimulationObject staticSimulationObject);

}
