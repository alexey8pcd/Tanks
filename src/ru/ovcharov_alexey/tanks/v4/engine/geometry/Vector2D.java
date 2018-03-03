package ru.ovcharov_alexey.tanks.v4.engine.geometry;

/**
 * @author Alexey
 */
public class Vector2D {

    public static Vector2D NULL = new Vector2D(0, 0);

    public static Vector2D create(Direction direction, float speed) {
        float i = 0, j = 0;
        switch (direction) {
            case DOWN:
                i = 0;
                j = -speed;
                break;
            case LEFT:
                i = -speed;
                j = 0;
                break;
            case RIGHT:
                i = speed;
                j = 0;
                break;
            case UP:
                i = 0;
                j = speed;
        }
        return new Vector2D(i, j);
    }

    private final float i;
    private final float j;

    public Vector2D(float i, float j) {
        this.i = i;
        this.j = j;
    }

    public Vector2D(double i, double j) {
        this.i = (float) i;
        this.j = (float) j;
    }

    public float getI() {
        return i;
    }

    public float getJ() {
        return j;
    }

    public Vector2D rotate(double rotateAngle) {
        double cosR = Math.cos(rotateAngle);
        double sinR = Math.sin(rotateAngle);
        double ii = i * cosR - j * sinR;
        double jj = i * sinR + j * cosR;
        return new Vector2D(ii, jj);
    }

}
