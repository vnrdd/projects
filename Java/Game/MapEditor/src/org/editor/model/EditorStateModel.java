package org.editor.model;
import org.editor.view.*;
import org.editor.manage.*;
import org.editor.files.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EditorStateModel {
    ArrayList<Wall> walls;
    ArrayList<Wall> wtr;
    ArrayList<Obstacle> obs;
    ArrayList<Fireguy> fgs;
    ArrayList<RedZone> rzs;
    ArrayList<Diamond> ds;
    ArrayList<Bonus> bs;
    EditorStateManager esm;
    public String fileToWrite = "";
    int offsetWalls = -150;
    int currentChoice;
    int cameraX;
    int xspeed;
    int shift;
    int placeYspeed = 0;
    int placeXspeed = 0;

    public EditorStateModel(EditorStateManager esm){
        currentChoice = -1;
        shift = 0;
        cameraX = 150;
        walls = new ArrayList<>();
        wtr = new ArrayList<>();
        obs = new ArrayList<>();
        rzs = new ArrayList<>();
        ds = new ArrayList<>();
        bs = new ArrayList<>();
        fgs = new ArrayList<>();
        makeWalls(offsetWalls);
        this.esm = esm;
    }

    public ArrayList<Wall> getWalls(){return walls;}

    public ArrayList<Obstacle> getObs(){
        return obs;
    }

    public ArrayList<RedZone> getR(){
        return rzs;
    }

    public ArrayList<Bonus> getB(){
        return bs;
    }

    public ArrayList<Diamond> getD(){
        return ds;
    }

    public ArrayList<Fireguy> getF(){
        return fgs;
    }

    public int getCurrentChoice(){return currentChoice;}

    public void update(){
        shift += xspeed;
        cameraX += xspeed;
        if(walls.size() != 0) {
            if (walls.get(walls.size() - 1).getModel().getX() < 750) {
                offsetWalls += 720;
                makeWalls(offsetWalls);
            }
        }

        for(int i = 0; i < walls.size(); ++i) walls.get(i).getModel().set(cameraX);
        for(int i = 0; i < obs.size(); ++i) {
            if(obs.get(i).getModel().placed) obs.get(i).getModel().set(cameraX);
        }
        for(int i = 0; i < rzs.size(); ++i) {
            if(rzs.get(i).getModel().placed) rzs.get(i).getModel().set(cameraX);
        }
        for(int i = 0; i < ds.size(); ++i) {
            if(ds.get(i).getModel().placed) ds.get(i).getModel().set(cameraX);
        }
        for(int i = 0; i < bs.size(); ++i) {
            if(bs.get(i).getModel().placed) bs.get(i).getModel().set(cameraX);
        }
        for(int i = 0; i < fgs.size(); ++i) {
            if(fgs.get(i).getModel().placed) fgs.get(i).getModel().set(cameraX);
        }


        if(currentChoice == 0) {
            if (obs.size() != 0) {
                if (!obs.get(obs.size() - 1).getModel().placed) {
                    obs.get(obs.size() - 1).getModel().x += placeXspeed;
                    obs.get(obs.size() - 1).getModel().hitBox.x += placeXspeed;
                    obs.get(obs.size() - 1).getModel().startX += placeXspeed;
                    obs.get(obs.size() - 1).getModel().y += placeYspeed;
                    obs.get(obs.size() - 1).getModel().hitBox.y += placeYspeed;
                }
            }
        }

        if(currentChoice == 1) {
            if (rzs.size() != 0) {
                if (!rzs.get(rzs.size() - 1).getModel().placed) {
                    rzs.get(rzs.size() - 1).getModel().x += placeXspeed;
                    rzs.get(rzs.size() - 1).getModel().hitBox.x += placeXspeed;
                    rzs.get(rzs.size() - 1).getModel().startX += placeXspeed;
                    rzs.get(rzs.size() - 1).getModel().y += placeYspeed;
                    rzs.get(rzs.size() - 1).getModel().hitBox.y += placeYspeed;
                }
            }
        }

        if(currentChoice == 2) {
            if (fgs.size() != 0) {
                if (!fgs.get(fgs.size() - 1).getModel().placed) {
                    fgs.get(fgs.size() - 1).getModel().x += placeXspeed;
                    fgs.get(fgs.size() - 1).getModel().hitBox.x += placeXspeed;
                    fgs.get(fgs.size() - 1).getModel().startX += placeXspeed;
                    fgs.get(fgs.size() - 1).getModel().y += placeYspeed;
                    fgs.get(fgs.size() - 1).getModel().hitBox.y += placeYspeed;
                }
            }
        }

        if(currentChoice == 3) {
            if (bs.size() != 0) {
                if (!bs.get(bs.size() - 1).getModel().placed) {
                    bs.get(bs.size() - 1).getModel().x += placeXspeed;
                    bs.get(bs.size() - 1).getModel().hitBox.x += placeXspeed;
                    bs.get(bs.size() - 1).getModel().startX += placeXspeed;
                    bs.get(bs.size() - 1).getModel().y += placeYspeed;
                    bs.get(bs.size() - 1).getModel().hitBox.y += placeYspeed;
                }
            }
        }

        if(currentChoice == 4) {
            if (ds.size() != 0) {
                if (!ds.get(ds.size() - 1).getModel().placed) {
                    ds.get(ds.size() - 1).getModel().x += placeXspeed;
                    ds.get(ds.size() - 1).getModel().hitBox.x += placeXspeed;
                    ds.get(ds.size() - 1).getModel().startX += placeXspeed;
                    ds.get(ds.size() - 1).getModel().y += placeYspeed;
                    ds.get(ds.size() - 1).getModel().hitBox.y += placeYspeed;
                }
            }
        }
    }

    public void makeWalls(int offset){
        for(int i = 0; i < 3; ++i){
            walls.add(new Wall(offset + i*300, 580, 300, 150));
            walls.add(new Wall(offset + i*300, -10, 300, 150));
        }
    }

    public void keyPressedCheck(int k){
        if(k == KeyEvent.VK_F){
            try{
                FileWorker.saveToFile(this);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(k == KeyEvent.VK_ESCAPE) {
            reset();
            esm.setState(EditorStateManager.MENUSTATE);
        }
        if(k == KeyEvent.VK_RIGHT)
            xspeed = 4;
        if(k == KeyEvent.VK_LEFT){
            if(walls.get(0).getModel().x > -10) {
                xspeed = 0;
            }
            else xspeed = -4;
        }
        if(k == KeyEvent.VK_0) {
            currentChoice = 0;
            obs.add(new Obstacle(500, 300));
        }
        if(k == KeyEvent.VK_1) {
            currentChoice = 1;
            rzs.add(new RedZone(500, 300));
        }
        if(k == KeyEvent.VK_2) {
            currentChoice = 2;
            fgs.add(new Fireguy(500, 300));
        }
        if(k == KeyEvent.VK_3) {
            currentChoice = 3;
            bs.add(new Bonus(500, 300));
        }
        if(k == KeyEvent.VK_4) {
            currentChoice = 4;
            ds.add(new Diamond(500, 300));
        }

        if(k == KeyEvent.VK_W) placeYspeed = -2;
        if(k == KeyEvent.VK_A) placeXspeed = -2;
        if(k == KeyEvent.VK_S) placeYspeed = 2;
        if(k == KeyEvent.VK_D) placeXspeed = 2;

        if(k == KeyEvent.VK_ENTER) {
            if(currentChoice == 0 && !obs.get(obs.size()-1).getModel().placed) {
                if(obs.size() != 0) {
                    obs.get(obs.size()-1).getModel().x += cameraX;
                    obs.get(obs.size()-1).getModel().startX += cameraX;
                    obs.get(obs.size()-1).getModel().placed = true;
                    obs.get(obs.size()-1).getModel().shift = shift;
                }
            }
            if(currentChoice == 1 && !rzs.get(rzs.size()-1).getModel().placed){
                if(rzs.size() != 0) {
                    rzs.get(rzs.size()-1).getModel().x += cameraX;
                    rzs.get(rzs.size()-1).getModel().startX += cameraX;
                    rzs.get(rzs.size()-1).getModel().placed = true;
                    rzs.get(rzs.size()-1).getModel().shift = shift;
                }
            }
            if(currentChoice == 2 && !fgs.get(fgs.size()-1).getModel().placed){
                if(fgs.size() != 0) {
                    fgs.get(fgs.size()-1).getModel().x += cameraX;
                    fgs.get(fgs.size()-1).getModel().startX += cameraX;
                    fgs.get(fgs.size()-1).getModel().placed = true;
                    fgs.get(fgs.size()-1).getModel().shift = shift;
                }
            }
            if(currentChoice == 3 && !bs.get(bs.size()-1).getModel().placed){
                if(bs.size() != 0) {
                    bs.get(bs.size()-1).getModel().x += cameraX;
                    bs.get(bs.size()-1).getModel().startX += cameraX;
                    bs.get(bs.size()-1).getModel().placed = true;
                    bs.get(bs.size()-1).getModel().shift = shift;
                }
            }
            if(currentChoice == 4 && !ds.get(ds.size()-1).getModel().placed){
                if(ds.size() != 0) {
                    ds.get(ds.size()-1).getModel().x += cameraX;
                    ds.get(ds.size()-1).getModel().startX += cameraX;
                    ds.get(ds.size()-1).getModel().placed = true;
                    ds.get(ds.size()-1).getModel().shift = shift;
                }
            }
            currentChoice = -1;
        }
    }

    public void keyReleasedCheck(int k){
        if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_LEFT)
            xspeed = 0;
        if(k == KeyEvent.VK_W) placeYspeed = 0;
        if(k == KeyEvent.VK_A) placeXspeed = 0;
        if(k == KeyEvent.VK_S) placeYspeed = 0;
        if(k == KeyEvent.VK_D) placeXspeed = 0;
    }

    public void reset(){
        placeXspeed = 0;
        placeYspeed = 0;
        shift = 0;
        xspeed = 0;
        walls.clear();
        obs.clear();
        ds.clear();
        bs.clear();
        rzs.clear();
        fgs.clear();
        currentChoice = -1;
        offsetWalls = -150;
        makeWalls(offsetWalls);
    }
}
