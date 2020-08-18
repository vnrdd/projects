package org.game.manage;

import javax.swing.*;

public class MainGame {
    public static void main(String[] args) {
        JFrame window = new JFrame("Pixel Gravity");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
