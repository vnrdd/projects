package org.editor.manage;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class EditorPanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private EditorStateManager esm;

    public EditorPanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;

        esm = new EditorStateManager();
    }

    public void run() {
        init();
        while (running) {

            update();
            draw();
            drawToScreen();

            try {
                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        esm.update();
    }

    private void draw() {
        esm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        esm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        esm.keyReleased(key.getKeyCode());
    }
}
