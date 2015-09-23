package actions;

import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.util.EnumSet;

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
            movable.setLocation(dLeftX, dTopY);
            return true;
        }
        return false;
    }

}
