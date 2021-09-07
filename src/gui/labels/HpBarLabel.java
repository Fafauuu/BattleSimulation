package gui.labels;

import model.objects.Unit;

import javax.swing.*;
import java.awt.*;

public class HpBarLabel extends JProgressBar {
    private Unit labeledUnit;
    private int maxHp;
    private int currentHp;

    public HpBarLabel(Unit labeledUnit) {
        this.labeledUnit = labeledUnit;
        this.maxHp = labeledUnit.getStatistics().getMaxHp();
        this.currentHp = labeledUnit.getStatistics().getHp();
        this.setMinimum(0);
        this.setMaximum(maxHp);
        updateHpBar();
    }

    public void setHpBarBounds() {
        int barXShift = 5;
        int barYShift = 5;
        int barWidth = ObjectLabelSize.SIZE - 2 * barYShift;
        int barHeight = 8;

        this.setBounds(barYShift, barXShift, barWidth, barHeight);
    }

    public void updateHpBar() {
        currentHp = labeledUnit.getStatistics().getHp();
        this.setValue(currentHp);
        this.setForeground(setHpBarColor());
    }

    private Color setHpBarColor() {
        Color hpBarColor;
        int percentageHp = (int) Math.ceil(
                (double) labeledUnit.getStatistics().getHp() / labeledUnit.getStatistics().getMaxHp() * 100);

        if (percentageHp > 75 && percentageHp <= 100) {
            hpBarColor = new Color(48, 226, 14);
        } else if (percentageHp > 50 && percentageHp <= 75) {
            hpBarColor = new Color(245, 213, 33);
        } else if (percentageHp > 25 && percentageHp <= 50) {
            hpBarColor = new Color(250, 113, 4);
        } else {
            hpBarColor = new Color(215, 11, 11);
        }

        return hpBarColor;
    }
}
