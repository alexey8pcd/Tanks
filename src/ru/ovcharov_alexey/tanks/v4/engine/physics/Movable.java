package ru.ovcharov_alexey.tanks.v4.engine.physics;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;

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

    Vector2D getDirection();

    void setDirection(Vector2D direction);
}
