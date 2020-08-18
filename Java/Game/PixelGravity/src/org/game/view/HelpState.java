package org.game.view;
import org.game.manage.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class HelpState extends GameState{
    private Background bg;
    private Background monster;
    private Background heart;
    private BufferedImage shield;
    private Font font;

    public HelpState(GameStateManager gsm){
        this.gsm = gsm;

        try {
            bg = new Background("/bg.png");
            monster = new Background("/monster.png");
            heart = new Background("/heart.png");
            shield = ImageIO.read(getClass().getResourceAsStream("/shieldDown.png"));
            monster.setPosition(100, 410);
            heart.setPosition(100, 500);
            font = new Font("UAV OSD MONO", Font.PLAIN, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(int flag){}
    public void init(int flag, String filename) {};
    public void update(){}
    public void draw(Graphics2D g){
        bg.draw(g);
        monster.draw(g);
        heart.draw(g);
        g.drawImage(shield, 110, 600, 50, 50, null);
        g.setFont(font);
        g.setColor(new Color(67, 97, 158));
        g.drawString("Press ESC to join MENU", 30, 30);

        Font font = new Font("UAV OSD Mono", Font.PLAIN, 15);
        g.setFont(font);
        g.setColor(Color.WHITE);

        g.drawString("// RULES:", 100, 130);

        g.drawString("the sense of \"pixel gravity\" is to beat your", 100, 170);
        g.drawString("score record by crossing the map and getting scores.", 100, 190);

        g.drawString("you will meet obstacles and monsters on your way", 100, 230);
        g.drawString("so you mustnt touch this obstacles cause you will", 100, 250);
        g.drawString("die immediately and try to not been attacked by the monsters", 100, 270);
        g.drawString("cause it takes 1 heart of you (you have only 3), but.", 100, 290);
        g.drawString("it gives you shield for 4 seconds while that you cant die.", 100, 310);
        g.drawString("your goal is trying to stay alive as long as you can.", 100, 330);

        g.drawString("how to play? you must type the space button to", 100, 370);
        g.drawString("change the gravity and by this way evading the obstacles.", 100, 390);
        g.drawString("// Its a fireguy. he is enemy. he will attack you", 190, 435);
        g.drawString("when you appear on his level (take 1 heart).", 190, 455);
        g.setColor(Color.RED);
        g.drawString("be careful!", 190, 475);
        g.setColor(Color.WHITE);
        g.drawString("// its a diamond of life. it can refill the one heart.", 190, 525);
        g.drawString("this guy will save your life.", 190, 545);
        g.setColor(Color.GREEN);
        g.drawString("dont miss it!", 190, 565);
        g.setColor(Color.WHITE);
        g.drawString("// its a shield form. you get this when", 190, 615);
        g.drawString("you will be attacked by a fireguy. it lasts 4 seconds.", 190, 635);
        g.drawString("you lose 1 life but you cant die during the form.", 190, 655);
    }

    public void keyPressed(int k){
        if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.MENUSTATE);
    };
    public void keyReleased(int k){};
}
