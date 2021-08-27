package gui;

import model.SimulationObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ObjectLabel extends JLabel {

    private final SimulationObject labeledObject;

    public ObjectLabel(SimulationObject labeledObject, String iconPath, Color backgroundColor) {

        this.labeledObject = labeledObject;
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border);
        this.setBackground(backgroundColor);
        this.setOpaque(true);
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().
                getScaledInstance(ObjectLabelSize.SIZE, ObjectLabelSize.SIZE, Image.SCALE_DEFAULT));
        this.setIcon(icon);
//        this.setText(labeledObject.toString());
        this.setLayout(null);
    }

    public SimulationObject getLabeledObject() {
        return labeledObject;
    }

    @Override
    public String toString() {
        return "ObjectLabel{" +
                "labeledObject=" + labeledObject +
                '}';
    }
}
