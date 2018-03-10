package ru.ovcharov_alexey.tanks.v4.engine.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;

/**
 *
 * @author Alexey Ovcharov
 */
public class Scene {

    private final GeometryMap geometryMap;
    private final Collection<Shape> shapes = new ArrayList<>();

    public Scene(GeometryMap geometryMap) {
        this.geometryMap = geometryMap;
    }

    public GeometryMap getGeometryMap() {
        return geometryMap;
    }

    public boolean isSlowMove(Shape shape, Material slowMoveMaterial) {
        int tileSize = geometryMap.getTileSize();
        boolean slowMove = false;
        float leftX = shape.getX();
        float rightX = shape.getRightX();
        float topY = shape.getY();
        float downY = shape.getDownY();
        for (float x = leftX; x < rightX;) {
            for (float y = topY; y < downY;) {
                Material material = geometryMap.getTile((int) x, (int) y);
                if (material == slowMoveMaterial) {
                    slowMove = true;
                    break;
                }
                if (y >= downY - tileSize) {
                    ++y;
                } else {
                    y += tileSize;
                }
            }
            if (x >= rightX - tileSize) {
                ++x;
            } else {
                x += tileSize;
            }
        }
        return slowMove;
    }

    public boolean isCollisionDetected(Shape shape, EnumSet<Material> impassable) {
        int tileSize = geometryMap.getTileSize();
        float dRightX = shape.getRightX();
        float dDownY = shape.getDownY();
        float dLeftX = shape.getX();
        float dTopY = shape.getY();
        for (float x = dLeftX; x < dRightX;) {
            for (float y = dTopY; y < dDownY;) {
                Material material = geometryMap.getTile((int) x, (int) y);
                if (impassable.contains(material)) {
                    return true;
                }
                if (y >= dDownY - tileSize) {
                    ++y;
                } else {
                    y += tileSize;
                }
            }
            if (x >= dRightX - tileSize) {
                ++x;
            } else {
                x += tileSize;
            }
        }
        return false;
    }

    public boolean isUnitsCollisionDetected(Shape target, Shape me) {
        boolean detected = false;
        for (Shape shape : shapes) {
            if (shape != me && shape.intersectsWith(target)) {
                detected = true;
                break;
            }
        }
        return detected;
    }

    public boolean into(Shape shape) {
        float mapWidth = (float) geometryMap.getWidth();
        float mapHeight = (float) geometryMap.getHeight();
        float leftX = shape.getX();
        float rightX = shape.getRightX();
        float topY = shape.getY();
        float downY = shape.getDownY();
        return !(leftX < 0.f || rightX >= mapWidth || topY < 0.f || downY >= mapHeight);
    }

    public void addShapes(Collection<? extends Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        this.shapes.remove(shape);
    }

}
