package gui.panels;

import gui.labels.ObjectLabelSize;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DamageLayeredPane extends JLayeredPane {
    List<DamageLabel> damageLabels = new ArrayList<>(4);

    public void setDamageLayeredPaneBounds() {
        int labelSize = ObjectLabelSize.SIZE;
        int xShift = labelSize/2 + labelSize/10;
        int yShift = labelSize/2 + labelSize/10;
        int width = labelSize/2;
        int height = labelSize/2;

        this.setBounds(yShift,xShift,width,height);
    }

    public void addDamageLabel(DamageLabel damageLabel){
        damageLabels.add(damageLabel);
        this.add(damageLabel);
    }

    public void removeDamageLabel(DamageLabel damageLabel){
        damageLabels.remove(damageLabel);
        this.remove(damageLabel);
    }

    public void setDamageLabelBounds(DamageLabel damageLabel) {
        int labelIndex = damageLabels.indexOf(damageLabel);
        damageLabel.setDamageLabelBounds(labelIndex);
    }
}
