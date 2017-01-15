package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * Используется для проверки перемещения по карте. Перемещение невозможно, если
 * встречается препятствие.
 *
 * @author alex
 */
public abstract class AbstractMoveActionWithCollision implements MoveAction {

    protected float dLeftX;
    protected float dTopY;
    protected boolean slowMove;
    protected final EnumSet<Material> impassable;

    public AbstractMoveActionWithCollision(EnumSet<Material> impassable) {
        this.impassable = impassable;
    }

    @Override
    public void addImpassible(Material material) {
        impassable.add(material);
    }

    @Override
    public void removeImpassible(Material material) {
        impassable.remove(material);
    }

    @Override
    public abstract boolean move(Movable movable, GeometryMap map, GeometryPoint target);

    @Override
    public boolean canMove(Movable movable, GeometryMap map, GeometryPoint target) {
        calculateDesirePosition(movable);
        float dRightX = dLeftX + movable.getWidth();
        float dDownY = dTopY + movable.getHeight();
        if (!intoMap(Math.round(dRightX), Math.round(dDownY), map)) {
            return false;
        }
        return !detectCollisions(map, dRightX, dDownY);
    }

    /**
     * Вычислить желаемую позицию. Результат сохраняется в полях dLeftX и dTopY
     *
     * @param movable
     */
    protected void calculateDesirePosition(Movable movable) {
        dLeftX = movable.getX();
        dTopY = movable.getY();
        dLeftX += movable.getDirection().getI();
        dTopY -= movable.getDirection().getJ();
    }

    /**
     * Проверяет столкновение с объектами карты
     *
     * @param map
     * @param dRightX
     * @param dDownY
     * @return true, если обнаружено столкновение, false иначе
     */
    protected boolean detectCollisions(GeometryMap map, float dRightX, float dDownY) {
        int tileSize = map.getTileSize();
        slowMove = false;
        for (float x = dLeftX; x < dRightX;) {
            for (float y = dTopY; y < dDownY;) {
                Material material = map.getTile((int) x, (int) y);
                if (material == Material.ICE) {
                    slowMove = true;
                }
                if (impassable.contains(material)) {
                    return true;
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
        return false;
    }

    /**
     * Проверяет, что желаемая позиция находится в пределах карты
     *
     * @param dRightX
     * @param map
     * @param dDownY
     * @return
     */
    protected boolean intoMap(int dRightX, int dDownY, GeometryMap map) {
        return !(dLeftX < 0 || dRightX >= map.getWidth()
                || dTopY < 0 || dDownY >= map.getHeight());
    }

}
