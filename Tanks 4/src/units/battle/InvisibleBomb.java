package units.battle;

import geometry.GeometryShape;

/**
 * Невидимая бомба, которая взрывается, когда к ней приближается боевая единица
 *
 * @author alex
 */
public class InvisibleBomb extends GeometryShape {

    private int damage;
    private boolean alive;

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public InvisibleBomb(int x, int y, int size, int damage) {
        super(x, y, size);
        alive = true;
    }

}
