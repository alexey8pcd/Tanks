package ru.ovcharov_alexey.tanks.v4.engine;

/**
 * @author Alexey
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

public abstract class Surface extends Canvas implements Runnable {

    private boolean running;
    private final int width;
    private final int height;
    private final int delay;
    private final Map<Integer, Boolean> keys;

    public Surface(int width, int height, int framesPerSecond) {
        this.width = width;
        this.height = height;
        if (framesPerSecond < 1) {
            framesPerSecond = 60;
        }
        if (framesPerSecond > 100) {
            framesPerSecond = 60;
        }
        running = false;
        this.delay = 1000 / framesPerSecond;
        keys = new HashMap<>();
        setKeys();
    }

    private void setKeys() {
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                keys.replace(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keys.replace(e.getKeyCode(), false);
            }
        });
    }

    public void addKey(int keyCode) {
        keys.put(keyCode, false);
    }

    public void remove(int keyCode) {
        keys.remove(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return keys.get(keyCode);
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        int time = 0;
        while (running) {
            tryCheckKeys(time);
            update();
            tryRender(time);
            time = flowTime(time);
        }
    }

    private int flowTime(int time) {
        int longDelay = delay * 4;
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            running = false;
        }
        if (time > longDelay) {
            time = 0;
        }
        ++time;
        return time;
    }

    private void tryRender(int time) {
        if (time % delay == 0) {
            render();
        }
    }

    private void tryCheckKeys(int time) {
        final int keyCheckDelay = delay * 2;
        if (time % keyCheckDelay == 0) {
            checkKeys();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        display(g);
        g.dispose();
        bs.show();
    }

    public abstract void checkKeys();

    public abstract void update();

    public abstract void display(Graphics g);

}
