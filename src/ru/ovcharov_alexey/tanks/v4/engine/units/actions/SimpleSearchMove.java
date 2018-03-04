package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.EnumSet;
import java.util.Random;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import static ru.ovcharov_alexey.tanks.v4.engine.Global.RANDOM;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * @author Alexey
 */
public class SimpleSearchMove extends AbstractMoveActionWithCollision {

    private static final int CHANCE_CHANGE_DIRECTION = 5;

    public SimpleSearchMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map, GeometryPoint target) {
        if (canMove(movable, map, target)) {
            if (!slowMove) {
                movable.setLocation(dLeftX, dTopY);
            } else {
                float x = movable.getX();
                float y = movable.getY();
                x += (dLeftX - x) / 2;
                y += (dTopY - y) / 2;
                movable.setLocation(x, y);
            }
            updateDirection(target, movable);
            return true;
        } else {
            updateDirection(target, movable);
            return false;
        }
    }

    private void updateDirection(GeometryPoint target, Movable movable) {
        if (RANDOM.nextInt(100) < CHANCE_CHANGE_DIRECTION) {
            float dx = target.getX() - movable.getX();
            float dy = target.getY() - movable.getY();
            boolean nextBoolean = RANDOM.nextBoolean();
            float speed = movable.getSpeed();
            if (dx >= 0 && dy >= 0) {
                if (nextBoolean) {
                    movable.setDirection(new Vector2D(speed, 0));
                } else {
                    movable.setDirection(new Vector2D(0, -speed));
                }
            } else if (dx >= 0 && dy < 0) {
                if (nextBoolean) {
                    movable.setDirection(new Vector2D(speed, 0));
                } else {
                    movable.setDirection(new Vector2D(0, speed));
                }
            } else if (dx < 0 && dy > 0) {
                if (nextBoolean) {
                    movable.setDirection(new Vector2D(-speed, 0));
                } else {
                    movable.setDirection(new Vector2D(0, speed));
                }
            } else if (nextBoolean) {
                movable.setDirection(new Vector2D(-speed, 0));
            } else {
                movable.setDirection(new Vector2D(0, -speed));
            }
        }
    }

}
