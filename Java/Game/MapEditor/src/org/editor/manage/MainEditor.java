package org.editor.manage;

import javax.swing.*;

public class MainEditor {

    public static void main(String[] args) {
        JFrame window = new JFrame("Map Editor");
        window.setContentPane(new EditorPanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}

