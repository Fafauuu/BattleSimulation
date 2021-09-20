package gui.frames;

import service.*;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private final Engine engine;
    private final UnitFactory unitFactory;
    private final UnitDatabase unitDatabase;
    private final ObjectPlacementService objectPlacementService;
    private final int frameWidth;
    private final int frameHeight;
    private JButton loadGameButton;
    private JButton newGameButton;
    private JButton startGameButton;

    public MenuFrame(Engine engine, UnitFactory unitFactory,
                     UnitDatabase unitDatabase, ObjectPlacementService objectPlacementService) {
        this.engine = engine;
        this.unitFactory = unitFactory;
        this.unitDatabase = unitDatabase;
        this.objectPlacementService = objectPlacementService;
        this.frameWidth = 500;
        this.frameHeight = 250;
        this.setBackground(Color.RED);

        this.setSize(new Dimension(frameWidth + 16, frameHeight + 39));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Main menu");

        this.setVisible(true);


        setNewGameButton();
        setLoadSaveButton();
        setStartGameButton();
    }

    private void setNewGameButton() {
        newGameButton = new JButton();
        newGameButton.setLayout(null);
        newGameButton.setBounds(30, 50, 200, 50);
        newGameButton.setText("NEW GAME");
        newGameButton.setFocusable(false);
        newGameButton.setHorizontalTextPosition(JButton.CENTER);
        newGameButton.setVerticalTextPosition(JButton.CENTER);
        newGameButton.setVisible(true);
        newGameButton.setEnabled(false);

        this.add(newGameButton);
    }

    private void setLoadSaveButton() {
        loadGameButton = new JButton();

        loadGameButton.addActionListener(e -> {
            if (e.getSource() == loadGameButton) {
                new CsvSaveReader(unitFactory, unitDatabase);
                startGameButton.setEnabled(true);
            }
        });

        loadGameButton.setLayout(null);
        loadGameButton.setBounds(270, 50, 200, 50);
        loadGameButton.setText("LOAD GAME");
        loadGameButton.setFocusable(false);
        loadGameButton.setHorizontalTextPosition(JButton.CENTER);
        loadGameButton.setVerticalTextPosition(JButton.CENTER);
        loadGameButton.setVisible(true);

        this.add(loadGameButton);
    }

    private void setStartGameButton() {
        startGameButton = new JButton();

        startGameButton.setLayout(null);
        startGameButton.setBounds(150, 150, 200, 50);
        startGameButton.setText("START GAME");
        startGameButton.setFocusable(false);
        startGameButton.setHorizontalTextPosition(JButton.CENTER);
        startGameButton.setVerticalTextPosition(JButton.CENTER);
        startGameButton.setVisible(true);
        startGameButton.setEnabled(false);

        startGameButton.addActionListener(event -> {
            startGameTask process = new startGameTask();
            try {
                this.dispose();
                process.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.add(startGameButton);
    }

    class startGameTask extends SwingWorker {
        protected Object doInBackground() throws Exception {
            objectPlacementService.placeAllUnits(unitDatabase.getAllUnits());
            engine.simulateBattle();
            return null;
        }
    }
}
