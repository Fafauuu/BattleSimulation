package service;

import exceptions.CantMoveObjectException;
import exceptions.CantStackObjectsException;
import exceptions.ObjectOutOfFieldException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Engine {

    private final ActionHandler actionHandler;
    private final ObjectPlacementService objectPlacementService;
    private final UnitDatabase unitDatabase;
    private final PrintingFieldService printingFieldService;

    public Engine(ObjectPlacementService unitPlacementService, UnitDatabase unitDatabase, PrintingFieldService printingFieldService) {
        this.actionHandler = new ActionHandler(this);
        this.objectPlacementService = unitPlacementService;
        this.unitDatabase = unitDatabase;
        this.printingFieldService = printingFieldService;
    }

    public void simulateBattle() {
        printingFieldService.print();
        while (!simulationStopCondition()) {
            setTargets();
            setAllRequests();
            actionHandler.simulateTurn();
            printingFieldService.print();
        }
    }

    private boolean simulationStopCondition() {
        return unitDatabase.getBlueUnits().isEmpty() || unitDatabase.getRedUnits().isEmpty();
    }

    private void setAllRequests() {
        for (Unit unit : unitDatabase.getAllUnits()) {
            actionHandler.setRequest(unit, setActionRequest(unit));
        }
    }

    private Actions setActionRequest(Unit unit) {
        Actions action;
        double distance = countDistance(unit, unit.getTarget());
        if (distance > unit.getRange()){
            action = setFirstDirection(unit);
        }
        else{
            action = Actions.ATTACK;
        }
        return action;
    }

    private Actions setFirstDirection(Unit unit) {
        return setFirstAndSecondDirection(unit).get(0);
    }

    private void setRequestWithFirstDirection(Unit unit){
        actionHandler.setRequest(unit, setFirstAndSecondDirection(unit).get(0));
    }

    private void setRequestWithSecondDirection(Unit unit){
        actionHandler.setRequest(unit, setFirstAndSecondDirection(unit).get(1));
    }

    private List<Actions> setFirstAndSecondDirection(Unit unit) {
        Actions firstDirection = null;
        Actions secondDirection = null;
        int unitXCoordinate = unit.getXCoordinate();
        int unitYCoordinate = unit.getYCoordinate();
        int targetXCoordinate = unit.getTarget().getXCoordinate();
        int targetYCoordinate = unit.getTarget().getYCoordinate();
        int XDifference = targetXCoordinate - unitXCoordinate;
        int YDifference = targetYCoordinate - unitYCoordinate;

        boolean XDifferenceGreaterThatY = Math.abs(XDifference) > Math.abs(YDifference);
        boolean XDifferenceGreaterOrEqualToY = Math.abs(XDifference) >= Math.abs(YDifference);


        if (XDifference <= 0 && YDifference > 0){
            if (XDifferenceGreaterThatY){
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_RIGHT;
            }
            else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        if (XDifference > 0 && YDifference >= 0){
            if (XDifferenceGreaterOrEqualToY){
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_RIGHT;
            }
            else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        if (XDifference >= 0 && YDifference < 0){
            if (XDifferenceGreaterThatY){
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_LEFT;
            }
            else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        if (XDifference < 0 && YDifference <= 0){
            if (XDifferenceGreaterOrEqualToY){
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_LEFT;
            }
            else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        if (firstDirection != null) {
            return new ArrayList<>(List.of(firstDirection, secondDirection));
        }
        else throw new RuntimeException("Null pointer in setting first and second direction");
    }

    public void setTargets(){
        for (Unit unit : unitDatabase.getAllUnits()) {
            unit.setTarget(findClosestEnemy(unit));
        }
    }

    private Unit findClosestEnemy(Unit unit) {
        Set<Unit> setOfEnemies = setUnitsToScan(unit);
        Unit target = null;
        double minDistance = 0;
        double distance;

        for (Unit enemy : setOfEnemies) {
            if (minDistance == 0){
                minDistance = countDistance(unit, enemy);
                target = enemy;
            }
            else{
                distance = countDistance(unit, enemy);
                if (distance < minDistance){
                    minDistance = distance;
                    target = enemy;
                }
            }
        }
        return target;
    }

    private Set<Unit> setUnitsToScan(Unit unit) {
        Set<Unit> setOfEnemies;
        if (unit.getSide() == Side.BLUE){
            setOfEnemies = unitDatabase.getRedUnits();
        }
        else setOfEnemies = unitDatabase.getBlueUnits();
        return setOfEnemies;
    }

    private double countDistance(Unit unit, Unit enemy) {
        int unitXCoordinate = unit.getXCoordinate();
        int unitYCoordinate = unit.getYCoordinate();
        int enemyXCoordinate = enemy.getXCoordinate();
        int enemyYCoordinate = enemy.getYCoordinate();

        return Math.sqrt(
                Math.pow(unitXCoordinate - enemyXCoordinate, 2) + Math.pow(unitYCoordinate - enemyYCoordinate, 2));
    }


    public void move(Unit unit, int XPositionModifier, int YPositionModifier) throws CantMoveObjectException{
        int previousXCoordinate = unit.getXCoordinate();
        int previousYCoordinate = unit.getYCoordinate();

        unit.setXCoordinate(unit.getXCoordinate() + XPositionModifier);
        unit.setYCoordinate(unit.getYCoordinate() + YPositionModifier);

        try {
            objectPlacementService.placeUnit(unit);
        } catch (ObjectOutOfFieldException e) {
            unit.setXCoordinate(unit.getXCoordinate() - XPositionModifier);
            unit.setYCoordinate(unit.getYCoordinate() - YPositionModifier);
            throw new CantMoveObjectException("Cant move: " + unit + ", unit moved out of field");
        } catch (CantStackObjectsException e) {
            unit.setXCoordinate(unit.getXCoordinate() - XPositionModifier);
            unit.setYCoordinate(unit.getYCoordinate() - YPositionModifier);
            throw new CantMoveObjectException("Cant move: " + unit + ", unit tried to stack objects");
        }

        objectPlacementService.removeObjectAndReplaceWithStaticObject(previousXCoordinate, previousYCoordinate);
    }

    public void attack(Unit requester) {
        Unit target = requester.getTarget();

        target.setHp(target.getHp() - requester.getAttack());

        if (target.getHp() <= 0){
            objectPlacementService.removeObjectAndReplaceWithStaticObject(target);
            unitDatabase.removeUnit(target);
            target.setDead();
        }
    }
}
