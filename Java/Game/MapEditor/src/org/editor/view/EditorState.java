package org.editor.view;
import org.editor.model.*;
import org.editor.manage.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditorState extends State{
    EditorStateModel m;
    Background bg;
    BufferedImage toolsBg;
    BufferedImage fireguy;
    BufferedImage obstacle;
    BufferedImage redzone;
    BufferedImage bonus;
    BufferedImage diamond;

    public EditorState(EditorStateManager esm){
        try{
            bg = new Background("/bg.png");
            toolsBg = ImageIO.read(getClass().getResourceAsStream("/toolsbg.png"));
            fireguy = ImageIO.read(getClass().getResourceAsStream("/monster.png"));
            obstacle = ImageIO.read(getClass().getResourceAsStream("/obsSquare.png"));
            redzone = ImageIO.read(getClass().getResourceAsStream("/redGround.png"));
            bonus = ImageIO.read(getClass().getResourceAsStream("/bonus.png"));
            diamond = ImageIO.read(getClass().getResourceAsStream("/heart.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new EditorStateModel(esm);
        this.esm = esm;
    }

    public void init() { }

    public void update() {
        m.update();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);

        for(int i = 0; i < m.getWalls().size(); ++i){
            m.getWalls().get(i).draw(g);
        }
        for (int i = 0; i < m.getR().size(); ++i) {
            m.getR().get(i).draw(g);
        }
        for(int i = 0; i < m.getB().size(); ++i){
            m.getB().get(i).draw(g);
        }
        for(int i = 0; i < m.getD().size(); ++i){
            m.getD().get(i).draw(g);
        }
        for(int i = 0; i < m.getObs().size(); ++i){
            m.getObs().get(i).draw(g);
        }
        for(int i = 0; i < m.getF().size(); ++i){
            m.getF().get(i).draw(g);
        }
            g.drawImage(toolsBg, 10, 185, null);
            g.setFont(new Font("Minecraft Rus", Font.PLAIN, 25));
            g.setColor(Color.WHITE);
            for (int i = 0; i < 5; ++i)
                g.drawString(String.valueOf(i), 60, 240 + i * 65);
            g.setFont(new Font("Minecraft Rus", Font.PLAIN, 20));
            g.drawString("Item to place : ", 50, 80);
        g.setFont(new Font("UAV OSD Mono", Font.PLAIN, 10));
        g.setColor(Color.WHITE);
        g.drawString("Press 'F' to save file", 50, 650);
        g.drawString("FILE SAVED: ", 50, 670);
        g.setColor(Color.GREEN);
        g.drawString(m.fileToWrite, 160, 670);
            if (m.getCurrentChoice() == 0) {
                g.drawImage(obstacle, 250, 40, 60, 60, null);
            }
            if (m.getCurrentChoice() == 1) {
                g.drawImage(redzone, 250, 40, 60, 60, null);
            }
            if (m.getCurrentChoice() == 2) {
                 g.drawImage(fireguy, 250, 40, 60, 60, null);
             }
            if (m.getCurrentChoice() == 3) {
                g.drawImage(bonus, 250, 40, 60, 60, null);
            }
            if (m.getCurrentChoice() == 4) {
                g.drawImage(diamond, 250, 40, 60, 60, null);
            }
    }

    public void keyPressed(int k) {
        m.keyPressedCheck(k);
    }

    public void keyReleased(int k) {
        m.keyReleasedCheck(k);
    }
}
