package gui.buttons;

import javax.swing.*;

public class MenuButton extends JButton {
    public MenuButton(int x, int y, int width, int height, String text) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setText(text);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setVisible(true);
    }
}
