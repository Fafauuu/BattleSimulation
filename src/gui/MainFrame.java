package gui;

import model.BattleField;
import model.UnitDatabase;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(BattleField battlefield, UnitDatabase unitDatabase) {

        MainPanel mainPanel = new MainPanel(battlefield, unitDatabase);
        mainPanel.setBounds(mainPanel.getBounds());

        this.setSize(new Dimension(mainPanel.getPanelSize() + 16, mainPanel.getPanelSize() + 39));
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.add(mainPanel);


//        JLabel jLabel = new JLabel();
//        jLabel.setIcon(new ImageIcon("src/icons/knight.png"));
//        jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
//        jLabel.setText("wtf");
//        jLabel.setBounds(0,0,200,200);
//        jLabel.setLayout(null);
//        jLabel.setVisible(true);
//        this.add(jLabel);

    }
}
