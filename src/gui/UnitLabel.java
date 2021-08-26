package gui;

import model.SimulationObject;
import model.Unit;

import java.awt.*;

public class UnitLabel extends ObjectLabel{
    public UnitLabel(SimulationObject labeledObject, String iconPath, Color backgroundColor) {
        super(labeledObject, iconPath, backgroundColor);
        HpBarLabel hpBarLabel = new HpBarLabel((Unit)labeledObject);

        hpBarLabel.setBounds(0,0,ObjectLabelSize.SIZE,ObjectLabelSize.SIZE);
        this.add(hpBarLabel);
    }
}
