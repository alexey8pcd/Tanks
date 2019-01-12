package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Breaking;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Killable;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;

/**
 *
 * @author alex
 */
public class BreakingStraightMove extends AbstractMoveActionWithCollision {

    public BreakingStraightMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryPoint point, Scene scene) {
        calculateDesirePosition(movable);
        float dRightX = dLeftX + movable.getWidth();
        float dDownY = dTopY + movable.getHeight();
        Shape shape = new GeometryShape(dLeftX, dTopY, movable.getWidth(), movable.getHeight());
        if (scene.into(shape)) {
            GeometryMap map = scene.getGeometryMap();
            int tileSize = map.getTileSize();
            for (float x = dLeftX; x < dRightX;) {
                for (float y = dTopY; y < dDownY;) {
                    Material material = map.getTile((int) x, (int) y);
                    if (impassable.contains(material)) {
                        Breaking breaking = (Breaking) movable;
                        if (breaking.getBreakingStrength().isBreak(material)) {
                            map.setTile((int) x, (int) y, Material.TERRA);
                            int x1, x2, y1, y2;
                            Direction direction = Direction.approximate(movable.getDirection());
                            if (direction == Direction.LEFT
                                    || direction == Direction.RIGHT) {
                                x1 = (int) x;
                                x2 = (int) x;
                                y1 = (int) (y + map.getTileSize());
                                y2 = (int) (y - map.getTileSize());
                            } else {
                                x1 = (int) (x + map.getTileSize());
                                x2 = (int) (x - map.getTileSize());
                                y1 = (int) y;
                                y2 = (int) y;
                            }
                            if (impassable.contains(map.getTile(x1, y1))) {
                                map.setTile(x1, y1, Material.TERRA);
                            }
                            if (impassable.contains(map.getTile(x2, y2))) {
                                map.setTile(x2, y2, Material.TERRA);
                            }
                            breaking.kill();
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
            float dSpeed = (float)(movable.getSpeed() * movable.getSpeed() / 2 * Global.SPEED_SLOW_COEFF);
            movable.setSpeed(movable.getSpeed() - dSpeed);//скорость уменьшается со временем
            return true;
        }
        ((Killable) movable).kill();
        return false;
    }

}
