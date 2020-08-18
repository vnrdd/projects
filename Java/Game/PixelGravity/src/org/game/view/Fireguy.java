package org.game.view;
import org.game.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Fireguy{
    FireguyModel m;
    BufferedImage icon;

    public Fireguy (int x, int y, PlayStateModel ps){
        String s;
        if(y >= 460) s = "/fireGuyDown.png";
        else s = "/fireGuyUp.png";
        try {
            icon = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (Exception e){
            e.printStackTrace();
        }
        m = new FireguyModel(x, y, 80, 80, ps);
    }
    public FireguyModel getModel(){return m;}
    public void draw(Graphics2D gtd){
        try{
            if(m.getY() < 300) icon = ImageIO.read(getClass().getResourceAsStream("/fireGuyUp.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        gtd.drawImage(icon, m.getX(), m.getY(), 90,90, null);
        if(m.getBullet().getModel().isFlying()) m.getBullet().draw(gtd);
    }

}
