package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.EnumSet;
import static ru.ovcharov_alexey.tanks.v4.engine.Global.RANDOM;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
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
    public boolean move(Movable movable, GeometryPoint target, Scene scene) {
        float speed = movable.getSpeed();
        Direction optimal = getOptimal(target, movable);
        Direction notOptimal = getNotOptimal(target, movable);
        movable.setDirection(optimal.toVector(speed));
        if (!canMove(movable, target, scene)) {
            movable.setDirection(notOptimal.toVector(speed));
        }
        if (canMove(movable, target, scene)) {
            if (!slowMove) {
                movable.setLocation(dLeftX, dTopY);
            } else {
                float x = movable.getX();
                float y = movable.getY();
                x += (dLeftX - x) / 2;
                y += (dTopY - y) / 2;
                movable.setLocation(x, y);
            }
            if (RANDOM.nextInt(100) < CHANCE_CHANGE_DIRECTION) {
                movable.setDirection(Direction.random(speed));
            }
            return true;
        }
        return false;
    }

    private enum Zone {
        LEFT_TOP,
        RIGHT_TOP,
        LEFT_DOWN,
        RIGHT_DOWN
    }

    private Zone getZone(GeometryPoint target, Movable movable) {
        float dx = target.getX() - movable.getX();
        float dy = target.getY() - movable.getY();
        if (dx >= 0 && dy >= 0) {
            return Zone.RIGHT_DOWN;
        } else if (dx >= 0 && dy < 0) {
            return Zone.RIGHT_TOP;
        } else if (dx < 0 && dy > 0) {
            return Zone.LEFT_DOWN;
        } else {
            return Zone.LEFT_TOP;
        }
    }

    private Direction getOptimal(GeometryPoint target, Movable movable) {
        float dx = target.getX() - movable.getX();
        float dy = target.getY() - movable.getY();
        if (dx >= 0 && dy >= 0) {
            return dx > dy ? Direction.RIGHT : Direction.DOWN;
        } else if (dx >= 0 && dy < 0) {
            return dx > -dy ? Direction.RIGHT : Direction.UP;
        } else if (dx < 0 && dy > 0) {
            return -dx > dy ? Direction.LEFT : Direction.DOWN;
        } else {
            return -dx > -dy ? Direction.LEFT : Direction.UP;
        }
    }

    private Direction getNotOptimal(GeometryPoint target, Movable movable) {
        float dx = target.getX() - movable.getX();
        float dy = target.getY() - movable.getY();
        if (dx >= 0 && dy >= 0) {
            return dx <= dy ? Direction.RIGHT : Direction.DOWN;
        } else if (dx >= 0 && dy < 0) {
            return dx <= -dy ? Direction.RIGHT : Direction.UP;
        } else if (dx < 0 && dy > 0) {
            return -dx <= dy ? Direction.LEFT : Direction.DOWN;
        } else {
            return -dx <= -dy ? Direction.LEFT : Direction.UP;
        }
    }

}
