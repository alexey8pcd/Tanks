package geometry;

/**
 * Представляет пару координат (x;y), заданную целыми числами
 * 
 * @author alex
 */
public class GeometryPoint {

    int x;
    int y;

    public GeometryPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

}
