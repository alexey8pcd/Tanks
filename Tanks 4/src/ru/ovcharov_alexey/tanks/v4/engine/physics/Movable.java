package ru.ovcharov_alexey.tanks.v4.engine.physics;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;

/**
 *
 * @author alex
 */
public interface Movable extends Shape {

    boolean move(GeometryMap map, GeometryPoint point);

    boolean canMove(GeometryMap map, GeometryPoint point);

    float getSpeed();

    void setSpeed(float moveSpeed);

    void setLocation(float x, float y);

    Direction getDirection();

    void setDirection(Direction direction);
}
