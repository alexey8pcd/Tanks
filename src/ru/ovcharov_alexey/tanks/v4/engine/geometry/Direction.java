package ru.ovcharov_alexey.tanks.v4.engine.geometry;

/**
 * @author Alexey
 */
public enum Direction {

    LEFT(Axis.X, 0),
    UP(Axis.Y, 1),
    RIGHT(Axis.X, 2),
    DOWN(Axis.Y, 3);
    private static final double PI_4 = Math.PI / 4;
    private static final double PI_43 = Math.PI / 4 * 3;

    public static Direction approximate(Vector2D direction) {
        double alpha = Math.atan2(direction.getJ(), direction.getI());
        if (alpha <= PI_4 && alpha >= -Math.PI / 4) {
            return RIGHT;
        } else if (alpha > PI_4 && alpha <= PI_43) {
            return UP;
        } else if (alpha > PI_43 || alpha < -PI_43) {
            return LEFT;
        } else {
            return DOWN;
        }
    }

    private final Axis axis;
    private final int index;

    public Direction getOrto() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
        }
        return this;
    }

    private enum Axis {
        X, Y
    }

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

    public double getRotateAngle() {
        switch (this) {
            case LEFT:
                return Math.PI;
            case UP:
                return Math.PI / 2;
            case RIGHT:
                return 0;
            case DOWN:
                return -Math.PI / 2;
        }
        return 0;
    }

}
