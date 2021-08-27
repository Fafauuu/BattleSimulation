package gui;

import model.BattleField;
import model.StaticSimulationObject;
import model.Unit;
import model.UnitDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainPanel extends JPanel {
    private final BattleField battleField;
    private final UnitDatabase unitDatabase;
    private final List<ObjectLabel> staticObjectLabels = new ArrayList<>();
    private final List<ObjectLabel> unitLabels = new ArrayList<>();
    private final int panelSize;

    public MainPanel(BattleField battleField, UnitDatabase unitDatabase) {
        this.battleField = battleField;
        this.unitDatabase = unitDatabase;
        panelSize = battleField.getFieldSize() * ObjectLabelSize.SIZE;

        addStaticObjectLabels();
        addStaticObjectLabelsToPanel();
        addUnitLabels();
        addUnitLabelsToPanel();

        this.setLayout(null);
//        this.setPreferredSize(new Dimension(500,500));
        this.setBounds(0, 0, panelSize, panelSize);
        this.setBackground(Color.RED);
    }

    public void addStaticObjectLabels() {
        List<StaticSimulationObject> staticObjects = battleField.getStaticSimulationObjects();

        for (StaticSimulationObject staticObject : staticObjects) {
            staticObjectLabels.add(staticObject.getLabel());
        }
    }

    private void addStaticObjectLabelsToPanel() {
        for (ObjectLabel staticObjectLabel : staticObjectLabels) {
            staticObjectLabel.setBounds(staticObjectLabel.getLabeledObject().getYCoordinate() * ObjectLabelSize.SIZE,
                    staticObjectLabel.getLabeledObject().getXCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            this.add(staticObjectLabel);
            staticObjectLabel.setVisible(true);
        }
    }

    public void addUnitLabels() {
        List<Unit> units = unitDatabase.getAllUnits();
        for (Unit unit : units) {
            unitLabels.add(unit.getLabel());
        }
    }

    public void addUnitLabelsToPanel() {
        for (ObjectLabel unitLabel : unitLabels) {
            unitLabel.setBounds(unitLabel.getLabeledObject().getYCoordinate() * ObjectLabelSize.SIZE,
                    unitLabel.getLabeledObject().getXCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            this.add(unitLabel);
            unitLabel.setVisible(true);
        }
    }

    public void repaintUnits() {
        List<Unit> listOfUnits = unitDatabase.getAllUnits();
        for (Unit unit : listOfUnits) {
            unit.getLabel().setBounds(unit.getYCoordinate() * ObjectLabelSize.SIZE,
                    unit.getXCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            unit.getLabel().repaint();
        }

    }

    public int getPanelSize() {
        return panelSize;
    }

    public void paint(Graphics g) {
        super.paint(g);

        repaintUnits();
    }
}
