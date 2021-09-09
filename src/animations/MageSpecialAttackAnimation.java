package animations;

import gui.labels.ObjectLabelSize;
import model.objects.units.Mage;
import model.objects.units.Unit;

import javax.swing.*;

public class MageSpecialAttackAnimation extends Animation {
    boolean explosionStarted = false;
    int explosionRefreshes = 0;

    public MageSpecialAttackAnimation(Unit attacker, Unit target) {

        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/mageSpecialMissileAnimation.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
        directWeapon();
    }

    @Override
    public void updateAnimation() {

        if (!explosionStarted) {
            checkStopCondition();
            this.XCoordinate += xModifier * xVelocity;
            this.YCoordinate += yModifier * yVelocity;
        }

        if (this.stopAnimation) {
            ImageIcon imageIcon = new ImageIcon("src/graphics/animationGraphics/mageSpecialExplosionGraphic.png");
            this.image = imageIconToBufferedImage(imageIcon);
            this.image = resize(image, ObjectLabelSize.SIZE * 3, ObjectLabelSize.SIZE * 3);
            this.XCoordinate = (target.getYCoordinate() - 1) * ObjectLabelSize.SIZE;
            this.YCoordinate = (target.getXCoordinate() - 1) * ObjectLabelSize.SIZE;
            explosionStarted = true;
        }

        if (explosionStarted) {
            this.stopAnimation = false;
            explosionRefreshes++;
            if (explosionRefreshes == 15) {
                this.stopAnimation = true;
            }
        }
    }
}
