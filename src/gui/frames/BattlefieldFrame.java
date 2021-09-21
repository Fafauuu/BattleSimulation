package gui.frames;

import gui.panels.AnimationPanel;
import gui.panels.MainPanel;
import model.BattleField;
import service.UnitDatabase;

import javax.swing.*;
import java.awt.*;

public class BattlefieldFrame extends JFrame {
    MainPanel mainPanel;
    AnimationPanel animationPanel;

    public BattlefieldFrame(BattleField battlefield, UnitDatabase unitDatabase) {

        mainPanel = new MainPanel(battlefield, unitDatabase);
        mainPanel.setBounds(mainPanel.getBounds());

        animationPanel = new AnimationPanel(battlefield, unitDatabase);
        animationPanel.setBounds(mainPanel.getBounds());

        this.setSize(new Dimension(mainPanel.getPanelSize() + 16, mainPanel.getPanelSize() + 39));
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.add(animationPanel);
        this.add(mainPanel);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public AnimationPanel getAnimationPanel() {
        return animationPanel;
    }
}
