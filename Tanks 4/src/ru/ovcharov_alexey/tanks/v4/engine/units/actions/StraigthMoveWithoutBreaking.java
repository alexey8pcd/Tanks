package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * Перемещение прямо в заданном направлении.
 *
 * @author alex
 */
public class StraigthMoveWithoutBreaking extends AbstractMoveActionWithCollision {

    public StraigthMoveWithoutBreaking(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map) {
        if (canMove(movable, map)) {
            if (!slowMove) {
                movable.setLocation(dLeftX, dTopY);
            } else {
                if (movable.getSpeed() > 1) {
                    int x = movable.getX();
                    int y = movable.getY();
                    x += (dLeftX - x) / 2;
                    y += (dTopY - y) / 2;
                    movable.setLocation(x, y);
                } else {
                    movable.setLocation(dLeftX, dTopY);
                }

            }
            return true;
        }
        return false;
    }

}
