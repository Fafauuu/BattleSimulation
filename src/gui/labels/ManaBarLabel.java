package gui.labels;

import model.objects.units.Unit;

import javax.swing.*;
import java.awt.*;

public class ManaBarLabel extends JProgressBar {
    private Unit labeledUnit;
    private int maxMana;
    private int currentMana;

    public ManaBarLabel(Unit labeledUnit) {
        this.labeledUnit = labeledUnit;
        this.maxMana = labeledUnit.getStatistics().getMaxMana();
        this.currentMana = labeledUnit.getStatistics().getMana();
        this.setMinimum(0);
        this.setMaximum(maxMana);
        this.setForeground(new Color(30, 106, 166));
        updateManaBar();
    }

    public void setManaBarBounds() {
        int barXShift = 15;
        int barYShift = 5;
        int barWidth = ObjectLabelSize.SIZE - 2 * barYShift;
        int barHeight = 8;

        this.setBounds(barYShift, barXShift, barWidth, barHeight);
    }

    public void updateManaBar(){
        currentMana = labeledUnit.getStatistics().getMana();
        this.setValue(currentMana);
    }
}
