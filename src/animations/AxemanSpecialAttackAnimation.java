package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Unit;

import javax.swing.*;

public class AxemanSpecialAttackAnimation extends Animation {
    int rotationCounter = 0;

    public AxemanSpecialAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/axemanSpecialAnimationGraphic.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE * 3, ObjectLabelSize.SIZE * 3);
        this.XCoordinate = (attacker.getYCoordinate() - 1) * ObjectLabelSize.SIZE;
        this.YCoordinate = (attacker.getXCoordinate() - 1) * ObjectLabelSize.SIZE;
    }

    @Override
    public void updateAnimation() {
        rotateImag(image, 9);
        rotationCounter++;
        if (rotationCounter == 45){
            this.stopAnimation = true;
        }
    }
}
