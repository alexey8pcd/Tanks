package geometry;

/**
 *
 * @author alex
 */
public interface Movable {

    public enum Direction {

        LEFT(Axis.X, 0),
        UP(Axis.Y, 1),
        RIGHT(Axis.X, 2),        
        DOWN(Axis.Y, 3);

        private final Axis axis;
        private final int index;

        private enum Axis {

            X,
            Y
        };

        private Direction(Axis axis, int index) {
            this.axis = axis;
            this.index = index;
        }

        public boolean isOrtho(Direction direction) {
            return direction.axis == axis;
        }

        public int getIndex() {
            return index;
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
