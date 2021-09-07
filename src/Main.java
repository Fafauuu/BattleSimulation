import model.BattleField;
import model.Side;
import service.*;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField(10);
        UnitDatabaseImpl unitDatabase = new UnitDatabaseImpl();
        ObjectPlacementService objectPlacementService = new ObjectPlacementServiceImpl(battleField);
        PrintingFieldService printingFieldService = new PrintingFieldServiceConsoleImpl(battleField);
        UnitFactory unitFactory = new UnitFactoryImpl(unitDatabase);
        Engine engine = new Engine(battleField, objectPlacementService, unitDatabase, printingFieldService);

        battleField.plantTree(2,4);

        unitFactory.createKnight(Side.BLUE,0,0);
        unitFactory.createKnight(Side.RED,1,0);
        unitFactory.createKnight(Side.RED,0,1);

        unitFactory.createKnightsFormation(Side.BLUE, 4,4,0,2);
        unitFactory.createAxeman(Side.RED, 5, 1);

        objectPlacementService.placeAllUnits(unitDatabase.getAllUnits());

        engine.simulateBattle();

//        JFrame jFrame = new JFrame();
//        jFrame.setBounds(0,0,1000,1000);
//        jFrame.setVisible(true);
//        jFrame.setLayout(null);
//
//        JPanel jPanel1 = new JPanel();
//        jPanel1.setBackground(Color.RED);
//        jPanel1.setBounds(0,0,600,600);
//
//        JPanel jPanel2 = new JPanel();
//        jPanel2.setBounds(100,100,400,400);
//        jPanel2.setBackground(Color.BLUE);
//        jPanel2.setOpaque(false);
//
//
//        ObjectLabel objectLabel = new ObjectLabel(battleField.getStaticSimulationObjectWithXAndY(1,1), "src/icons/grass.png", Color.GREEN);
//        jPanel2.add(objectLabel);
//
////        JLabel jLabel = new JLabel();
////        jLabel.setIcon(new ImageIcon("src/icons/knight.png"));
////        jLabel.setBounds(0,0,50,50);
////        jPanel2.add(jLabel);
////        jLabel.setVisible(true);
//
//        jFrame.add(jPanel2);
//        jFrame.add(jPanel1);

    }
}
