package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
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
     * @param target - целя для перемещения
     * @param scene
     * @return true, если удалось переместиться, false, если координаты не
     * изменились
     */
    boolean move(Movable movable, GeometryPoint target, Scene scene);

    boolean canMove(Movable movable, GeometryPoint target, Scene scene);
    
    void addImpassible(Material material);
    
    void removeImpassible(Material material);

}
