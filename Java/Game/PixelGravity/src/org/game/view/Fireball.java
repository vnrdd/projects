package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Fireball{
    FireballModel m;
    BufferedImage icon;

    public Fireball (int x, int y, int width, int height){
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/fireball.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new FireballModel(x, y, width, height);
    }
    public FireballModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.drawImage(icon, m.getX(), m.getY(), 50,30, null);
    }
}
