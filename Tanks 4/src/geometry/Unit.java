package geometry;

import actions.MoveAction;
import battle.Attackable;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Unit extends GeometryShape implements Movable, Attackable {

    private int moveSpeed;
    private Direction direction;
    private final MoveAction moveAction;

    public Unit(int x, int y, int size,
            int moveSpeed, MoveAction moveAction) {
        super(x, y, size);
        this.moveSpeed = moveSpeed;
        this.moveAction = moveAction;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean move(GeometryMap map) {
        return moveAction.move(this, map);
    }

    @Override
    public int getSpeed() {
        return moveSpeed;
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
         this.direction = direction;
     }

    @Override
    public void setSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void setLocation(int x, int y) {
        this.point.x = x;
        this.point.y = y;
    }

    @Override
    public boolean canMove(GeometryMap map) {
        return moveAction.canMove(this, map);
    }

}
