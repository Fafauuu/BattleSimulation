import model.BattleField;
import service.Engine;
import service.UnitDatabaseImpl;
import service.UnitFactory;
import service.UnitFactoryImpl;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(10);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        UnitFactory unitFactory = new UnitFactoryImpl(unitDatabase);
        new Engine(battleField, unitDatabase, unitFactory);
    }
}
