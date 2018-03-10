package ru.ovcharov_alexey.tanks.v4.engine.geometry;

/**
 * @author Alexey
 */
public interface Shape {

    int getHeight();

    int getWidth();

    float getX();

    float getY();

    default boolean containsPoint(float dRightX, float dDownY) {
        float shapeRightX = dRightX;
        float shapeDownY = dDownY;
        int rightX = (int) (getX() + getWidth());
        int downY = (int) (getY() + getHeight());
        return !(getY() > shapeDownY || downY < dDownY
                || rightX < dRightX || getX() > shapeRightX);
    }

    default boolean intersectsWith(Shape shape) {
        int shapeRightX = (int) (shape.getX() + shape.getWidth());
        int shapeDownY = (int) (shape.getY() + shape.getHeight());
        int rightX = (int) (getX() + getWidth());
        int downY = (int) (getY() + getHeight());
        return !(getY() > shapeDownY || downY < shape.getY()
                || rightX < shape.getX() || getX() > shapeRightX);
    }

    default float getRightX() {
        return getX() + getWidth();
    }

    default float getDownY() {
        return getY() + getHeight();
    }

    default float getCenterX() {
        return getX() + getWidth() / 2;
    }

    default float getCenterY() {
        return getY() + getHeight() / 2;
    }

    default boolean nearWith(Shape attackable, float delta) {
        float centerX = getCenterX();
        float centerY = getCenterY();
        float centerXAtt = attackable.getCenterX();
        float centerYAtt = attackable.getCenterY();
        float dx = centerX - centerXAtt;
        float dy = centerY - centerYAtt;
        return dx * dx + dy * dy < delta * delta;
    }
}
