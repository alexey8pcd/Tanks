package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Killable;

/**
 * @author Alexey
 */
public class Explosion extends GeometryShape implements Drawable, Killable {

    public static final int SIZE = 15;
    private static final EnumMap<State, Image> images = new EnumMap<>(State.class);

    public static void init() throws IOException {
        for (State state : State.values()) {
            if (state != State.TERMINATE) {
                String name = "/images/explosions/" + state.getImageName();
                images.put(state, ImageIO.read(Explosion.class.getResourceAsStream(name)));
            }
        }
    }

    public Explosion(GeometryShape geometryShape) {
        super((int) (geometryShape.getX() - SIZE / 2),
                (int) (geometryShape.getY() - SIZE / 2), SIZE);
        state = State.NEW;
    }

    @Override
    public void draw(Graphics2D g) {
        if (state != null && state != State.TERMINATE) {
            g.drawImage(images.get(state), (int) getX(), (int) getY(),
                    getWidth(), getHeight(), null);
        }
    }

    @Override
    public boolean isVisible() {
        return isLive();
    }

    @Override
    public boolean isLive() {
        return state != State.TERMINATE;
    }

    @Override
    public void kill() {
        state = State.TERMINATE;
    }

    @Override
    public boolean isDead() {
        return state == State.TERMINATE;
    }

    @Override
    public void restore() {
        state = State.NEW;
    }

    private enum State {
        NEW("s0.png"),
        INCREASE("s1.png"),
        MAX("s2.png"),
        DECREASE("s3.png"),
        TERMINATE("");

        public String getImageName() {
            return imageName;
        }

        private String imageName;

        private State(String name) {
            this.imageName = name;
        }

        public State next() {
            switch (this) {
                case NEW:
                    return INCREASE;
                case INCREASE:
                    return MAX;
                case MAX:
                    return DECREASE;
                case DECREASE:
                    return TERMINATE;
            }
            return TERMINATE;
        }
    }

    private State state;

    public Explosion(float x, float y) {
        super(x, y, SIZE);
        state = State.NEW;
    }

    public void next() {
        this.state = state.next();
    }

}
