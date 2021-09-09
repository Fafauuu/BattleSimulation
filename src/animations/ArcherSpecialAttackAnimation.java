package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Unit;

import javax.swing.*;

public class ArcherSpecialAttackAnimation extends Animation {
    public ArcherSpecialAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/archerSpecialAnimationGraphic.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
        directWeapon();
    }
}
