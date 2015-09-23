package actions;

import geometry.GeometryMap;
import geometry.Movable;

/**
 *
 * @author alex
 */
public interface MoveAction {
    
    /**
     * Выполняет перемещение заданного объекта, используя карту
     * @param movable - объект, который совершает перемещение
     * @param map - карта для определения столкновений
     * @return true, если удалось переместиться, 
     *  false, если координаты не изменились
     */
    public boolean move(Movable movable, GeometryMap map);

}
