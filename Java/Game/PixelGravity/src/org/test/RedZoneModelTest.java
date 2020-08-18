package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RedZoneModelTest {

    @Test
    void intersect() {
        RedZoneModel model = new RedZoneModel(20, 20, 200, 150);
        PlayerModel player = new PlayerModel(20, 20, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        RedZoneModel model = new RedZoneModel(20, 20, 200, 150);
        int actual = model.getX();
        int expected = 20;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        RedZoneModel model = new RedZoneModel(20, 20, 200, 150);
        int actual = model.getY();
        int expected = 20;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        RedZoneModel model = new RedZoneModel(20, 20, 200, 150);
        int actual = model.getHeight();
        int expected = 150;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        RedZoneModel model = new RedZoneModel(20, 20, 200, 150);
        int actual = model.getWidth();
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        RedZoneModel model = new RedZoneModel(15, 15, 200 ,150);
        Rectangle actual = model.getHitBox();
        Rectangle expected = new Rectangle(15, 15, 200, 150);
        assertEquals(actual, expected);
    }
}