package org.game.model;
import org.game.view.*;
import org.game.manage.*;
import org.game.files.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PlayStateModel {
    GameStateManager gsm;
    Player player;
    Finish finish;
    public int finishX = 0;
    ArrayList<Wall> walls;
    ArrayList<Obstacle> obs;
    ArrayList<RedZone> redz;
    ArrayList<Diamond> dmds;
    ArrayList<Bonus> bs;
    ArrayList<Fireguy> fgs;
    ArrayList<Wall> wtr;
    ArrayList<Obstacle> otr;
    ArrayList<RedZone> rztr;
    ArrayList<Diamond> dtr;
    ArrayList<Bonus> btr;
    ArrayList<Fireguy> ftr;
    boolean preset = false;
    int cameraX;
    int offsetWalls;
    int offsetObs;
    int offsetRz;
    int offsetD;
    int offsetB;
    int offsetF;
    public String fileToRead = "";
    boolean paused;
    boolean loaded = false;

    final String[] options = {"Retry", "Menu"};
    int currentChoice = 0;
    int curDiff = -2;

    public PlayStateModel(GameStateManager gsm){
        this.gsm = gsm;
        cameraX = 150;
        finish = new Finish(0, 0);
        walls = new ArrayList<>();
        obs = new ArrayList<>();
        redz = new ArrayList<>();
        dmds = new ArrayList<>();
        bs = new ArrayList<>();
        fgs = new ArrayList<>();
        wtr = new ArrayList<>();
        otr = new ArrayList<>();
        rztr = new ArrayList<>();
        dtr = new ArrayList<>();
        btr = new ArrayList<>();
        ftr = new ArrayList<>();
        offsetWalls = -150;
        makeWalls(offsetWalls);
        offsetObs = 1500;
        offsetRz = 1600;
        offsetD = 3000;
        offsetB = 2000;
        offsetF = 4900;
        makeObs(offsetObs);
        makeRz(offsetRz);
        makeD(offsetD);
        makeF(offsetF);
        makeB(offsetB);
        makeF(offsetF);
        player = new Player(200, 400, this);
    }

    public int getCurDiff(){return curDiff;}
    public String[] getOptions(){return options;}
    public Player getPlayer(){return player;}
    public Finish getFinish(){return finish;}
    public int getCurrentChoice(){return currentChoice;}
    public boolean getPaused(){return paused;}
    public void setCurDiff(int num){curDiff = num;}

    public ArrayList<Wall> getWalls(){return walls;}
    public ArrayList<Obstacle> getObs(){ return obs; }
    public ArrayList<RedZone> getR(){ return redz; }
    public ArrayList<Bonus> getB(){ return bs; }
    public ArrayList<Diamond> getD(){ return dmds; }
    public ArrayList<Fireguy> getF(){ return fgs; }

    public void update() {
        if ((curDiff == -1 && !loaded)) {
            readMap();
            loaded = true;
        }

        if (walls.size() != 0) {
            if (walls.get(walls.size() - 1).getModel().x < 750) {
                offsetWalls += 720;
                makeWalls(offsetWalls);
            }
        }

        if (curDiff == 0) generateElements();
        playerManage();
        setAll();
        removeExtras();
    }

    public void readMap(){
        obs.clear();
        dmds.clear();
        redz.clear();
        bs.clear();
        fgs.clear();
        try {
            FileWorker.readFile(this); // catch in view and print message
        } catch (Exception e) {
            e.printStackTrace();
        }
        loaded = true;
        finish.getModel().x = finishX + 800;
        finish.getModel().startX = finishX + 800;
    }

    public void generateElements(){
        if (obs.size() != 0) {
            if (obs.get(obs.size() - 1).getModel().x < 750) {
                offsetObs += 600;
                makeObs(offsetObs);
            }
        }

        if (redz.size() != 0) {
            if (redz.get(redz.size() - 1).getModel().x < 750) {
                offsetRz += 600;
                makeRz(offsetRz);
            }
        }
        if (dmds.size() != 0) {
            if (dmds.get(dmds.size() - 1).getModel().x < 750) {
                offsetD += 3000;
                makeD(offsetD);
            }
        }
        if (bs.size() != 0) {
            if (bs.get(bs.size() - 1).getModel().x < 750) {
                offsetB += 2000;
                makeB(offsetB);
            }
        }
        if (fgs.size() != 0) {
            if (fgs.get(fgs.size() - 1).getModel().x < 750) {
                offsetF += 2400;
                makeF(offsetF);
            }
        }
    }

    public void makeWalls(int offset){
        for(int i = 0; i < 3; ++i){
            walls.add(new Wall(offset + i*300, 580, 300, 150));
            walls.add(new Wall(offset + i*300, -10, 300, 150));
        }
    }

    public void makeObs(int offset){
        Random rand = new Random();
        int spec = rand.nextInt(3);
        if(spec  == 0) obs.add(new Obstacle(offset, 90));
        if(spec == 1) obs.add(new Obstacle(offset, 310));
        if(spec == 2) obs.add(new Obstacle(offset, 530));

        int spec2 = rand.nextInt(3);
        if(spec2  == 0) obs.add(new Obstacle(offset, 90));
        if(spec2 == 1) obs.add(new Obstacle(offset, 310));
        if(spec2 == 2) obs.add(new Obstacle(offset, 530));
    }

    public void makeRz(int offset){
        if(redz.size() == 0) {
            redz.add(new RedZone(offset, -10));
            return;
        }
        Random rand = new Random();
        int spec = rand.nextInt(3);
        if (spec == 0) {
            redz.add(new RedZone(offset, 580));
        }
        if (spec == 1) {
            redz.add(new RedZone(offset, -10));
        }
    }

    public void makeD(int offset){
        Random rand = new Random();
        int vert = rand.nextInt(300) + 210;
        dmds.add(new Diamond(offset, vert));
    }

    public void makeB(int offset){
        Random rand = new Random();
        int vert = rand.nextInt(300) + 210;
        bs.add(new Bonus(offset, vert));
    }

    public void makeF(int offset){
        Random rand = new Random();
        int upDown = rand.nextInt(2);
        if(upDown == 0) fgs.add(new Fireguy(offset, 490, this));
        else fgs.add(new Fireguy(offset, 140, this));
    }

    private void playerManage(){
        if(player.getModel().keySpace) {
            ++player.getModel().yspeed;
            ++player.getModel().hitBox.y;
            for (int i = 0; i < walls.size(); ++i) {
                if (walls.get(i).getModel().intersect(player.getModel())) {
                    player.getModel().yspeed = -1;
                }
            }
            --player.getModel().hitBox.y;
        }
        if(!player.getModel().keySpace) {
            --player.getModel().yspeed;
            --player.getModel().hitBox.y;
            for (int i = 0; i < walls.size(); ++i) {
                if (walls.get(i).getModel().intersect(player.getModel())) {
                    player.getModel().yspeed = 1;
                }
            }
            ++player.getModel().hitBox.y;
        }

        for(int i = 0; i < redz.size(); ++i){
            --player.getModel().hitBox.y;
            if(redz.get(i).getModel().intersect(player.getModel()) && player.getModel().shield == 0) player.getModel().lives = 0;
            ++player.getModel().hitBox.y;
            ++player.getModel().hitBox.y;
            if(redz.get(i).getModel().intersect(player.getModel()) && player.getModel().shield == 0) player.getModel().lives = 0;
            --player.getModel().hitBox.y;
        }

        for(int i = 0; i < obs.size(); ++i){
            if(obs.get(i).getModel().intersect(player.getModel()) && player.getModel().shield == 0) player.getModel().lives = 0;
        }

        if(finish.getModel().intersect(player.getModel()) && curDiff == -1) player.getModel().lives = 0;

        for(int i = 0; i < bs.size(); ++i){
            if(bs.get(i).getModel().intersect(player.getModel())){
                player.getModel().scores += 1000;
                player.getModel().bsCount++;
                player.getModel().bToRemove = i;
                break;
            }
            player.getModel().bToRemove = -1;
        } if(player.getModel().bToRemove >= 0 && bs.size() != 0) bs.remove(player.getModel().bToRemove);

        for(int i = 0; i < dmds.size(); ++i){
            if(dmds.get(i).getModel().intersect(player.getModel())){
                player.getModel().dToRemove = i;
                break;
            }
            player.getModel().dToRemove = -1;
            if (player.getModel().lives > 3 ) player.getModel().lives = 3;
        } if(player.getModel().dToRemove >= 0 && player.getModel().lives < 3) {
            player.getModel().dmdsCount++;
            dmds.remove(player.getModel().dToRemove);
            player.getModel().lives++;
        }

        for(int i = 0; i < fgs.size(); ++i){
            if(fgs.get(i).getModel().intersect(player.getModel()) || fgs.get(i).getModel().fireball.getModel().intersect(player.getModel())){
                if(player.getModel().shield == 0){
                    player.getModel().lives--;
                    player.getModel().shield = player.getModel().scores;
                    player.getModel().shieldStart = System.currentTimeMillis();
                    fgs.get(i).getModel().fireball.getModel().flying = false;
                }
                player.getModel().xspeed = player.getModel().shieldXspeed;
            }
        }

        if(System.currentTimeMillis() - player.getModel().shieldStart > 4000) {
            player.getModel().shield = 0;
            player.getModel().xspeed = player.getModel().normalXspeed;
        }
        player.getModel().set();
    }

    public void reset(){
        player.getModel().dmdsCount = 0;
        preset = false;
        player.getModel().bsCount = 0;
        player.getModel().keySpace = true;
        player.getModel().shield = 0;
        player.getModel().lives = 3;
        player.getModel().xspeed = 8;
        player.getModel().scores = 0;
        player.getModel().x = 200;
        player.getModel().y = 400;
        cameraX = 150;
        offsetWalls = -150;
        offsetObs = 1500;
        offsetRz = 1600;
        offsetD = 3000;
        offsetB = 2000;
        offsetF = 4900;
        walls.clear();
        obs.clear();
        redz.clear();
        dmds.clear();
        bs.clear();
        fgs.clear();
        fileToRead = "";
        makeWalls(offsetWalls);
        makeRz(offsetRz);
        makeObs(offsetObs);
        makeD(offsetD);
        makeB(offsetB);
        makeF(offsetF);
        loaded = false;
    }

    private void removeExtras(){
        for(int i = 0; i < walls.size(); ++i){
            if(walls.get(i).getModel().x < -300) wtr.add(walls.get(i));
        } walls.removeAll(wtr); wtr.clear();
        for(int i = 0; i < obs.size(); ++i){
            if(obs.get(i).getModel().x < -300) otr.add(obs.get(i));
        } obs.removeAll(otr); otr.clear();
        for(int i = 0; i < redz.size(); ++i){
            if(redz.get(i).getModel().x < -300) rztr.add(redz.get(i));
        } redz.removeAll(rztr); rztr.clear();
        for(int i = 0; i < dmds.size(); ++i){
            if(dmds.get(i).getModel().x < -300) dtr.add(dmds.get(i));
        } dmds.removeAll(dtr); dtr.clear();
        for(int i = 0; i < bs.size(); ++i){
            if(bs.get(i).getModel().x < -300) btr.add(bs.get(i));
        } bs.removeAll(btr); btr.clear();
        for(int i = 0; i < fgs.size(); ++i){
            if(fgs.get(i).getModel().x < -300) ftr.add(fgs.get(i));
        } fgs.removeAll(ftr); ftr.clear();
    }

    private void setAll(){
        for(int i = 0; i < walls.size(); ++i) walls.get(i).getModel().set(cameraX);
        for(int i = 0; i < redz.size(); ++i) redz.get(i).getModel().set(cameraX);
        for(int i = 0; i < obs.size(); ++i) obs.get(i).getModel().set(cameraX);
        for(int i = 0; i < dmds.size(); ++i) dmds.get(i).getModel().set(cameraX);
        for(int i = 0; i < bs.size(); ++i) bs.get(i).getModel().set(cameraX);
        for(int i = 0; i < fgs.size(); ++i) fgs.get(i).getModel().set(cameraX);
        finish.getModel().set(cameraX);
    }

    public void checkKeys(int k){
        if(k == KeyEvent.VK_SPACE) player.getModel().changeGravity();
        if (k == KeyEvent.VK_UP) {
            --currentChoice;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            ++currentChoice;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
        if(k == KeyEvent.VK_ENTER) select();
        if(k == KeyEvent.VK_ESCAPE) {
            player.getModel().xspeed = 0;
            paused = true;
            player.getModel().pausedY = player.getModel().y;
        }
        if(k == KeyEvent.VK_C) {
            paused = false;
        }

        if(k == KeyEvent.VK_Q){
            reset();
            gsm.setState(GameStateManager.MENUSTATE);
            reset();
            paused = false;
        }
    }

    public void select() {
        if (currentChoice == 0) {
            if(curDiff == 0) reset();
            gsm.setState(GameStateManager.PLAYSTATE, curDiff, fileToRead);
            if(curDiff == 0) reset();
        }
        if (currentChoice == 1) {
            reset();
            gsm.setState(GameStateManager.MENUSTATE);
            reset();
        }
    }
}
