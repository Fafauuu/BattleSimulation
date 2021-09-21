package gui.frames;

import gui.buttons.MenuButton;
import service.Engine;

import javax.swing.*;

public class EndScreenFrame extends AMenu {
    private final Engine engine;

    private JButton saveGameButton;
    private JButton mainMenuButton;
    private JButton exitButton;

    public EndScreenFrame(Engine engine) {
        super(500,250,"Ending screen");

        this.engine = engine;

        setSaveGameButton();
        setMainMenuButton();
        setExitButton();
    }

    private void setSaveGameButton() {
        saveGameButton = new MenuButton(30, 50, 200, 50, "SAVE GAME");
        saveGameButton.setEnabled(false);

        this.add(saveGameButton);
    }

    private void setMainMenuButton() {
        mainMenuButton = new MenuButton(270, 50, 200, 50, "MAIN MENU");

        mainMenuButton.addActionListener(e -> {
            if (e.getSource() == mainMenuButton){
                engine.restartSimulation();
                this.dispose();
                engine.createNewMainMenuFrame();
            }
        });

        this.add(mainMenuButton);
    }

    private void setExitButton() {
        exitButton = new MenuButton(150, 150, 200, 50, "EXIT GAME");

        exitButton.addActionListener(e -> {
            if (e.getSource() == exitButton){
                System.exit(0);
            }
        });

        this.add(exitButton);
    }
}
