package gui;

import model.SimulationObject;
import model.Unit;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class UnitLabel extends ObjectLabel {
    private HpBarLabel hpBarLabel;
    private ManaBarLabel manaBarLabel;
    private DamageLayeredPane damageLayeredPane;

    public UnitLabel(SimulationObject labeledObject, String iconPath, Color backgroundColor) {
        super(labeledObject, iconPath, backgroundColor);
        addHpLabel((Unit) labeledObject);
        addManaLabel((Unit) labeledObject);
        addDamageLayeredPane();
    }

    private void addManaLabel(Unit labeledObject) {
        manaBarLabel = new ManaBarLabel(labeledObject);
        manaBarLabel.setManaBarBounds();
        this.add(manaBarLabel);
    }

    private void addHpLabel(Unit labeledObject) {
        hpBarLabel = new HpBarLabel(labeledObject);
        hpBarLabel.setHpBarBounds();
        this.add(hpBarLabel);
    }

    private void addDamageLayeredPane() {
        damageLayeredPane = new DamageLayeredPane();
        damageLayeredPane.setDamageLayeredPaneBounds();
        this.add(damageLayeredPane);
    }

    public void addDamageLabel(DamageLabel damageLabel, Timer animationTimer) {
        damageLayeredPane.addDamageLabel(damageLabel);
        damageLayeredPane.setDamageLabelBounds(damageLabel);
        damageLayeredPane.repaint();
        TimerTask task = new TimerTask() {
            public void run() {
                removeDamageLabel(damageLabel);
            }
        };
        animationTimer.schedule(task, 500);
    }

    private void removeDamageLabel(DamageLabel damageLabel) {
        damageLayeredPane.removeDamageLabel(damageLabel);
        damageLayeredPane.repaint();
    }

    public HpBarLabel getHpBarLabel() {
        return hpBarLabel;
    }

    public ManaBarLabel getManaBarLabel() {
        return manaBarLabel;
    }


}
