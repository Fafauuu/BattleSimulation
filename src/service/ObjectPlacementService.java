package service;

import model.objects.SimulationObject;
import model.objects.StaticSimulationObject;
import model.objects.units.Unit;

import java.util.List;

public interface ObjectPlacementService {
    void placeAllUnits(List<Unit> units);
    void placeUnit(Unit unit);
    void removeObjectAndReplaceWithStaticObject(SimulationObject simulationObject);
    void removeObjectAndReplaceWithStaticObject(int XCoordinate, int YCoordinate);
    void placeStaticObject(StaticSimulationObject staticSimulationObject);

}
