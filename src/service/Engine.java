package service;

import exceptions.CantMoveObjectException;
import exceptions.CantStackObjectsException;
import exceptions.ObjectOutOfFieldException;
import gui.MainFrame;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private final ActionHandler actionHandler;
    private final BattleField battleField;
    private final ObjectPlacementService objectPlacementService;
    private final UnitDatabase unitDatabase;
    private final PrintingFieldService printingFieldService;
    private MainFrame gui;
    private final Timer timer = new Timer();

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
        gui = new MainFrame(battleField, unitDatabase);
        gui.setVisible(true);
        pauseSimulation(1000);
        for (int turn = 0; !simulationStopCondition(); turn++) {
            List<Unit> units = SetUnitsTurn(turn);
            setTargets(units);
            setRequests(units);
            actionHandler.simulateTurn();
            printingFieldService.print();
            pauseSimulation(1500);
        }
        printingFieldService.print();
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

    private List<Unit> SetUnitsTurn(int turn) {
        if (turn % 2 == 0) {
            return unitDatabase.getBlueUnits();
        } else {
            return unitDatabase.getRedUnits();
        }
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

    private void setRequests(List<Unit> units) {
        for (Unit unit : units) {
            setActionRequest(unit);
        }
    }

    private void setActionRequest(Unit unit) {
        if (TargetIsInRange(unit)) {
            actionHandler.setRequest(unit, setFirstDirection(unit), setSecondDirection(unit));
        } else {
            actionHandler.setRequest(unit, Actions.ATTACK);
        }
    }

    private boolean TargetIsInRange(Unit unit){
        double distance = countDistance(unit, unit.getTarget());
        return distance > unit.getStatistics().getRange();
    }

    private Actions setFirstDirection(Unit unit) {
        return setFirstAndSecondDirection(unit).get(0);
    }

    private Actions setSecondDirection(Unit unit) {
        return setFirstAndSecondDirection(unit).get(1);
    }

    private List<Actions> setFirstAndSecondDirection(Unit unit) {
        Actions firstDirection;
        Actions secondDirection;

        int XDifference = checkXDifference(unit, unit.getTarget());
        int YDifference = checkYDifference(unit, unit.getTarget());

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
        else if (XDifference > 0 && YDifference >= 0) {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_RIGHT;
            } else {
                firstDirection = Actions.MOVE_RIGHT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        else if (XDifference >= 0 && YDifference < 0) {
            if (XDifferenceGreaterThatY) {
                firstDirection = Actions.MOVE_DOWN;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_DOWN;
            }
        }
        else {
            if (XDifferenceGreaterOrEqualToY) {
                firstDirection = Actions.MOVE_UP;
                secondDirection = Actions.MOVE_LEFT;
            } else {
                firstDirection = Actions.MOVE_LEFT;
                secondDirection = Actions.MOVE_UP;
            }
        }
        return new ArrayList<>(List.of(firstDirection, secondDirection));
    }

    private int checkXDifference(Unit unit, Unit target) {
        int unitXCoordinate = unit.getXCoordinate();
        int targetXCoordinate = target.getXCoordinate();

        return targetXCoordinate - unitXCoordinate;
    }

    private int checkYDifference(Unit unit, Unit target) {
        int unitYCoordinate = unit.getYCoordinate();
        int targetYCoordinate = target.getYCoordinate();

        return  targetYCoordinate - unitYCoordinate;
    }


    public void move(Unit unit, int XPositionModifier, int YPositionModifier) throws CantMoveObjectException {
        int previousXCoordinate = unit.getXCoordinate();
        int previousYCoordinate = unit.getYCoordinate();

        unit.setXCoordinate(unit.getXCoordinate() + XPositionModifier);
        unit.setYCoordinate(unit.getYCoordinate() + YPositionModifier);

        try {
            objectPlacementService.placeUnit(unit);
            gui.getMainPanel().repaintUnit(unit);
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

        if (requester.getStatistics().getMana() >= requester.getStatistics().getMaxMana()) {
            requester.performSpecialAttack(this, unitDatabase);
        } else {
            requester.performBasicAttack(this, unitDatabase);
        }

        removeUnitIfDead(target);

        target.getUnitLabel().getHpBarLabel().updateHpBar();
        requester.getUnitLabel().getManaBarLabel().updateManaBar();
    }

    private void removeUnitIfDead(Unit target) {
        if (target.getStatistics().getHp() <= 0) {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    objectPlacementService.removeObjectAndReplaceWithStaticObject(target);
                    unitDatabase.removeUnit(target);
                    target.setDead();
                    gui.getMainPanel().removeUnitLabel(target);
                }
            };
            timer.schedule(task, 700);
        }
    }

    public int calculateBasicAttackDamage(Unit attacker, Unit defender, AttackTypes attackType) {
        int attackDamageNegated
                = (int) Math.ceil(attacker.getStatistics().getBasicAttack().getBaseDamage()
                * calculateDamageNegationPercentage(defender, attackType));
        return attacker.getStatistics().getBasicAttack().getBaseDamage() - attackDamageNegated;
    }

    public int calculateSpecialAttackDamage(Unit attacker, Unit defender, AttackTypes attackType) {
        int attackDamageNegated
                = (int) Math.ceil(attacker.getStatistics().getSpecialAttack().getBaseDamage()
                * calculateDamageNegationPercentage(defender, attackType));
        return attacker.getStatistics().getSpecialAttack().getBaseDamage() - attackDamageNegated;
    }

    public double calculateDamageNegationPercentage(Unit defender, AttackTypes attackType) {
        int defensiveStatisticValue;

        if (attackType == AttackTypes.PHYSICAL) {
            defensiveStatisticValue = defender.getStatistics().getArmour();
        } else if (attackType == AttackTypes.MAGICAL) {
            defensiveStatisticValue = defender.getStatistics().getMagicResist();
        } else defensiveStatisticValue = 0;

        return (double) defensiveStatisticValue / (defensiveStatisticValue + 100);
    }

    public Timer getTimer() {
        return timer;
    }
}
