package model;

import java.util.Set;

public interface UnitDatabase {

    Set<Unit> getAllUnits();

    Set<Unit> getBlueUnits();

    Set<Unit> getRedUnits();

    void addBlueUnit(Unit unit);

    void addRedUnit(Unit unit);

    void removeUnit(Unit unit);
}
