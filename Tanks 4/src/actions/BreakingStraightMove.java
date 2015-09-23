package actions;

import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.util.EnumSet;

/**
 *
 * @author alex
 */
public class BreakingStraightMove extends AbstractMoveActionWithCollision {

    public BreakingStraightMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
