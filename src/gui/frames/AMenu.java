package gui.frames;

import javax.swing.*;
import java.awt.*;

public abstract class AMenu extends JFrame {
    public AMenu(int frameWidth, int frameHeight, String title){
        this.setSize(new Dimension(frameWidth + 16, frameHeight + 39));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle(title);
        this.setVisible(true);
    }
}
