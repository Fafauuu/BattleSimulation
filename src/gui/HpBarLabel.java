package gui;

import model.Unit;

import javax.swing.*;
import java.awt.*;

public class HpBarLabel extends JLabel {

    private final Unit labeledUnit;
    private final int barYShift = 5;
    private final int barWidth = ObjectLabelSize.SIZE - 2 * barYShift;
    private int percentageHp;

    public HpBarLabel(Unit labeledUnit) {
        this.labeledUnit = labeledUnit;
        this.setLayout(null);
    }

    private Color hpBarColor(){
        Color hpBarColor;
        percentageHp = (int) Math.ceil((double) labeledUnit.getHp() / labeledUnit.getMaxHp() * 100);

        if (percentageHp > 75 && percentageHp <= 100) {
            hpBarColor = new Color(48, 226, 14);
        }
        else if (percentageHp > 50 && percentageHp <= 75) {
            hpBarColor = new Color(245, 213, 33);
        }
        else if (percentageHp > 25 && percentageHp <= 50) {
            hpBarColor = new Color(250, 113, 4);
        }
        else {
            hpBarColor = new Color(215, 11, 11);
        }

        return hpBarColor;
    }

    private int calculateFilledBarWidth(){
        return barWidth * percentageHp / 100;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        int barXShift = 5;
        int barHeight = 5;
        int filledBarWidth = calculateFilledBarWidth();

        g2D.setColor(Color.white);
        g2D.fillRect(barXShift, barYShift, barWidth, barHeight);
        g2D.setColor(hpBarColor());
        g2D.fillRect(barXShift, barYShift, filledBarWidth, barHeight);
        g2D.setColor(Color.black);
        g2D.drawRect(barXShift, barYShift, barWidth, barHeight);
    }
}
