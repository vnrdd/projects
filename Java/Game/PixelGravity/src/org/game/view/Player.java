package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    PlayerModel m;
    BufferedImage pl;
    BufferedImage pl2;
    BufferedImage s;
    BufferedImage l0;
    BufferedImage l1;
    BufferedImage l2;
    BufferedImage l3;
    BufferedImage dead;

    public Player(int x, int y, PlayStateModel ps){
        try{
            pl = ImageIO.read(getClass().getResourceAsStream("/playerDown.png"));
            pl2 = ImageIO.read(getClass().getResourceAsStream("/playerUp.png"));
            s = ImageIO.read(getClass().getResourceAsStream("/shieldDown.png"));
            dead = ImageIO.read(getClass().getResourceAsStream("/dead.png"));
            l0 = ImageIO.read(getClass().getResourceAsStream("/l0.png"));
            l1 = ImageIO.read(getClass().getResourceAsStream("/l1.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/l2.png"));
            l3 = ImageIO.read(getClass().getResourceAsStream("/l3.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        m = new PlayerModel(x, y, 50, 50, ps);
    }
    public PlayerModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.WHITE);
        if(m.getLives() != 0 && m.getKeySpace()) {
            if(m.getShield() != 0) gtd.drawImage(s, m.getX(), m.getY(), 50, 50, null);
            else gtd.drawImage(pl, m.getX(), m.getY(), 50, 50, null);
        }
        if(m.getLives() != 0 && !m.getKeySpace()) {
            if(m.getShield() != 0) gtd.drawImage(s, m.getX(), m.getY(), 50, 50, null);
            else gtd.drawImage(pl2, m.getX(), m.getY(), 50, 50, null);
        }
        if(m.getLives() == 0) gtd.drawImage(dead, m.getX(), m.getY(), 50, 50, null);
    }
}
