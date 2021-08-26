package service;

import model.BattleField;
import model.SimulationObject;
import model.StaticSimulationObject;
import model.Unit;

public class ObjectPlacementServiceImpl implements ObjectPlacementService {
    private final BattleField battleField;

    public ObjectPlacementServiceImpl(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public void placeUnit(Unit unit){
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
    public void placeStaticObject(StaticSimulationObject staticSimulationObject){
        int XCoordinate = staticSimulationObject.getXCoordinate();
        int YCoordinate = staticSimulationObject.getYCoordinate();

        battleField.setSingleFieldWithStaticObject(XCoordinate, YCoordinate, staticSimulationObject);
    }
}
