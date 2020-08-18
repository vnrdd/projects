package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {

    @Test
    void getX() {
        PlayerModel model = new PlayerModel(0, 0, 50, 50, null);
        int actual = model.getX();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        PlayerModel model = new PlayerModel(0, 0, 50, 50, null);
        int actual = model.getY();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getLives() {
        PlayerModel model = new PlayerModel(0, 0, 50, 50, null);
        model.setLives(2);
        int actual = model.getLives();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void getHitbox() {
        PlayerModel model = new PlayerModel(0, 0, 50, 50, null);
        Rectangle actual = new Rectangle(0, 0, 50, 50);
        Rectangle expected = model.getHitbox();
        assertEquals(expected, actual);
    }
}