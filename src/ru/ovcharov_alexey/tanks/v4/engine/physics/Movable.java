package ru.ovcharov_alexey.tanks.v4.engine.physics;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;

/**
 *
 * @author alex
 */
public interface Movable extends Shape {
    
    boolean canRandomChangeDirection();

    boolean move(GeometryPoint point, Scene scene);

    boolean canMove(GeometryPoint point, Scene scene);

    float getSpeed();

    void setSpeed(float moveSpeed);

    void setLocation(float x, float y);

    Vector2D getDirection();

    void setDirection(Vector2D direction);

    default void randomDirection() {
        Vector2D vector2D = Direction.random(getSpeed());
        setDirection(vector2D);
    }
}
