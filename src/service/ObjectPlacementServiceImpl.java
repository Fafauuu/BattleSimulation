package service;

import model.BattleField;
import model.objects.SimulationObject;
import model.objects.StaticSimulationObject;
import model.objects.units.Unit;

import java.util.List;

public class ObjectPlacementServiceImpl implements ObjectPlacementService {
    private final BattleField battleField;

    public ObjectPlacementServiceImpl(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public void placeAllUnits(List<Unit> units) {
        for (Unit unit : units) {
            placeUnit(unit);
        }
    }

    @Override
    public void placeUnit(Unit unit) {
        int XCoordinate = unit.getXCoordinate();
        int YCoordinate = unit.getYCoordinate();

        battleField.setSingleFieldWithUnit(XCoordinate, YCoordinate, unit);
    }

    @Override
    public void removeObjectAndReplaceWithStaticObject(SimulationObject simulationObject) {
        int XCoordinate = simulationObject.getXCoordinate();
        int YCoordinate = simulationObject.getYCoordinate();

        StaticSimulationObject staticSimulationObject
                = battleField.getStaticSimulationObjectWithXAndY(XCoordinate, YCoordinate);

        placeStaticObject(staticSimulationObject);
    }

    @Override
    public void removeObjectAndReplaceWithStaticObject(int XCoordinate, int YCoordinate) {
        StaticSimulationObject staticSimulationObject
                = battleField.getStaticSimulationObjectWithXAndY(XCoordinate, YCoordinate);

        placeStaticObject(staticSimulationObject);
    }

    @Override
    public void placeStaticObject(StaticSimulationObject staticSimulationObject) {
        int XCoordinate = staticSimulationObject.getXCoordinate();
        int YCoordinate = staticSimulationObject.getYCoordinate();

        battleField.setSingleFieldWithStaticObject(XCoordinate, YCoordinate, staticSimulationObject);
    }
}
