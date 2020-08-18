package org.game.view;
import org.game.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Diamond{
    DiamondModel m;
    BufferedImage icon;

    public Diamond(int x, int y){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/heart.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new DiamondModel(x, y, 50, 50);
    }
    public DiamondModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
