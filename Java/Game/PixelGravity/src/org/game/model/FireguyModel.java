package org.game.model;
import org.game.view.*;
import java.awt.*;

public class FireguyModel{
    int x;
    int y;
    int height;
    int width;
    Rectangle hitBox;
    int startX;
    boolean shooted;
    int visionZone = 600;
    PlayStateModel ps;
    Fireball fireball;
    int xspeed;

    public FireguyModel(int x, int y, int width, int height, PlayStateModel ps){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        startX = x;
        hitBox = new Rectangle(x, y, width, height);
        this.ps = ps;
        shooted = false;
        xspeed = 10;
        fireball = new Fireball(x - 50, 520, 50, 30);
    }

    public boolean intersect(PlayerModel p){
        return hitBox.intersects(p.hitBox);
    }

    public Fireball getBullet(){return fireball;}
    public int getX(){return x;}
    public int getY(){return y;}

    public void shoot(){
        if(y > 460) fireball.getModel().y = 535;
        else {
            fireball.getModel().y = 150;
            fireball.getModel().hitBox.y = fireball.getModel().y;
        }
        fireball.getModel().x = x - 50;
        fireball.getModel().flying = true;
    }

    public boolean tryToShoot(PlayerModel p){
        hitBox.x -= visionZone;
        hitBox.width += visionZone;
        if(hitBox.intersects(p.hitBox)){
            if(!shooted){
                shoot();
                shooted = true;
            }
        } else shooted = false;
        hitBox.x += visionZone;
        hitBox.width -= visionZone;
        return shooted;
    }

    public void set(int cameraX){
        tryToShoot(ps.player.getModel());

        x = startX - cameraX;
        if(fireball.getModel().flying) fireball.getModel().set();
        hitBox.x = x;
    }
}