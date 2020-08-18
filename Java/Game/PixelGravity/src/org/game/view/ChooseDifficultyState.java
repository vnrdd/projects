package org.game.view;
import org.game.manage.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ChooseDifficultyState extends GameState{
    private Background bg;
    private int currentSubChoice = 0;
    private final String[] options = {"Easy", "Medium", "Hard"};

    private Font font;

    public ChooseDifficultyState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            bg = new Background("/bg.png");
            font = new Font("UAV OSD MONO", Font.PLAIN, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(int flag) {
    };

    public void init(int flag, String filename) {
    };

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        g.setColor(new Color(67, 97, 158));
        g.drawString("Press ESC to join MENU", 30, 30);
        g.setFont(new Font("Minecraft Rus", Font.PLAIN, 24));
        for (int i = 0; i < options.length; ++i) {
            if (i == currentSubChoice) {
                g.setColor(new Color(67, 97, 158));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 480 - options[i].length()*7, 320 + i * 40);
        }
    };

    public void update() {
    };

    public void select() {
        if (currentSubChoice == 0) {
            gsm.setState(GameStateManager.PLAYSTATE, 0, "");
        }
        if (currentSubChoice == 1) {
            gsm.setState(GameStateManager.PLAYSTATE, 1, "");
        }
        if (currentSubChoice == 2) {
            gsm.setState(GameStateManager.PLAYSTATE, 2, "");
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (k == KeyEvent.VK_UP) {
            --currentSubChoice;
            if (currentSubChoice == -1) {
                currentSubChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            ++currentSubChoice;
            if (currentSubChoice == options.length) {
                currentSubChoice = 0;
            }
        }
    };

    public void keyReleased(int k) {
    };
}
