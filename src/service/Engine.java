package service;

import exceptions.CantMoveObjectException;
import exceptions.CantStackObjectsException;
import exceptions.ObjectOutOfFieldException;
import gui.MainFrame;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Engine {

    private final ActionHandler actionHandler;
    private final BattleField battleField;
    private final ObjectPlacementService objectPlacementService;
    private final UnitDatabase unitDatabase;
    private final PrintingFieldService printingFieldService;

    public Engine(BattleField battleField,
                  ObjectPlacementService unitPlacementService,
                  UnitDatabase unitDatabase,
                  PrintingFieldService printingFieldService
    ) {
        this.actionHandler = new ActionHandler(this);
        this.battleField = battleField;
        this.objectPlacementService = unitPlacementService;
        this.unitDatabase = unitDatabase;
        this.printingFieldService = printingFieldService;
    }

    public void simulateBattle() {
        printingFieldService.print();
        MainFrame gui = new MainFrame(battleField, unitDatabase);
        gui.setVisible(true);
        pauseSimulation(1000);
        for (int turn = 0; !simulationStopCondition(); turn++) {
            List<Unit> units = SetUnitsTurn(turn);
            setTargets(units);
            setAllRequests(units);
            actionHandler.simulateTurn();
            printingFieldService.print();
            gui.repaint();
            pauseSimulation(1000);
        }
    }

    private List<Unit> SetUnitsTurn(int turn) {
        if (turn % 2 == 0){
            return unitDatabase.getBlueUnits();
        }
        else{
            return unitDatabase.getRedUnits();
        }
    }

    private void pauseSimulation(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean simulationStopCondition() {
        return unitDatabase.getBlueUnits().isEmpty() || unitDatabase.getRedUnits().isEmpty();
    }

    public void setTargets(List<Unit> units) {
        for (Unit unit : units) {
            unit.setTarget(findClosestEnemy(unit));
        }
    }

    private Unit findClosestEnemy(Unit unit) {
        List<Unit> listOfEnemies = setUnitsToScan(unit);
        Unit target = null;
        double minDistance = 0;
        double distance;

        for (Unit enemy : listOfEnemies) {
            if (minDistance == 0) {
                minDistance = countDistance(unit, enemy);
                target = enemy;
            } else {
                distance = countDistance(unit, enemy);
                if (distance < minDistance) {
                    minDistance = distance;
                    target = enemy;
                }
            }
        }
        return target;
    }

    private void setAllRequests(List<Unit> units) {
        for (Unit unit : units) {
            setActionRequest(unit);
        }
    }

    private void setActionRequest(Unit unit) {
        double distance = countDistance(unit, unit.getTarget());
        if (distance > unit.getRange()) {
            actionHandler.setRequest(unit, setFirstDirection(unit), setSecondDirection(unit));
        } else {
            actionHandler.setRequest(unit, Actions.ATTACK);
        }
    }

    private Actions setFirstDirection(Unit unit) {
        return setFirstAndSecondDirection(unit).get(0);
    }

    private Actions setSecondDirection(Unit unit) {
        return setFirstAndSecondDirection(unit).get(1);
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


        if (XDifference <= 0 && YDifference > 0) {
            if (XDifferenceGreaterThatY) {
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_RIGHT;
            } else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        if (XDifference > 0 && YDifference >= 0) {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_RIGHT;
            } else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        if (XDifference >= 0 && YDifference < 0) {
            if (XDifferenceGreaterThatY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        if (XDifference < 0 && YDifference <= 0) {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        if (firstDirection != null) {
            return new ArrayList<>(List.of(firstDirection, secondDirection));
        } else throw new RuntimeException("Null pointer in setting first and second direction");
    }

    private List<Unit> setUnitsToScan(Unit unit) {
        List<Unit> listOfEnemies;
        if (unit.getSide() == Side.BLUE) {
            listOfEnemies = unitDatabase.getRedUnits();
        } else listOfEnemies = unitDatabase.getBlueUnits();
        return listOfEnemies;
    }

    private double countDistance(Unit unit, Unit enemy) {
        int unitXCoordinate = unit.getXCoordinate();
        int unitYCoordinate = unit.getYCoordinate();
        int enemyXCoordinate = enemy.getXCoordinate();
        int enemyYCoordinate = enemy.getYCoordinate();

        return Math.sqrt(
                Math.pow(unitXCoordinate - enemyXCoordinate, 2) + Math.pow(unitYCoordinate - enemyYCoordinate, 2));
    }


    public void move(Unit unit, int XPositionModifier, int YPositionModifier) throws CantMoveObjectException {
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

        if (target.getHp() <= 0) {
            objectPlacementService.removeObjectAndReplaceWithStaticObject(target);
            unitDatabase.removeUnit(target);
            target.setDead();
        }
    }
}
