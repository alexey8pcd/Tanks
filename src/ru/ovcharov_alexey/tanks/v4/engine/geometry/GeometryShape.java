package ru.ovcharov_alexey.tanks.v4.engine.geometry;

import java.util.Objects;

/**
 * Представляет базовый двумерный объект с длиной и шириной, координатами левого
 * верхнего угла
 *
 * @author alex
 */
public class GeometryShape implements Shape {

    protected GeometryPoint point;//левый верхний угол геометрической фигуры
    protected int width;
    protected int height;

    @Override
    public String toString() {
        return "GeometryShape{" + "point=" + point + ", width=" + width + ", height=" + height + '}';
    }

    @Override
    public float getX() {
        return point.x;
    }

    public void setX(float x) {
        this.point.x = x;
    }

    @Override
    public float getY() {
        return point.y;
    }

    public void setY(float y) {
        this.point.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GeometryPoint getPoint() {
        return point;
    }

    public GeometryShape(float x, float y, int width, int height) {
        this.point = new GeometryPoint(x, y);
        this.width = width;
        this.height = height;
    }

    public GeometryShape(float rightX, float downY, float leftX, float topY) {
        this.point = new GeometryPoint(leftX, topY);
        this.width = Math.round(Math.abs(rightX - leftX));
        this.height = Math.round(Math.abs(topY - downY));
    }

    public GeometryShape(float x, float y, int size) {
        this.point = new GeometryPoint(x, y);
        this.width = size;
        this.height = size;
    }

    public GeometryShape(int width, int height) {
        this.width = width;
        this.height = height;
        this.point = new GeometryPoint(0, 0);
    }

    public GeometryShape(int size) {
        this.width = size;
        this.height = size;
        this.point = new GeometryPoint(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        GeometryShape geometryShape = (GeometryShape) obj;
        return geometryShape.point.equals(point)
                && geometryShape.width == width
                && geometryShape.height == height;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.point);
        hash = 17 * hash + this.width;
        hash = 17 * hash + this.height;
        return hash;
    }
    
    

}
