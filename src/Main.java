import gui.MainFrame;
import model.BattleField;
import model.UnitDatabaseImpl;
import service.*;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(5);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        SimulationObjectFactory simulationObjectFactory = new SimulationObjectFactoryImpl(unitDatabase);
        Engine engine = new Engine(objectPlacementService, unitDatabase, printingFieldService);
        MainFrame gui = new MainFrame(battleField);

        gui.setVisible(true);

//        Knight knight1 = simulationObjectFactory.createKnight(Side.BLUE, 2, 2);
//        Knight knight2 = simulationObjectFactory.createKnight(Side.RED, 4, 2);
//        objectPlacementService.placeUnit(knight1);
//        objectPlacementService.placeUnit(knight2);
//        printingFieldService.print();
//        engine.simulateBattle();

//        printingFieldService.print();
//        Knight knight1 = simulationObjectFactory.createKnight(Side.BLUE, 3, 2);
//        Knight knight2 = simulationObjectFactory.createKnight(Side.RED,2,2);
//        Knight knight3 = simulationObjectFactory.createKnight(Side.RED,1,2);
//        objectPlacementService.placeUnit(knight1);
//        objectPlacementService.placeUnit(knight2);
//        objectPlacementService.placeUnit(knight3);
//        printingFieldService.print();
//        engine.moveUp(knight1);
//        engine.moveUp(knight2);
//        engine.moveUp(knight3);
//        engine.simulateBattle(1);
//        printingFieldService.print();
//        engine.moveRight(knight1);
//        engine.moveRight(knight2);
//        engine.moveRight(knight3);
//        engine.simulateBattle(1);
//        printingFieldService.print();
//        objectPlacementService.removeObjectAndReplaceWithStaticObject(knight1);
//        engine.simulateBattle(1);
//        printingFieldService.print();
    }
}
