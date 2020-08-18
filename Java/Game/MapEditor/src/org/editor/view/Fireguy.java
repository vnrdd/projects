package org.editor.view;
import org.editor.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Fireguy {
    FireguyModel m;
    BufferedImage icon;

    public Fireguy(int x, int y){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/fireGuyDown.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        m = new FireguyModel(x, y);
    }

    public FireguyModel getModel(){
        return m;
    }

    public void draw(Graphics2D g){
       try {
           if (m.getY() < 300) icon = ImageIO.read(getClass().getResourceAsStream("/fireGuyUp.png"));
           else icon = ImageIO.read(getClass().getResourceAsStream("/fireGuyDown.png"));
       } catch(Exception e){
           e.printStackTrace();
       }
        g.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
