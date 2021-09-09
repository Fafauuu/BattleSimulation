package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Cavalry;
import model.objects.units.Unit;

import javax.swing.*;

public class CavalryBasicAttackAnimation extends Animation {

    public CavalryBasicAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/cavalryBasicAnimationGraphic.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
    }
}
