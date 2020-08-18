package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle{
    ObstacleModel m;
    BufferedImage icon;

    public Obstacle(int x, int y){
       try {
           icon = ImageIO.read(getClass().getResourceAsStream("/obsSquare.png"));
       }
       catch(Exception e){
           e.printStackTrace();
       }
       m = new ObstacleModel(x ,y, 100, 100);
    }
    public ObstacleModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
