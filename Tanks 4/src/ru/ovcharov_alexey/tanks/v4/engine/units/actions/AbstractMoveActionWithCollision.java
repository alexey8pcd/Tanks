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
    public abstract boolean move(Movable movable, GeometryMap map, GeometryPoint target);

    @Override
    public boolean canMove(Movable movable, GeometryMap map, GeometryPoint target) {
        float speed = movable.getSpeed();
        calculateDesirePosition(movable, speed);
        float dRightX = dLeftX + movable.getWidth();
        float dDownY = dTopY + movable.getHeight();
        if (!intoMap((int) dRightX, (int) dDownY, map)) {
            return false;
        }
        return !detectCollisions(map, dRightX, dDownY);
    }

    /**
     * Вычислить желаемую позицию. Результат сохраняется в полях dLeftX и dTopY
     *
     * @param movable
     * @param speed
     */
    protected void calculateDesirePosition(Movable movable, float speed) {
        dLeftX = movable.getX();
        dTopY = movable.getY();
        switch (movable.getDirection()) {
            case LEFT:
                dLeftX -= speed;
                break;
            case RIGHT:
                dLeftX += speed;
                break;
            case UP:
                dTopY -= speed;
                break;
            case DOWN:
                dTopY += speed;
                break;
        }
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
