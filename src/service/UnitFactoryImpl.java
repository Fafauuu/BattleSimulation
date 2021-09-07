package service;

import model.objects.units.Axeman;
import model.objects.units.Knight;
import model.Side;

public class UnitFactoryImpl implements UnitFactory {
    private final UnitDatabase unitDatabase;

    public UnitFactoryImpl(UnitDatabase unitDatabase) {
        this.unitDatabase = unitDatabase;
    }

    @Override
    public void createKnightsFormation(Side side, int startingX, int finalX, int startingY, int finalY) {
        if (finalX < startingX) {
            int buff = startingX;
            startingX = finalX;
            finalX = buff;
        }

        if (finalY < startingY) {
            int buff = startingY;
            startingY = finalY;
            finalY = buff;
        }

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                    createKnight(side, x, y);
            }
        }
    }

    @Override
    public void createKnight(Side side, int XCoordinate, int YCoordinate) {
        Knight knight = new Knight(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE) {
            unitDatabase.addBlueUnit(knight);
        }
        if (side == Side.RED) {
            unitDatabase.addRedUnit(knight);
        }
    }

    @Override
    public void createAxemanFormation(Side side, int startingX, int finalX, int startingY, int finalY) {
        if (finalX < startingX) {
            int buff = startingX;
            startingX = finalX;
            finalX = buff;
        }

        if (finalY < startingY) {
            int buff = startingY;
            startingY = finalY;
            finalY = buff;
        }

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                createAxeman(side, x, y);
            }
        }
    }

    @Override
    public void createAxeman(Side side, int XCoordinate, int YCoordinate) {
        Axeman axeman = new Axeman(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE) {
            unitDatabase.addBlueUnit(axeman);
        }
        if (side == Side.RED) {
            unitDatabase.addRedUnit(axeman);
        }
    }
}
