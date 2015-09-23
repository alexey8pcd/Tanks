package geometry;

/**
 *
 * @author alex
 */
public interface Movable {

    public enum Direction {

        LEFT(Axis.X),
        RIGHT(Axis.X),
        UP(Axis.Y),
        DOWN(Axis.Y);

        private final Axis axis;

        private enum Axis {

            X,
            Y
        };

        private Direction(Axis axis) {
            this.axis = axis;
        }

        public boolean isOrtho(Direction direction) {
            return direction.axis == axis;
        }
    }

    public boolean move(GeometryMap map);
    
    public boolean canMove(GeometryMap map);

    public int getSpeed();

    public void setSpeed(int moveSpeed);

    public void setLocation(int x, int y);

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public Direction getDirection();

    public void setDirection(Direction direction);
}
