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
        //на пути нет препятствий
        if (dLeftX < 0 || dRightX >= map.getWidth()
                || dTopY < 0 || dDownY >= map.getHeight()) {
            return false;
        }
        return (!impassable.contains(map.getTile(dLeftX, dTopY))
                && !impassable.contains(map.getTile(dRightX, dTopY))
                && !impassable.contains(map.getTile(dLeftX, dDownY))
                && !impassable.contains(map.getTile(dRightX, dDownY)));
    }

}
