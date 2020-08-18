package org.editor.view;
import org.editor.model.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus {
    BonusModel m;
    BufferedImage icon;

    public Bonus(int x, int y){
        try{
            icon = ImageIO.read(getClass().getResourceAsStream("/bonus.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        m = new BonusModel(x, y);
    }

    public BonusModel getModel(){
        return m;
    }

    public void draw(Graphics2D g){
        g.drawImage(icon, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
    }
}
