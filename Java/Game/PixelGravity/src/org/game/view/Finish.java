package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Finish{
    FinishModel m;
    BufferedImage icon;

    public Finish(int x, int y){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/finish.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new FinishModel(x, y, 85, 720);
    }
    public FinishModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        gtd.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
