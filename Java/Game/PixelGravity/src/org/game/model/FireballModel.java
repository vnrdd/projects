package org.game.model;

import java.awt.*;

public class FireballModel{
    int x;
    int y;
    int height;
    int width;
    Rectangle hitBox;
    int startX;
    int xspeed = 12;
    boolean flying = false;

    public FireballModel(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        startX = x;
        hitBox = new Rectangle(x, y, width, height);
    }

    public boolean intersect(PlayerModel p){
        return hitBox.intersects(p.hitBox);
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public boolean isFlying(){return flying;}
    public void set(){
        x -= xspeed;
        hitBox.x = x;
    }
}