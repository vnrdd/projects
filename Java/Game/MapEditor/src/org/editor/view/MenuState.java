package org.editor.view;
import org.editor.manage.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends State{
    private Background bg;
    private int currentChoice = 0;
    private final String[] options = {"Start", "Quit"};

    private Font font;

    public MenuState(EditorStateManager esm) {
        this.esm = esm;

        try {
            bg = new Background("/menubg.png");
            font = new Font("Minecraft Rus", Font.PLAIN, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
    };

    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setFont(font);
        for (int i = 0; i < options.length; ++i) {
            if (i == currentChoice) {
                g.setColor(new Color(67, 97, 158));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 480 - options[i].length()*7, 500 + i * 40);
        }
    };

    public void update() {
    };

    public void select() {
        if (currentChoice == 0) {
            esm.setState(EditorStateManager.EDITORSTATE);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) select();
        if (k == KeyEvent.VK_UP) {
            --currentChoice;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            ++currentChoice;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    };

    public void keyReleased(int k) {
    };
}
