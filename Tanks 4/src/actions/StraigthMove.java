package actions;

import geometry.GeometryMap;
import geometry.Movable;

/**
 * Перемещение прямо в заданном направлении.
 *
 * @author alex
 */
public class StraigthMove implements MoveAction {

    @Override
    public boolean move(Movable movable, GeometryMap map) {
        int speed = movable.getSpeed();
        //вычислить желаемую позицию
        int dx = movable.getX();
        int dy = movable.getY();
        switch (movable.getDirection()) {
            case LEFT:
                dx -= speed;
            case RIGHT:
                dx += speed;
                break;
            case UP:
                dy -= speed;
                break;
            case DOWN:
                dy += speed;
                break;
            default:
                throw new AssertionError(movable.getDirection().name());
        }
        //проверить, что желаемая позиция находится в пределах карты и
        //на пути нет препятствий
        if (dx < 0 || dx >= map.getWidth() || dy < 0 || dy >= map.getHeight()) {
            return false;
        }
        if (map.getTile(dx, dy) != GeometryMap.Material.TERRA) {
            return false;
        }
        movable.setLocation(dx, dy);
        return true;
    }

}
