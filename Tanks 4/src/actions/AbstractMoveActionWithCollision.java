package actions;

import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.util.EnumSet;

/**
 * Используется для проверки перемещения по карте. Перемещение невозможно, если
 * встречается препятствие.
 *
 * @author alex
 */
public abstract class AbstractMoveActionWithCollision implements MoveAction {

    protected int dLeftX;
    protected int dTopY;
    protected final EnumSet<Material> impassable;

    public AbstractMoveActionWithCollision(EnumSet<Material> impassable) {
        this.impassable = impassable;
    }

    @Override
    public abstract boolean move(Movable movable, GeometryMap map);

    @Override
    public boolean canMove(Movable movable, GeometryMap map) {
        int speed = movable.getSpeed();
        calculateDesirePosition(movable, speed);
        int dRightX = dLeftX + movable.getWidth();
        int dDownY = dTopY + movable.getHeight();
        if (intoMap(dRightX, dDownY, map)) {
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
    protected void calculateDesirePosition(Movable movable, int speed) {
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
    protected boolean detectCollisions(GeometryMap map, int dRightX, int dDownY) {
        int tileSize = map.getTileSize();
        for (int x = dLeftX; x < dRightX;) {
            for (int y = dTopY; y < dDownY;) {
                if (impassable.contains(map.getTile(x, y))) {
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
        return dLeftX < 0 || dRightX >= map.getWidth()
                || dTopY < 0 || dDownY >= map.getHeight();
    }

}
