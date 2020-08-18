package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall{
    WallModel m;
    BufferedImage icon;

    public Wall(int x, int y, int width, int height){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/ground2.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new WallModel(x, y, width, height);
    }
    public WallModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
