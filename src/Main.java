import gui.frames.MenuFrame;
import model.BattleField;
import service.*;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(10);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        UnitFactory unitFactory = new UnitFactoryImpl(unitDatabase);
        Engine engine = new Engine(battleField, objectPlacementService, unitDatabase, printingFieldService);
        new MenuFrame(engine,unitFactory,unitDatabase,objectPlacementService);
    }
}
