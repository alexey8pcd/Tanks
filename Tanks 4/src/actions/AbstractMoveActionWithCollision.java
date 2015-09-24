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
        //вычислить желаемую позицию
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
            default:
                throw new AssertionError(movable.getDirection().name());
        }
        int dRightX = dLeftX + movable.getWidth();
        int dDownY = dTopY + movable.getHeight();
        //проверить, что желаемая позиция находится в пределах карты и        
        if (dLeftX < 0 || dRightX >= map.getWidth()
                || dTopY < 0 || dDownY >= map.getHeight()) {
            return false;
        }
        //на пути нет препятствий
        int tileSize = map.getTileSize();
        for (int x = dLeftX; x < dRightX;) {
            for (int y = dTopY; y < dDownY; ) {
                if (impassable.contains(map.getTile(x, y))) {
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
        return true;
    }

}
