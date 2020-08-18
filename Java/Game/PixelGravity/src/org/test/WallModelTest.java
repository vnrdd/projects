package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallModelTest {

    @Test
    void getX() {
        WallModel model = new WallModel(10, 10, 300 ,150);
        int actual = model.getX();
        int expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        WallModel model = new WallModel(15, 15, 300 ,150);
        int actual = model.getY();
        int expected = 15;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        WallModel model = new WallModel(15, 15, 300 ,150);
        int actual = model.getHeight();
        int expected = 150;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        WallModel model = new WallModel(15, 15, 300 ,150);
        int actual = model.getWidth();
        int expected = 300;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        WallModel model = new WallModel(15, 15, 300 ,150);
        Rectangle actual = model.getHitBox();
        Rectangle expected = new Rectangle(15, 15, 300, 150);
        assertEquals(actual, expected);
    }

    @Test
    void intersect() {
        WallModel model = new WallModel(15, 15, 300 ,150);
        PlayerModel player = new PlayerModel(15, 15, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }
}