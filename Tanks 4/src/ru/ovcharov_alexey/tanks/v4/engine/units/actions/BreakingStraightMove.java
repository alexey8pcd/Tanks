package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Liveable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Breaking;

/**
 *
 * @author alex
 */
public class BreakingStraightMove extends AbstractMoveActionWithCollision {

    public BreakingStraightMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map, GeometryPoint point) {
        calculateDesirePosition(movable, movable.getSpeed());
        float dRightX = dLeftX + movable.getWidth();
        float dDownY = dTopY + movable.getHeight();
        if (intoMap((int) dRightX, (int) dDownY, map)) {
            int tileSize = map.getTileSize();
            for (float x = dLeftX; x < dRightX;) {
                for (float y = dTopY; y < dDownY;) {
                    Material material = map.getTile((int) x, (int) y);
                    if (impassable.contains(material)) {
                        Breaking breaking = (Breaking) movable;
                        if (breaking.getBreakingStrength().isBreak(material)) {
                            map.setTile((int) x, (int) y, Material.TERRA);
                            breaking.setLive(false);
                        }
                        return false;
                    }
                    if (y >= dDownY - tileSize) {
                        ++y;
                    } else {
                        y += tileSize;
                    }
                }
                if (x >= dRightX - tileSize) {
                    ++x;
                } else {
                    x += tileSize;
                }
            }
            movable.setLocation(dLeftX, dTopY);
            return true;
        }
        ((Liveable) movable).setLive(false);
        return false;
    }

}
