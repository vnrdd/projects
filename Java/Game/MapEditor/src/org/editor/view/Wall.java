package org.editor.view;
import org.editor.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall {
    WallModel m;
    BufferedImage icon;

    public Wall(int x, int y, int width, int height){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/ground2.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        m = new WallModel(x, y);
    }

    public WallModel getModel(){
        return m;
    }

    public void draw(Graphics2D g){
        g.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
