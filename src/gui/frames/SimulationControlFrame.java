package gui.frames;

import gui.buttons.MenuButton;
import service.Engine;

import javax.swing.*;

public class SimulationControlFrame extends AMenu{
    private final Engine engine;
    private JButton exitSimulationButton;
    private JButton pauseSimulationButton;
    private JButton resumeSimulationButton;

    public SimulationControlFrame(Engine engine) {
        super(350,250, "Control panel");

        this.engine = engine;

        setExitSimulationButton();
        setPauseSimulationButton();
        setResumeSimulationButton();
    }

    private void setExitSimulationButton() {
        exitSimulationButton = new MenuButton(75,150,200,50, "END SIMULATION");
        exitSimulationButton.setEnabled(true);

        exitSimulationButton.addActionListener(e -> {
            if(e.getSource() == exitSimulationButton){
                this.dispose();
                engine.restartSimulation();
            }
        });

        this.add(exitSimulationButton);
    }

    private void setPauseSimulationButton() {
        pauseSimulationButton = new MenuButton(50,50,100,50, "PAUSE");
        pauseSimulationButton.setEnabled(true);

        pauseSimulationButton.addActionListener(e -> {
            if(e.getSource() == pauseSimulationButton){
                engine.stopSimulation();
            }
        });

        this.add(pauseSimulationButton);
    }

    private void setResumeSimulationButton() {
        resumeSimulationButton = new MenuButton(200,50,100,50, "RESUME");
        resumeSimulationButton.setEnabled(true);

        resumeSimulationButton.addActionListener(e -> {
            if(e.getSource() == resumeSimulationButton){
                engine.resumeSimulation();
            }
        });

        this.add(resumeSimulationButton);
    }
}
