package service;

import model.objects.units.Unit;

import java.util.List;

public interface UnitDatabase {
    List<Unit> getAllUnits();
    List<Unit> getBlueUnits();
    List<Unit> getRedUnits();

    void addBlueUnit(Unit unit);
    void addRedUnit(Unit unit);
    void removeAllUnits();
    void removeUnit(Unit unit);
}
