package org.editor.view;
import org.editor.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RedZone {
    RedZoneModel m;
    BufferedImage icon;

    public RedZone(int x, int y){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/redGround.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        m = new RedZoneModel(x, y);
    }

    public RedZoneModel getModel(){
        return m;
    }

    public void draw(Graphics2D g){
        try{
            if(m.getY() < 20) icon = ImageIO.read(getClass().getResourceAsStream("/redGroundUp.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        g.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
