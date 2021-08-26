package gui;

import model.BattleField;
import model.StaticSimulationObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {
    private final BattleField battleField;
    private final List<ObjectLabel> staticObjectLabels = new ArrayList<>();
    private final int panelSize;

    public MainPanel(BattleField battleField) {
        this.battleField = battleField;
        panelSize = battleField.getFieldSize()* ObjectLabelSize.SIZE;

        addStaticObjectLabels();
        addStaticObjectLabelsToPanel();

        this.setLayout(null);
//        this.setPreferredSize(new Dimension(500,500));
        this.setBounds(0,0,panelSize,panelSize);
        this.setBackground(Color.RED);
    }

    public void addStaticObjectLabels(){
        List<StaticSimulationObject> staticObjects = battleField.getStaticSimulationObjects();

        for (StaticSimulationObject staticObject : staticObjects) {
            staticObjectLabels.add(staticObject.getLabel());
        }
    }

    private void addStaticObjectLabelsToPanel() {
        for (ObjectLabel staticObjectLabel : staticObjectLabels) {
            staticObjectLabel.setBounds(staticObjectLabel.getLabeledObject().getXCoordinate() * ObjectLabelSize.SIZE,
                    staticObjectLabel.getLabeledObject().getYCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            this.add(staticObjectLabel);
            staticObjectLabel.setVisible(true);
        }
    }

    public int getPanelSize() {
        return panelSize;
    }
}
