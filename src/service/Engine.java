package service;

import animations.Animation;
import exceptions.CantMoveObjectException;
import exceptions.CantStackObjectsException;
import exceptions.ObjectOutOfFieldException;
import gui.frames.BattlefieldFrame;
import gui.frames.EndScreenFrame;
import gui.frames.MainMenuFrame;
import gui.frames.SimulationControlFrame;
import gui.panels.DamageLabel;
import model.Actions;
import model.AttackTypes;
import model.BattleField;
import model.Side;
import model.objects.units.Unit;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {
    private final ActionHandler actionHandler;
    private BattleField battleField;
    private ObjectPlacementService objectPlacementService;
    private final UnitDatabase unitDatabase;
    private final UnitFactory unitFactory;
    private PrintingFieldService printingFieldService;
    private final DamageCalculationService damageCalculationService;
    private final PathFindingService pathFindingService;
    private BattlefieldFrame gui;
    private SimulationControlFrame simulationControlFrame;
    private final Timer timer = new Timer();
    private boolean stopSimulation = false;

    public Engine(BattleField battleField,
                  UnitDatabase unitDatabase,
                  UnitFactory unitFactory) {
        this.actionHandler = new ActionHandler(this);
        this.battleField = battleField;
        this.objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        this.unitDatabase = unitDatabase;
        this.unitFactory = unitFactory;
        this.printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        this.damageCalculationService = new DamageCalculationServiceImpl();
        this.pathFindingService = new PathFindingService();

        createNewMainMenuFrame();
    }

    //pause for at least 800 to simulate properly

    public void simulateBattle() {
        printingFieldService.print();
        gui = new BattlefieldFrame(battleField, unitDatabase);
        gui.setVisible(true);
        pauseSimulation(1300);
        for (int turn = 0; !simulationStopCondition(); turn++) {
            while (stopSimulation){
                pauseSimulation(100);
            }
            List<Unit> units = setUnitsTurn(turn);
            setTargets(units);
            setRequests(units);
            actionHandler.simulateTurn();
            printingFieldService.print();
            gui.getAnimationPanel().getTimer().start();
            pauseSimulation(1300);
            gui.getAnimationPanel().getTimer().stop();
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

    public void stopSimulation(){
        stopSimulation = true;
    }

    public void resumeSimulation(){
        stopSimulation = false;
    }

    private boolean simulationStopCondition() {
        return unitDatabase.getBlueUnits().isEmpty() || unitDatabase.getRedUnits().isEmpty();
    }

    private List<Unit> setUnitsTurn(int turn) {
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
        List<Unit> listOfEnemies = setListOfEnemies(unit);
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

    public List<Unit> setListOfEnemies(Unit unit) {
        List<Unit> listOfEnemies;
        if (unit.getSide() == Side.BLUE) {
            listOfEnemies = unitDatabase.getRedUnits();
        } else listOfEnemies = unitDatabase.getBlueUnits();
        return listOfEnemies;
    }

    public double countDistance(Unit unit, Unit enemy) {
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
        if (targetIsInRange(unit)) {
            actionHandler.setRequest(unit, Actions.ATTACK);
        } else {
            List<Actions> actions = pathFindingService.setFirstAndSecondDirection(unit);
            actionHandler.setRequest(unit, actions.get(0), actions.get(1));
        }
    }

    private boolean targetIsInRange(Unit unit) {
        double distance = countDistance(unit, unit.getTarget());

        if (unit.getStatistics().getMana() < unit.getStatistics().getMaxMana()) {
            return distance <= unit.getStatistics().getBasicAttack().getRange();
        } else {
            return distance <= unit.getStatistics().getSpecialAttack().getRange();
        }
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
        Animation animation = null;

        if (requester.getStatistics().getMana() >= requester.getStatistics().getMaxMana()) {
            try {
                animation = requester.performSpecialAttack(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                animation = requester.performBasicAttack(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gui.getAnimationPanel().addAnimation(animation);
    }

    public void addDamageLabelToTarget(Unit target, AttackTypes attackType, int damage) {
        DamageLabel damageLabel = new DamageLabel(target, attackType, damage);

        target.getUnitLabel().addDamageLabel(damageLabel, timer);
    }

    public void updateFieldAfterAttack(Unit attacker, Unit target) {
        removeUnitIfDead(target);
        target.getUnitLabel().getHpBarLabel().updateHpBar();
        attacker.getUnitLabel().getManaBarLabel().updateManaBar();
    }

    public void removeUnitIfDead(Unit target) {
        if (target.getStatistics().getHp() <= 0) {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    target.setDead();
                    objectPlacementService.removeObjectAndReplaceWithStaticObject(target);
                    unitDatabase.removeUnit(target);
                    gui.getMainPanel().removeUnitLabel(target);
                }
            };
            timer.schedule(task, 700);
        }
    }

    public void restartSimulation(){
        battleField = new BattleField(10);
        unitDatabase.removeAllUnits();
        actionHandler.dropAllRequests();
        objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        gui.dispose();
    }

    public void createNewMainMenuFrame(){
        new MainMenuFrame(this);
    }

    public void createNewEndScreenFrame(){
        new EndScreenFrame(this);
    }

    public void createNewSimulationControlFrame(){
        simulationControlFrame = new SimulationControlFrame(this);
    }

    public void removeSimulationControlFrame(){
        simulationControlFrame.dispose();
    }

    public DamageCalculationService getDamageCalculationService() {
        return damageCalculationService;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    public UnitDatabase getUnitDatabase() {
        return unitDatabase;
    }

    public ObjectPlacementService getObjectPlacementService() {
        return objectPlacementService;
    }

    public PathFindingService getPathFindingService() {
        return pathFindingService;
    }
}
