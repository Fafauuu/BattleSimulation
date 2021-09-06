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
        this.setFont(new Font("MV Boli", Font.BOLD, 17));
        this.setText("-" + damage);
        this.setForeground(setDamageTakenColor());
        this.setOpaque(false);
        this.setVisible(true);
    }

    private Color setDamageTakenColor() {
        if (attackType == AttackTypes.PHYSICAL) {
            return new Color(0x9A1212);
        } else if (attackType == AttackTypes.MAGICAL) {
            return new Color(0x2020FF);
        } else {
            return new Color(0xE8E7D2);
        }
    }

    public void setDamageLabelBounds(int labelIndex) {
        int shiftModifier = 0;
        if (labelIndex != 0){
            shiftModifier = labelIndex * 10;
        }
        int xShift = getRandomNumber(0,shiftModifier);
        int yShift = getRandomNumber(0,shiftModifier);
        int width = 50;
        int height = 30;

        this.setBounds(yShift, xShift, width, height);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
