package org.editor.manage;
import org.editor.view.*;
import java.util.ArrayList;

public class EditorStateManager {
    private final ArrayList<State> editorStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int EDITORSTATE = 1;

    public EditorStateManager() {
        editorStates = new ArrayList<State>();
        currentState = MENUSTATE;
        editorStates.add(new MenuState(this));
        editorStates.add(new EditorState(this));
    }

    public void setState(int state) {
        currentState = state;
        editorStates.get(currentState).init();
    }

    public void update() {
        editorStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        editorStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        editorStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        editorStates.get(currentState).keyReleased(k);
    }
}

