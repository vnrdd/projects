package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireguyModelTest {

    @Test
    void intersect() {
        FireguyModel model = new FireguyModel(20, 20, 80, 80, null);
        PlayerModel player = new PlayerModel(-100, 20, 50, 50, null);
        boolean actual = model.intersect(player);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        FireguyModel model = new FireguyModel(20, 20, 80, 80, null);
        int actual = model.getX();
        int expected = 20;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        FireguyModel model = new FireguyModel(20, 20, 80, 80, null);
        int actual = model.getY();
        int expected = 20;
        assertEquals(expected, actual);
    }

    @Test
    void tryToShoot() {
        FireguyModel model = new FireguyModel(20, 20, 80, 80, null);
        PlayerModel player = new PlayerModel(-100, 20, 50, 50, null);
        boolean actual = model.tryToShoot(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }
}