package animations;

import gui.labels.ObjectLabelSize;
import model.objects.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Animation {
    protected Unit attacker;
    protected Unit target;
    protected BufferedImage image;
    protected int XCoordinate;
    protected int YCoordinate;
    protected boolean stopAnimation;
    protected AnimationDirections animationDirection;

    public Animation(Unit attacker, Unit target) {
        this.attacker = attacker;
        this.target = target;
        this.XCoordinate = attacker.getYCoordinate() * ObjectLabelSize.SIZE;
        this.YCoordinate = attacker.getXCoordinate() * ObjectLabelSize.SIZE;
        this.stopAnimation = false;
        setAnimationDirection();
    }

    public abstract void updateAnimation();

    private void setAnimationDirection() {

        int XDifference = target.getXCoordinate() - attacker.getXCoordinate();
        int YDifference = target.getYCoordinate() - attacker.getYCoordinate();

        if (XDifference == 0 && YDifference > 0) {
            this.animationDirection = AnimationDirections.RIGHT;
        }
        else if (XDifference == 0 && YDifference < 0) {
            this.animationDirection = AnimationDirections.LEFT;
        }
        else if (XDifference < 0 && YDifference == 0) {
            this.animationDirection = AnimationDirections.UP;
        }
        else if (XDifference > 0 && YDifference == 0) {
            this.animationDirection = AnimationDirections.DOWN;
        }
        else if (XDifference < 0 && YDifference > 0) {
            this.animationDirection = AnimationDirections.UP_RIGHT;
        }
        else if (XDifference < 0 && YDifference < 0) {
            this.animationDirection = AnimationDirections.UP_LEFT;
        }
        else if (XDifference > 0 && YDifference > 0) {
            this.animationDirection = AnimationDirections.DOWN_RIGHT;
        }
        else {
            this.animationDirection = AnimationDirections.DOWN_LEFT;
        }
    }

    protected void rotateImag(BufferedImage img, int n) { //n rotation in gradians
        double rotationRequired = Math.toRadians(n);
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 1.3;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        op.filter(img, newImage);

        this.image = newImage;
    }

    protected BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }

    protected static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    public Image getImage() {
        return image;
    }

    public int getXCoordinate() {
        return XCoordinate;
    }

    public int getYCoordinate() {
        return YCoordinate;
    }

    public boolean isStopAnimation() {
        return stopAnimation;
    }
}
