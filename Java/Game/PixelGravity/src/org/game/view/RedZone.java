package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RedZone{
    RedZoneModel m;
    BufferedImage icon;

    public RedZone(int x, int y){
        String s;
        if(y == -10) s = "/redGroundUp.png";
        else s = "/redGround.png";
        try {
            icon = ImageIO.read(getClass().getResourceAsStream(s));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        m = new RedZoneModel(x, y, 200, 150);
    }

    public RedZoneModel getModel(){return m;}

    public void draw(Graphics2D gtd){
        try{
            if(m.getY() > -20 && m.getY() < 30) icon = ImageIO.read(getClass().getResourceAsStream("/redGroundUp.png"));
        } catch(Exception e){
            e.printStackTrace();
        }gtd.drawImage(icon, m.getX(), m.getY(), 200,150, null);
    }
}
