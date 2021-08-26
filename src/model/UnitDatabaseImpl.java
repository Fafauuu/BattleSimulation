package model;

import java.util.HashSet;
import java.util.Set;

public class UnitDatabaseImpl implements UnitDatabase {
    private final Set<Unit> allUnits;
    private final Set<Unit> blueUnits;
    private final Set<Unit> redUnits;

    public UnitDatabaseImpl() {
        this.allUnits = new HashSet<>();
        this.blueUnits = new HashSet<>();
        this.redUnits = new HashSet<>();
    }

    @Override
    public Set<Unit> getAllUnits() {
        return allUnits;
    }

    @Override
    public Set<Unit> getBlueUnits() {
        return blueUnits;
    }

    @Override
    public Set<Unit> getRedUnits() {
        return redUnits;
    }

    @Override
    public void addBlueUnit(Unit unit){
        blueUnits.add(unit);
        allUnits.add(unit);
    }

    @Override
    public void addRedUnit(Unit unit){
        redUnits.add(unit);
        allUnits.add(unit);
    }

    @Override
    public void removeUnit(Unit unit) {
        if (unit.getSide() == Side.BLUE){
            blueUnits.remove(unit);
        }
        else{
            redUnits.remove(unit);
        }
        allUnits.remove(unit);
    }
}
