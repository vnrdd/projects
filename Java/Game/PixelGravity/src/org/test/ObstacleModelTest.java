package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleModelTest {

    @Test
    void getX() {
        ObstacleModel model = new ObstacleModel(50, 50, 100, 100);
        int actual = model.getX();
        int expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        ObstacleModel model = new ObstacleModel(50, 50, 100, 100);
        int actual = model.getY();
        int expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        ObstacleModel model = new ObstacleModel(50, 50, 100, 100);
        int actual = model.getHeight();
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        ObstacleModel model = new ObstacleModel(50, 50, 100, 100);
        int actual = model.getWidth();
        int expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        ObstacleModel model = new ObstacleModel(0, 0, 50, 50);
        Rectangle actual = new Rectangle(0, 0, 50, 50);
        Rectangle expected = model.getHitBox();
        assertEquals(expected, actual);
    }

    @Test
    void intersect() {
        ObstacleModel model = new ObstacleModel(0, 0, 50, 50);
        PlayerModel player = new PlayerModel(300, 300, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}