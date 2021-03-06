package model;

import exceptions.CantStackObjectsException;
import exceptions.NoSuchObjectException;
import exceptions.ObjectOutOfFieldException;
import model.objects.*;
import model.objects.units.Unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BattleField {
    private final int fieldSize;
    private final List<StaticSimulationObject> staticSimulationObjects;
    private final List<List<SimulationObject>> battleField;

    public BattleField(int fieldSize) {
        this.fieldSize = fieldSize;
        this.staticSimulationObjects = new LinkedList<>();
        this.battleField = new ArrayList<>(fieldSize);
        for (int i = 0; i < fieldSize; i++) {
            this.battleField.add(i, new ArrayList<>(fieldSize));
        }
        coverFieldWithGrass(fieldSize);
    }

    private void coverFieldWithGrass(int fieldSize) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Grass grass = new Grass(i, j);
                battleField.get(i).add(j, grass);
                staticSimulationObjects.add(grass);
            }
        }
    }

    public void plantTree(int xCoordinate, int yCoordinate){
        Tree tree = new Tree(xCoordinate,yCoordinate);
        if (xCoordinate >= fieldSize || xCoordinate < 0 || yCoordinate >= fieldSize || yCoordinate < 0){
            throw new ObjectOutOfFieldException(tree + "placed out of field");
        }
        if (!(battleField.get(xCoordinate).get(yCoordinate) instanceof Passable)){
            throw new CantStackObjectsException(tree + "tried to stack");
        }
        StaticSimulationObject previousObject = (StaticSimulationObject) battleField.get(xCoordinate).get(yCoordinate);
        int index = staticSimulationObjects.indexOf(previousObject);
        staticSimulationObjects.set(index,tree);
        battleField.get(xCoordinate).set(yCoordinate, tree);
    }

    public StaticSimulationObject getStaticSimulationObjectWithXAndY(int x, int y){
        StaticSimulationObject staticSimulationObjectToReturn = null;
        for (StaticSimulationObject staticSimulationObject : staticSimulationObjects) {
            if (staticSimulationObject.getXCoordinate() == x && staticSimulationObject.getYCoordinate() == y){
                staticSimulationObjectToReturn = staticSimulationObject;
            }
        }
        if (staticSimulationObjectToReturn == null){
            throw new NoSuchObjectException("No such static simulation object");
        }
        return staticSimulationObjectToReturn;
    }

    public void setSingleFieldWithStaticObject(int x, int y, StaticSimulationObject staticSimulationObject){
        if (x < fieldSize && y < fieldSize) {
            battleField.get(x).set(y, staticSimulationObject);
        }
        else throw new ObjectOutOfFieldException(
                "Static object: " + staticSimulationObject.toString() + "placed out of field");
    }

    public void setSingleFieldWithUnit(int x, int y, Unit unit){
        if (x < fieldSize &&  x >= 0 && y < fieldSize && y >= 0){
            if (battleField.get(x).get(y) instanceof Passable) {
                battleField.get(x).set(y, unit);
            }
            else throw new CantStackObjectsException(unit.toString() + "tried to stack");
        }
        else throw new ObjectOutOfFieldException(unit.toString() + "placed out of field");
    }

    public List<StaticSimulationObject> getStaticSimulationObjects() {
        return staticSimulationObjects;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public SimulationObject getSingleField(int x, int y){
        return battleField.get(x).get(y);
    }
}
