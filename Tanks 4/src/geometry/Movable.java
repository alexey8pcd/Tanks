package geometry;

/**
 *
 * @author alex
 */
public interface Movable {

    public enum Direction {

        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public boolean move(GeometryMap map);

    public int getSpeed();

    public void setSpeed(int moveSpeed);

    public void setLocation(int x, int y);
    
    public int getX();
    
    public int getY();

    public Direction getDirection();

    public void setDirection(Direction direction);
}
