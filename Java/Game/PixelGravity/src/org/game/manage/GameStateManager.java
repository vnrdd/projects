package org.game.manage;
import org.game.view.*;
import java.util.ArrayList;

public class GameStateManager {
    private final ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int HELPSTATE = 1;
    public static final int PLAYSTATE = 2;
    public static final int CHOOSETYPESTATE = 3;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new HelpState(this));
        gameStates.add(new PlayState(this));
        gameStates.add(new ChooseTypeState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init(0);
    }

    public void setState(int state, int diff, String filename) {
        currentState = state;
        gameStates.get(currentState).init(diff, filename);
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }
}
