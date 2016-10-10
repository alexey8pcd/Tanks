package ru.ovcharov_alexey.tanks.v4.engine.physics;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;

/**
 *
 * @author alex
 */
public interface Movable extends Shape {

    boolean move(GeometryMap map);

    boolean canMove(GeometryMap map);

    int getSpeed();

    void setSpeed(int moveSpeed);

    void setLocation(int x, int y);

    Direction getDirection();

    void setDirection(Direction direction);
}
