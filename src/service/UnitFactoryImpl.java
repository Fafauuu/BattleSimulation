package service;

import model.objects.units.*;
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

    @Override
    public void createCavalryFormation(Side side, int startingX, int finalX, int startingY, int finalY) {
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
                createCavalry(side, x, y);
            }
        }
    }

    @Override
    public void createCavalry(Side side, int XCoordinate, int YCoordinate) {
        Cavalry cavalry = new Cavalry(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE) {
            unitDatabase.addBlueUnit(cavalry);
        }
        if (side == Side.RED) {
            unitDatabase.addRedUnit(cavalry);
        }
    }

    @Override
    public void createArcherFormation(Side side, int startingX, int finalX, int startingY, int finalY) {
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
                createArcher(side, x, y);
            }
        }
    }

    @Override
    public void createArcher(Side side, int XCoordinate, int YCoordinate) {
        Archer archer = new Archer(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE) {
            unitDatabase.addBlueUnit(archer);
        }
        if (side == Side.RED) {
            unitDatabase.addRedUnit(archer);
        }
    }

    @Override
    public void createMageFormation(Side side, int startingX, int finalX, int startingY, int finalY) {
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
                createMage(side, x, y);
            }
        }
    }

    @Override
    public void createMage(Side side, int XCoordinate, int YCoordinate) {
        Mage mage = new Mage(side, XCoordinate, YCoordinate);
        if (side == Side.BLUE) {
            unitDatabase.addBlueUnit(mage);
        }
        if (side == Side.RED) {
            unitDatabase.addRedUnit(mage);
        }
    }
}
