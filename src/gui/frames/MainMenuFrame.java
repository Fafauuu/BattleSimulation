package gui.frames;

import gui.buttons.MenuButton;
import service.*;

import javax.swing.*;

public class MainMenuFrame extends AMenu {
    private final Engine engine;
    private final UnitFactory unitFactory;
    private final UnitDatabase unitDatabase;
    private final ObjectPlacementService objectPlacementService;
    private JButton loadGameButton;
    private JButton newGameButton;
    private JButton startGameButton;
    private JButton exitButton;

    public MainMenuFrame(Engine engine) {
        super(500,250,"Main menu");

        this.engine = engine;
        this.unitFactory = engine.getUnitFactory();
        this.unitDatabase = engine.getUnitDatabase();
        this.objectPlacementService = engine.getObjectPlacementService();

        setNewGameButton();
        setLoadSaveButton();
        setStartGameButton();
        setExitButton();
    }

    private void setNewGameButton() {
        newGameButton = new MenuButton(30,50,200,50, "CREATE NEW GAME");
        newGameButton.setEnabled(false);

        this.add(newGameButton);
    }

    private void setLoadSaveButton() {
        loadGameButton = new MenuButton(270, 50, 200, 50, "LOAD GAME");

        loadGameButton.addActionListener(e -> {
            if (e.getSource() == loadGameButton) {
                new CsvSaveReader(unitFactory, unitDatabase);
                startGameButton.setEnabled(true);
            }
        });

        this.add(loadGameButton);
    }

    private void setExitButton() {
        exitButton = new MenuButton(270, 150, 200, 50, "EXIT GAME");

        exitButton.addActionListener(e -> {
            if (e.getSource() == exitButton){
                System.exit(0);
            }
        });

        this.add(exitButton);
    }

    private void setStartGameButton() {
        startGameButton = new MenuButton(30, 150, 200, 50, "START GAME");
        startGameButton.setEnabled(false);

        startGameButton.addActionListener(event -> {
            startGameTask process = new startGameTask();
            try {
                this.dispose();
                engine.createNewSimulationControlFrame();
                process.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.add(startGameButton);
    }
    class startGameTask extends SwingWorker {

        @Override
        protected Object doInBackground(){
            objectPlacementService.placeAllUnits(unitDatabase.getAllUnits());
            engine.simulateBattle();
            return null;
        }
        @Override
        public void done(){
            engine.removeSimulationControlFrame();
            engine.createNewEndScreenFrame();
        }

    }
}
