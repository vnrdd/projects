package org.editor.view;
import org.editor.manage.*;

import java.awt.*;

public abstract class State {
    protected EditorStateManager esm;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);
}
