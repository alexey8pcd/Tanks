package ru.ovcharov_alexey.tanks.v4.engine.units.shell;

import java.awt.Graphics2D;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;

/**
 * Невидимая бомба, которая взрывается, когда к ней приближается боевая единица
 *
 * @author alex
 */
public class InvisibleBomb extends GeometryShape implements DamageDealer {

    private final int damage;
    private boolean alive;

    @Override
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
        this.damage = damage;
        alive = true;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public boolean isFixedPosition() {
        return true;
    }

}
