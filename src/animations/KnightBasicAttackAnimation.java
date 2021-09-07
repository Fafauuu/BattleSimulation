package animations;

import gui.labels.ObjectLabelSize;
import model.objects.Unit;

import javax.swing.*;

public class KnightBasicAttackAnimation extends Animation {

    public KnightBasicAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        ImageIcon imageIcon = new ImageIcon("src/icons/knightBasicAnimation.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
    }

    @Override
    public void updateAnimation() {
        int targetGuiX = target.getYCoordinate() * ObjectLabelSize.SIZE;
        int targetGuiY = target.getXCoordinate() * ObjectLabelSize.SIZE;

        switch (animationDirection) {
            case UP:
                this.YCoordinate -= 3;
                stopAnimation = this.YCoordinate <= targetGuiY;
                break;
            case DOWN:
                this.YCoordinate += 3;
                stopAnimation = this.YCoordinate >= targetGuiY;
                break;
            case RIGHT:
                this.XCoordinate += 3;
                stopAnimation = this.XCoordinate >= targetGuiX;
                break;
            case LEFT:
                this.XCoordinate -= 3;
                stopAnimation = this.XCoordinate <= targetGuiX;
                break;
        }
    }
}
