package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Unit;

import javax.swing.*;

public class KnightSpecialAttackAnimation extends Animation {

    public KnightSpecialAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        this.XCoordinate = attacker.getYCoordinate() * ObjectLabelSize.SIZE;
        this.YCoordinate = attacker.getXCoordinate() * ObjectLabelSize.SIZE;
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/knightSpecialAnimationGraphic.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
    }
}
