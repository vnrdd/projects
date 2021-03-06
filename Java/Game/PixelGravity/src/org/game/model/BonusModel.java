package org.game.model;

import java.awt.*;

public class BonusModel{
    int x;
    int y;
    int height;
    int width;
    Rectangle hitBox;
    int startX;

    public BonusModel(int x, int y, int width, int height){
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
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public int getStartX(){return startX;}
    public Rectangle getHitBox(){return hitBox;}

    public void set(int cameraX){
        x = startX - cameraX;
        hitBox.x = x;
    }
}