package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Unit;

import javax.swing.*;

public class ArcherBasicAttackAnimation extends Animation {
    public ArcherBasicAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/archerBasicAnimationGraphic.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
        directWeapon();
    }
}
