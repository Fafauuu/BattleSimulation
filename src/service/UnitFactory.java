package service;

import model.Side;

//methods used in CsvReader
@SuppressWarnings("unused")
public interface UnitFactory {

    void createKnight(Side side, int XCoordinate, int YCoordinate);

    void createAxeman(Side side, int XCoordinate, int YCoordinate);

    void createCavalry(Side side, int XCoordinate, int YCoordinate);

    void createArcher(Side side, int XCoordinate, int YCoordinate);

    void createMage(Side side, int XCoordinate, int YCoordinate);
}

