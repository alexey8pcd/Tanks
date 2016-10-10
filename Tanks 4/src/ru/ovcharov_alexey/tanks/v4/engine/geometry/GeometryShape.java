package ru.ovcharov_alexey.tanks.v4.engine.geometry;

import java.util.Objects;

/**
 * Представляет базовый двумерный объект с длиной и шириной, координатами левого
 * верхнего угла
 *
 * @author alex
 */
public abstract class GeometryShape implements Shape{

    protected GeometryPoint point;//левый верхний угол геометрической фигуры
    protected int width;
    protected int height;

    @Override
    public int getX() {
        return point.x;
    }

    public void setX(int x) {
        this.point.x = x;
    }

    @Override
    public int getY() {
        return point.y;
    }

    public void setY(int y) {
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

    public GeometryShape(int x, int y, int width, int height) {
        this.point = new GeometryPoint(x, y);
        this.width = width;
        this.height = height;
    }

    public GeometryShape(int x, int y, int size) {
        this.point = new GeometryPoint(x, y);
        this.width = size;
        this.height = size;
    }

    public GeometryShape(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public GeometryShape(int size){
        this.width = size;
        this.height = size;
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
