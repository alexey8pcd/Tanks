package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Bonus extends GeometryShape implements Drawable{

    @Override
    public boolean isVisible() {
        return true;
    }


    private final BonusType bonusType;

    public Bonus(Color color, int x, int y, int size, BonusType bonusType) {
        super(x, y, size);
        this.bonusType = bonusType;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(bonusType.getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }

}
