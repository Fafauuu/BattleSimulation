package service;

import model.Side;
import model.objects.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitDatabaseImpl implements UnitDatabase {
    private final List<Unit> allUnits;
    private final List<Unit> blueUnits;
    private final List<Unit> redUnits;

    public UnitDatabaseImpl() {
        this.allUnits = new ArrayList<>();
        this.blueUnits = new ArrayList<>();
        this.redUnits = new ArrayList<>();
    }

    @Override
    public List<Unit> getAllUnits() {
        return allUnits;
    }

    @Override
    public List<Unit> getBlueUnits() {
        return blueUnits;
    }

    @Override
    public List<Unit> getRedUnits() {
        return redUnits;
    }

    @Override
    public void addBlueUnit(Unit unit) {
        blueUnits.add(unit);
        allUnits.add(unit);
    }

    @Override
    public void addRedUnit(Unit unit) {
        redUnits.add(unit);
        allUnits.add(unit);
    }

    @Override
    public void removeUnit(Unit unit) {
        if (unit.getSide() == Side.BLUE) {
            blueUnits.remove(unit);
        } else {
            redUnits.remove(unit);
        }
        allUnits.remove(unit);
    }
}
