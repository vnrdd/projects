package org.game.view;

import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.*;

public class Background {
    private BufferedImage image;

    private double x;
    private double y;

    public Background(String s) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
