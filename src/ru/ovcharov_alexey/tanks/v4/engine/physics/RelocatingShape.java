package ru.ovcharov_alexey.tanks.v4.engine.physics;

import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;

/**
 *
 * @author alex
 */
public abstract class RelocatingShape extends GeometryShape implements Movable {

    protected float speed;
    private Vector2D direction;
    protected MoveAction moveAction;

    public RelocatingShape(float speed, float x, float y,
            int width, int height, Direction direction, MoveAction moveAction) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = Vector2D.create(direction, speed);
        this.moveAction = moveAction;
    }

    public RelocatingShape(float speed, float x, float y, int size,
            Direction direction, MoveAction moveAction) {
        super(x, y, size);
        this.speed = speed;
        this.direction = Vector2D.create(direction, speed);
        this.moveAction = moveAction;
    }

    public RelocatingShape(float speed, MoveAction moveAction, int size) {
        super(size);
        this.speed = speed;
        this.direction = Vector2D.NULL;
        this.moveAction = moveAction;
    }

    @Override
    public boolean move(GeometryMap map, GeometryPoint point) {
        return moveAction.move(this, map, point);
    }

    @Override
    public boolean canMove(GeometryMap map, GeometryPoint point) {
        return moveAction.canMove(this, map, point);
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float moveSpeed) {
        this.speed = moveSpeed;
    }

    @Override
    public void setLocation(float x, float y) {
        setX(x);
        setY(y);
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    @Override
    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

}
