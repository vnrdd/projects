package org.game.view;
import org.game.manage.*;
import org.game.files.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ChooseTypeState extends GameState {
    private Background bg;
    private String fileToRead = "";
    private int currentSubChoice = 0;
    private final String[] options = {"Infinity Map", "Load Map"};

    private Font font;

    public ChooseTypeState(GameStateManager gsm) {
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
            g.drawString(options[i], 480 - options[i].length()*5, 320 + i * 40);
        }
        g.setFont(new Font("UAV OSD Mono", Font.PLAIN, 10));
        g.setColor(Color.WHITE);
        g.drawString("Press 'L' to load file", 50, 650);
        g.drawString("FILE LOADED: ", 50, 670);
        g.setColor(Color.GREEN);
        g.drawString(fileToRead, 170, 670);
        g.setColor(Color.ORANGE);
        g.drawString("To play 'Load Map' you", 700, 650);
        g.drawString("must load the file", 700, 670);
    };

    public void update() {
    };

    public void select() {
        if (currentSubChoice == 0) {
            gsm.setState(GameStateManager.PLAYSTATE, 0, "");
        }
        if (currentSubChoice == 1 && !fileToRead.equals("")) {
            gsm.setState(GameStateManager.PLAYSTATE, -1, fileToRead);
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
        if(k == KeyEvent.VK_L){
            fileToRead = FileWorker.getFileName();
        }
    };

    public void keyReleased(int k) {
    };
}
