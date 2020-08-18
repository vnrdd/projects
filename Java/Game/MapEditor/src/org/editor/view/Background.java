package org.editor.view;

import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.*;

public class Background {
    private BufferedImage image;

    public Background(String s) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
    }
}
