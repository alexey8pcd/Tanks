package ru.ovcharov_alexey.tanks.v4.engine.geometry;

/**
@author Alexey
 */
public enum Direction {
    
    LEFT(Axis.X, 0), 
    UP(Axis.Y, 1), 
    RIGHT(Axis.X, 2), 
    DOWN(Axis.Y, 3);
    
    private final Axis axis;
    private final int index;

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

}
