package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireballModelTest {

    @Test
    void intersect() {
        FireballModel model = new FireballModel(0, 0, 50, 30);
        PlayerModel player = new PlayerModel(0, 0, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        FireballModel model = new FireballModel(0, 0, 50, 30);
        int actual = model.getX();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        FireballModel model = new FireballModel(0, 0, 50, 30);
        int actual = model.getY();
        int expected = 0;
        assertEquals(expected, actual);
    }
}