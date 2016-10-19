package ru.ovcharov_alexey.tanks.v4.engine.geometry;

/**
 * Представляет пару координат (x;y), заданную целыми числами
 * 
 * @author alex
 */
public class GeometryPoint {

    float x;
    float y;

    public GeometryPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Если сравниваемый объект имеет тип <code>GeometryPoint</code>,
     * сравнение происходит по координатма
     * @param obj - объект для сравнения
     * @return 
     */
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
        GeometryPoint geometryPoint = (GeometryPoint) obj;
        return geometryPoint.x == x && geometryPoint.y == y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (int) (67 * hash + this.x);
        hash = (int) (67 * hash + this.y);
        return hash;
    }

}
