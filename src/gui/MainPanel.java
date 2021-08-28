package gui;

import model.BattleField;
import model.StaticSimulationObject;
import model.Unit;
import service.UnitDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainPanel extends JPanel {
    private final BattleField battleField;
    private final UnitDatabase unitDatabase;
    private final List<ObjectLabel> staticObjectLabels = new ArrayList<>();
    private final List<UnitLabel> unitLabels = new ArrayList<>();
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
            unitLabels.add(unit.getUnitLabel());
        }
    }

    public void addUnitLabelsToPanel() {
        for (UnitLabel unitLabel : unitLabels) {
            unitLabel.setBounds(unitLabel.getLabeledObject().getYCoordinate() * ObjectLabelSize.SIZE,
                    unitLabel.getLabeledObject().getXCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            this.add(unitLabel);
            unitLabel.setVisible(true);
        }
    }

    public void repaintUnit(Unit unit) {
        for (UnitLabel unitLabel : unitLabels) {
            if (unitLabel.getLabeledObject() == unit) {
                unitLabel.setBounds(unitLabel.getLabeledObject().getYCoordinate() * ObjectLabelSize.SIZE,
                        unitLabel.getLabeledObject().getXCoordinate() * ObjectLabelSize.SIZE,
                        ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
//                unitLabel.repaint();
            }
        }
    }

    public void removeUnitLabel(Unit unit) {
        unitLabels.removeIf(unitLabel -> unitLabel.getLabeledObject() == unit);
    }

    public int getPanelSize() {
        return panelSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        repaintUnits();
    }

    public void repaintUnits() {
        List<Unit> listOfUnits = unitDatabase.getAllUnits();
        for (Unit unit : listOfUnits) {
            unit.getUnitLabel().setBounds(unit.getYCoordinate() * ObjectLabelSize.SIZE,
                    unit.getXCoordinate() * ObjectLabelSize.SIZE,
                    ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
            unit.getUnitLabel().repaint();
        }
    }
}
