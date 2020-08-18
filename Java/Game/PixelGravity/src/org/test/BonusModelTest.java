package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BonusModelTest {

    @Test
    void intersect() {
        BonusModel model = new BonusModel(0, 0, 40, 40);
        PlayerModel player = new PlayerModel(0, 0, 40, 40, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        BonusModel model = new BonusModel(0, 0, 40, 40);
        int actual = model.getX();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        BonusModel model = new BonusModel(0, 0, 40, 40);
        int actual = model.getY();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        BonusModel model = new BonusModel(0, 0, 40, 40);
        int actual = model.getHeight();
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        BonusModel model = new BonusModel(0, 0, 40, 40);
        int actual = model.getWidth();
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        BonusModel model = new BonusModel(0, 0, 40 ,40);
        Rectangle actual = model.getHitBox();
        Rectangle expected = new Rectangle(0, 0, 40, 40);
        assertEquals(actual, expected);
    }
}