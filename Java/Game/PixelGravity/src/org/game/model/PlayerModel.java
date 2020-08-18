package org.game.model;

import java.awt.*;

public class PlayerModel {
    int dToRemove = -1;
    int bToRemove = -1;
    int shield = 0;
    long shieldStart;
    int dmdsCount = 0;
    int bsCount = 0;
    int scores = 0;
    int lives = 3;
    int x;
    int y;
    int width;
    int height;
    int pausedY;
    double xspeed = 8;
    double normalXspeed = 8;
    double shieldXspeed = 5;
    double yspeed;
    Rectangle hitBox;
    PlayStateModel ps;
    boolean keySpace;

    public PlayerModel(int x, int y, int width, int height, PlayStateModel ps){
        keySpace = true;
        this.ps = ps;
        this.x = x;
        this.y = y;
        this.yspeed = 0;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x, y, width, height);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getLives(){return lives;}
    public int getShield(){return shield;}
    public boolean getKeySpace(){return keySpace;}
    public int getScores(){return scores;}
    public long getShieldStart(){return shieldStart;}
    public void setLives(int num){lives = num;}
    public int getDCount(){return dmdsCount;}
    public int getBCount(){return bsCount;}

    public void changeGravity(){
        keySpace = !keySpace;
    }

    public Rectangle getHitbox(){
        return hitBox;
    }

    public void set(){
        if(lives < 0) lives = 0;

        if(yspeed > 0 && yspeed < 0.75) yspeed = 0;
        if(yspeed < 0 && yspeed > -0.75) yspeed = 0;

        if(lives != 0 && !ps.paused) {
            scores += (int) xspeed - 2;
            if (scores > 10 && xspeed != 3) scores -= 1;
        }

        if(lives == 0) {
            xspeed = 0;
            yspeed = 0;
            shield = 0;
        }

        if(yspeed > 10) yspeed = 10;
        if(yspeed < -10) yspeed = -10;

        if(!ps.paused) {
            ps.cameraX += xspeed;
            y += yspeed;
            if (y > 530) y = 530;
            if (y < 140) y = 140;
            hitBox.y = y;
        }
    }
}
