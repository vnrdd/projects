package org.editor.model;

import java.awt.*;

public class BonusModel {
    int x;
    int y;
    int height;
    int width;
    int shift;
    Rectangle hitBox;
    boolean placed;
    int startX;

    public BonusModel(int x, int y){
        this.x = x;
        startX = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
        hitBox = new Rectangle(x, y, width, height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getShift(){return shift;}

    public void set(int cameraX){
        x = startX - cameraX;
        hitBox.x = x;
    }
}
