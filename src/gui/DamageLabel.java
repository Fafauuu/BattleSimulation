package gui;

import model.AttackTypes;
import model.Unit;

import javax.swing.*;
import java.awt.*;

public class DamageLabel extends JLabel {
    private Unit attackedUnit;
    private AttackTypes attackType;
    private int damage;

    public DamageLabel(Unit attackedUnit, AttackTypes attackType, int damage) {
        this.attackedUnit = attackedUnit;
        this.attackType = attackType;
        this.damage = damage;
        this.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.setText("-" + damage);
        this.setForeground(setDamageTakenColor());
        this.setOpaque(false);
        this.setVisible(true);
    }

    private Color setDamageTakenColor() {
        if (attackType == AttackTypes.PHYSICAL) {
            return new Color(0x703217);
        } else if (attackType == AttackTypes.MAGICAL) {
            return new Color(0x3D3DE3);
        } else {
            return new Color(0xE8E7D2);
        }
    }

    public void setDamageLabelBounds(int shiftModifier) {
        int xShift = shiftModifier * 8;
        int yShift = shiftModifier * 8;
        int width = 50;
        int height = 30;

        this.setBounds(yShift, xShift, width, height);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
