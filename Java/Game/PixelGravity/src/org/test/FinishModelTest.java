package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FinishModelTest {

    @Test
    void intersect() {
        FinishModel model = new FinishModel(0, 0, 85, 720);
        PlayerModel player = new PlayerModel(0, 0, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        FinishModel model = new FinishModel(0, 0, 85, 720);
        int actual = model.getX();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        FinishModel model = new FinishModel(0, 0, 85, 720);
        int actual = model.getY();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        FinishModel model = new FinishModel(0, 0, 85, 720);
        int actual = model.getHeight();
        int expected = 720;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        FinishModel model = new FinishModel(0, 0, 85, 720);
        int actual = model.getWidth();
        int expected = 85;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        FinishModel model = new FinishModel(0, 0, 85 ,720);
        Rectangle actual = model.getHitBox();
        Rectangle expected = new Rectangle(0, 0, 85, 720);
        assertEquals(actual, expected);
    }
}