package service;

import model.Knight;
import model.Side;
import model.UnitDatabase;

public class SimulationObjectFactoryImpl implements SimulationObjectFactory{
    private final UnitDatabase unitDatabase;

    public SimulationObjectFactoryImpl(UnitDatabase unitDatabase) {
        this.unitDatabase = unitDatabase;
    }

    @Override
    public Knight createKnight(Side side, int XCoordinate, int YCoordinate) {
        Knight knight = new Knight(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE){
            unitDatabase.addBlueUnit(knight);
        }
        if (side == Side.RED){
            unitDatabase.addRedUnit(knight);
        }
        return knight;
    }
}
