package org.game.view;
import org.game.model.*;
import org.game.manage.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayState extends GameState {
    PlayStateModel psm;
    Background bg;
    BufferedImage gameOverBg;

    public PlayState(GameStateManager gsm) {
        psm = new PlayStateModel(gsm);
        this.gsm = gsm;
        try {
            bg = new Background("/bg.png");
            gameOverBg = ImageIO.read(getClass().getResourceAsStream("/transpbg.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedImage gameOverBg;
    }

    public void init(int flag) { }

    public void init(int flag, String filename) {
        psm.reset();
        psm.fileToRead = filename;
        psm.setCurDiff(flag);
    };

    public void update() {
        psm.update();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        psm.getPlayer().draw(g);
        if(psm.getCurDiff() == -1) psm.getFinish().draw(g);
        drawTools(g);
        if(psm.getPlayer().m.getShield() != 0) shieldInfo(g);
        playerLivesOutput(g);
        if(psm.getPaused()) pauseInfo(g);
    }

    private void drawTools(Graphics2D g){
        for(int i = 0; i < psm.getWalls().size(); ++i) psm.getWalls().get(i).draw(g);
        for(int i = 0; i < psm.getR().size(); ++i) psm.getR().get(i).draw(g);
        for(int i = 0; i < psm.getB().size(); ++i) psm.getB().get(i).draw(g);
        for(int i = 0; i < psm.getD().size(); ++i)psm.getD().get(i).draw(g);
        for(int i = 0; i < psm.getObs().size(); ++i) psm.getObs().get(i).draw(g);
        for(int i = 0; i < psm.getF().size(); ++i) psm.getF().get(i).draw(g);
    }

    private void pauseInfo(Graphics2D g){
        g.drawImage(gameOverBg, 0, 0, null);
        Font font = new Font("Minecraft Rus", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("GAME PAUSED", 372, 302);
        g.setColor(Color.WHITE);
        g.drawString("GAME PAUSED", 370, 300);
        g.setColor(Color.GREEN);
        font = new Font("UAV OSD Mono", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("Press 'c' to unpause", 360, 350);
        g.drawString("Press 'q' to join menu", 350, 370);
    }

    private void shieldInfo(Graphics2D g){
        Font font = new Font("Minecraft Rus", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.GREEN);
        if((psm.getPlayer().m.getShield() + 1000 - psm.getPlayer().m.getScores())/300 <= 1) g.setColor(Color.RED);
        g.drawString("SHIELD ON: " + (psm.getPlayer().m.getShieldStart() + 4000 - System.currentTimeMillis())/1000 + "s", 400, 60);
        g.setColor(Color.RED);
        font = new Font("Minecraft Rus", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("but you got -1 live :(", 400, 80);
    }

    private void playerLivesOutput(Graphics2D g){
        Font font = new Font("Minecraft Rus", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + String.valueOf(psm.getPlayer().m.getScores()), 30, 50);
        if(psm.getPlayer().m.getLives() == 3) g.drawImage(psm.getPlayer().l3, 30, 70, 120, 34, null);
        if(psm.getPlayer().m.getLives() == 2) g.drawImage(psm.getPlayer().l2, 30, 70, 120, 34, null);
        if(psm.getPlayer().m.getLives() == 1) g.drawImage(psm.getPlayer().l1, 30, 70, 120, 34, null);
        if(psm.getPlayer().m.getLives() == 0) {
            g.drawImage(psm.getPlayer().l0, 30, 70, 120, 34, null);
            psm.getPlayer().m.setLives(3);
            gameOver(g);
        }
    }

    private void gameOver(Graphics2D g){
        g.drawImage(gameOverBg, 0, 0, null);
        g.setColor(Color.WHITE);
        Font font = new Font("Minecraft Rus", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("GAME OVER !", 392, 252);
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER !", 390, 250);

        font = new Font("Minecraft Rus", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString("Your score: ", 360, 340);
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(psm.getPlayer().m.getScores()), 560, 340);

        font = new Font("Minecraft Rus", Font.PLAIN, 15);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Diamonds collected: ", 390, 380);
        g.drawString("+1000s collected: ", 390, 400);
        g.setColor(Color.ORANGE);
        g.drawString(String.valueOf(psm.getPlayer().m.getDCount()), 580, 380);
        g.drawString(String.valueOf(psm.getPlayer().m.getBCount()), 580, 400);

        font = new Font("Minecraft Rus", Font.PLAIN, 25);
        g.setFont(font);
        for (int i = 0; i < psm.getOptions().length; ++i) {
            if (i == psm.getCurrentChoice()) {
                g.setColor(new Color(67, 97, 158));
            } else g.setColor(Color.WHITE);
            g.drawString(psm.getOptions()[i], 480 - psm.getOptions()[i].length()*7, 520 + i * 40);
        }
    }

    public void keyPressed(int k) { psm.checkKeys(k); }
    public void keyReleased(int k) {}
}
