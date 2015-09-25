package actions;

import geometry.GeometryMap;
import geometry.Movable;
import java.util.EnumSet;

/**
 *
 * @author alex
 */
public interface MoveAction {
    
    public static final MoveAction UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING
            = new StraigthMoveWithoutBreaking(
                    EnumSet.of(GeometryMap.Material.ARMOR,
                            GeometryMap.Material.BRICK,
                            GeometryMap.Material.WATER)
            );

    /**
     * Выполняет перемещение заданного объекта, используя карту
     *
     * @param movable - объект, который совершает перемещение
     * @param map - карта для определения столкновений
     * @return true, если удалось переместиться, false, если координаты не
     * изменились
     */
    public boolean move(Movable movable, GeometryMap map);

    public boolean canMove(Movable movable, GeometryMap map);

}
