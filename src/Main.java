import model.Actions;
import model.BattleField;
import model.Side;
import service.*;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(10);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        UnitFactory unitFactory = new UnitFactoryImpl(unitDatabase);
        Engine engine = new Engine(battleField, objectPlacementService, unitDatabase, printingFieldService);
        CsvSaveReader csvSaveReader = new CsvSaveReader(unitFactory);



//        battleField.plantTree(6,3);
//        battleField.plantTree(2,4);
//        battleField.plantTree(2,1);
//        battleField.plantTree(4,6);
//        battleField.plantTree(7,9);
//        battleField.plantTree(7,8);
//        battleField.plantTree(7,7);
//
//        unitFactory.createKnightsFormation(Side.BLUE, 1,1,2,7);
//        unitFactory.createArcherFormation(Side.BLUE,0,0,3,5);
//        unitFactory.createAxeman(Side.BLUE,1,0);
//        unitFactory.createAxeman(Side.BLUE,2,0);
//        unitFactory.createCavalry(Side.BLUE,1,8);
//        unitFactory.createCavalry(Side.BLUE,1,9);
//        unitFactory.createMage(Side.BLUE,0,6);
//        unitFactory.createMage(Side.BLUE,0,7);
//
//        unitFactory.createKnightsFormation(Side.RED, 8,8,0,2);
//        unitFactory.createKnightsFormation(Side.RED, 8,8,6,6);
//        unitFactory.createArcherFormation(Side.RED,9,9,6,8);
//        unitFactory.createCavalryFormation(Side.RED,8,8,3,5);
//        unitFactory.createAxemanFormation(Side.RED, 9,9,4,5);
//        unitFactory.createMageFormation(Side.RED,9,9,2,3);
//
        objectPlacementService.placeAllUnits(unitDatabase.getAllUnits());

        engine.simulateBattle();
    }
}
