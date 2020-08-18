package org.test;

import org.game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DiamondModelTest {

    @Test
    void intersect() {
        DiamondModel model = new DiamondModel(0, 0, 40, 40);
        PlayerModel player = new PlayerModel(0, 0, 40, 40, null);
        boolean actual = model.intersect(player);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getX() {
        DiamondModel model = new DiamondModel(0, 0, 40, 40);
        int actual = model.getX();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        DiamondModel model = new DiamondModel(0, 0, 40, 40);
        int actual = model.getY();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getHeight() {
        DiamondModel model = new DiamondModel(0, 0, 40, 40);
        int actual = model.getHeight();
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    void getWidth() {
        DiamondModel model = new DiamondModel(0, 0, 40, 40);
        int actual = model.getWidth();
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    void getHitBox() {
        DiamondModel model = new DiamondModel(0, 0, 40 ,40);
        Rectangle actual = model.getHitBox();
        Rectangle expected = new Rectangle(0, 0, 40, 40);
        assertEquals(actual, expected);
    }
}