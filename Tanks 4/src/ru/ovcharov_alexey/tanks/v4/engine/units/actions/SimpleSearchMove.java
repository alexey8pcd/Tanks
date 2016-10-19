package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.EnumSet;
import java.util.Random;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * @author Alexey
 */
public class SimpleSearchMove extends AbstractMoveActionWithCollision {

    private Random random = new Random();
    private static final int CHANCE_CHANGE_DIRECTION = 12;
    
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
        }
        updateDirection(target, movable);
        return false;
    }

    private void updateDirection(GeometryPoint target, Movable movable) {
        if (random.nextInt(100) < CHANCE_CHANGE_DIRECTION) {
            float dx = target.getX() - movable.getX();
            float dy = target.getY() - movable.getY();
            if (dx >= 0 && dy >= 0) {
                movable.setDirection(random.nextBoolean() ? Direction.RIGHT : Direction.DOWN);
            } else if (dx >= 0 && dy < 0) {
                movable.setDirection(random.nextBoolean() ? Direction.RIGHT : Direction.UP);
            } else if (dx < 0 && dy > 0) {
                movable.setDirection(random.nextBoolean() ? Direction.LEFT : Direction.DOWN);
            } else {
                movable.setDirection(random.nextBoolean() ? Direction.LEFT : Direction.UP);
            }
        }

    }
    

}
