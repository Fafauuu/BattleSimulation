package animations;

import gui.ObjectLabelSize;
import model.Unit;

import javax.swing.*;

public class KnightBasicAttackAnimation extends Animation {

    public KnightBasicAttackAnimation(Unit attacker, Unit target) {
        super(attacker, target);
        this.XCoordinate = attacker.getYCoordinate() * ObjectLabelSize.SIZE;
        this.YCoordinate = attacker.getXCoordinate() * ObjectLabelSize.SIZE;
        ImageIcon imageIcon = new ImageIcon("src/icons/knightAnimation.png");
        this.image = imageIconToBufferedImage(imageIcon);
        this.image = resize(image, ObjectLabelSize.SIZE, ObjectLabelSize.SIZE);
        this.imageSize = 20;
    }

    @Override
    public void updateAnimation() {
        moveAnimation();
        checkStopCondition();
    }

    private void moveAnimation() {
        switch (animationDirection) {
            case UP:
                this.YCoordinate -= 3;
                break;
            case DOWN:
                this.YCoordinate += 3;
                break;
            case RIGHT:
                this.XCoordinate += 3;
                break;
            case LEFT:
                this.XCoordinate -= 3;
                break;
        }
    }

    private void checkStopCondition() {
        int targetGuiX = target.getYCoordinate() * ObjectLabelSize.SIZE;
        int targetGuiY = target.getXCoordinate() * ObjectLabelSize.SIZE;

        switch (animationDirection) {
            case UP:
                stopAnimation = this.YCoordinate <= targetGuiY;
                break;
            case DOWN:
                stopAnimation = this.YCoordinate >= targetGuiY;
                break;
            case RIGHT:
                stopAnimation = this.XCoordinate >= targetGuiX;
                break;
            case LEFT:
                stopAnimation = this.XCoordinate <= targetGuiX;
                break;
        }
    }


}
