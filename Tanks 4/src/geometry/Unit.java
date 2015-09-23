package geometry;

import battle.Attackable;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Unit extends GeometryShape implements Movable, Attackable {

    private int moveSpeed;
    private Direction direction;

    public Unit(int x, int y, int width, int height, int moveSpeed) {
        super(x, y, width, height);
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean move(GeometryMap map) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
