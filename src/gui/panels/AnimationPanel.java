package gui.panels;

import animations.Animation;
import model.BattleField;
import service.UnitDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnimationPanel extends JPanel implements ActionListener {
    private final BattleField battleField;
    private final UnitDatabase unitDatabase;
    private final List<Animation> animationList;
    private final Timer timer;


    public AnimationPanel(BattleField battleField, UnitDatabase unitDatabase) {
        this.battleField = battleField;
        this.unitDatabase = unitDatabase;
        this.animationList = new ArrayList<>();
        this.timer = new Timer(10, this);

        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setOpaque(false);
    }

    public void addAnimation(Animation animation) {
        animationList.add(animation);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Animation animation : animationList) {
            g.drawImage(animation.getImage(), animation.getXCoordinate(), animation.getYCoordinate(), null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Animation animation : animationList) {
            if (!animation.isStopAnimation()) {
                animation.updateAnimation();
            }
        }

        dropFulfilledAnimations();
        repaint();
    }

    public void dropFulfilledAnimations() {
        animationList.removeIf(animation -> animation.isStopAnimation());
    }

    public Timer getTimer() {
        return timer;
    }

    //    public void clearAnimationList(){
//        this.animationList.clear();
//    }
}
