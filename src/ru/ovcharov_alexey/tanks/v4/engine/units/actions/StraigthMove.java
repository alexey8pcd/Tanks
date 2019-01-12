package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * Перемещение прямо в заданном направлении.
 *
 * @author alex
 */
public class StraigthMove extends AbstractMoveActionWithCollision {

    private static final int CHANCE_CHANGE_DIRECTION = 5;

    public StraigthMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryPoint target, Scene scene) {
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
            if (movable.canRandomChangeDirection() && 
                    Global.RANDOM.nextInt(100) < CHANCE_CHANGE_DIRECTION) {
                movable.randomDirection();
            }
            return true;
        }
        return false;
    }

}
