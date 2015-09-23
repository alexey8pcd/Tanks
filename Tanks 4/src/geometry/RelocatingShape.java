package geometry;

import actions.MoveAction;

/**
 *
 * @author alex
 */
public abstract class RelocatingShape extends GeometryShape implements Movable {

    protected int speed;
    private Direction direction;
    protected final MoveAction moveAction;

    public RelocatingShape(int speed, int x, int y,
            int width, int height, Direction direction, MoveAction moveAction) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = direction;
        this.moveAction = moveAction;
    }

    public RelocatingShape(int speed, int x, int y, int size,
            Direction direction, MoveAction moveAction) {
        super(x, y, size);
        this.speed = speed;
        this.direction = direction;
        this.moveAction = moveAction;
    }

    @Override
    public boolean move(GeometryMap map){
        return moveAction.move(this, map);
    }

    @Override
    public boolean canMove(GeometryMap map){
        return moveAction.canMove(this, map);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int moveSpeed) {
        this.speed = moveSpeed;
    }

    @Override
    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
