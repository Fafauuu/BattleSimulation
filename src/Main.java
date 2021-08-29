import model.BattleField;
import model.Side;
import service.*;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(20);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        UnitFactory unitFactory = new UnitFactoryImpl(unitDatabase);
        Engine engine = new Engine(battleField, objectPlacementService, unitDatabase, printingFieldService);

        battleField.plantTree(1,1);
        battleField.plantTree(3,16);
        battleField.plantTree(4,12);
        battleField.plantTree(4,9);
        battleField.plantTree(3,5);
        battleField.plantTree(8,6);
        battleField.plantTree(13,2);
        battleField.plantTree(14,16);
        battleField.plantTree(16,8);

        unitFactory.createKnightsFormation(Side.BLUE,0,2,6,14);
        unitFactory.createKnightsFormation(Side.RED,17,19,4,12);
        objectPlacementService.placeAllUnits(unitDatabase.getAllUnits());

        engine.simulateBattle();
    }
}
