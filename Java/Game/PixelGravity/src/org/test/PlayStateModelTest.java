package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayStateModelTest {

    @Test
    void getCurDiff() {
        PlayStateModel model = new PlayStateModel(null);
        model.setCurDiff(1);
        int actual = model.getCurDiff();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getWalls() {
        PlayStateModel model = new PlayStateModel(null);
        model.getWalls().clear();
        model.makeWalls(-150);
        int actual = model.getWalls().size();
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    void getObs() {
        PlayStateModel model = new PlayStateModel(null);
        model.getObs().clear();
        model.makeObs(150);
        int actual = model.getObs().size();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void getR() {
        PlayStateModel model = new PlayStateModel(null);
        model.getR().clear();
        model.makeRz(150);
        int actual = model.getR().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getB() {
        PlayStateModel model = new PlayStateModel(null);
        model.getB().clear();
        model.makeB(150);
        int actual = model.getB().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getD() {
        PlayStateModel model = new PlayStateModel(null);
        model.getD().clear();
        model.makeD(150);
        int actual = model.getD().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getF() {
        PlayStateModel model = new PlayStateModel(null);
        model.getF().clear();
        model.makeF(150);
        int actual = model.getF().size();
        int expected = 1;
        assertEquals(expected, actual);
    }
}