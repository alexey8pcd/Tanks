package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 *
 * @author alex
 */
public interface MoveAction {
    
    /**
     * Выполняет перемещение заданного объекта, используя карту
     *
     * @param movable - объект, который совершает перемещение
     * @param map - карта для определения столкновений
     * @param target - целя для перемещения
     * @return true, если удалось переместиться, false, если координаты не
     * изменились
     */
    boolean move(Movable movable, GeometryMap map, GeometryPoint target);

    boolean canMove(Movable movable, GeometryMap map, GeometryPoint target);
    
    void addImpassible(Material material);
    
    void removeImpassible(Material material);

}
