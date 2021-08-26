import model.BattleField;
import model.Knight;
import model.Side;
import model.UnitDatabaseImpl;
import service.*;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(5);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        SimulationObjectFactory simulationObjectFactory = new SimulationObjectFactoryImpl(unitDatabase);
        Engine engine = new Engine(battleField, objectPlacementService, unitDatabase, printingFieldService);


        Knight knight1 = simulationObjectFactory.createKnight(Side.BLUE, 0, 0);
        Knight knight2 = simulationObjectFactory.createKnight(Side.RED, 4, 4);
        objectPlacementService.placeUnit(knight1);
        objectPlacementService.placeUnit(knight2);
        printingFieldService.print();
        engine.simulateBattle();


    }
}
