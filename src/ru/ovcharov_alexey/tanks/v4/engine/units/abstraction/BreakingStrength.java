package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.awt.Color;

/**
 *
 * @author alex
 */
public enum BreakingStrength {

    BREAK_WOOD(Color.GREEN, 1),
    BREAK_BRICKS(Color.YELLOW, 2),
    BREAK_ARMOR(Color.RED, 3);

    private final Color color;
    private final int power;

    public boolean isBreak(Material material) {
        return power >= material.getHardness();
    }

    public Color getColor() {
        return color;
    }

    private BreakingStrength(Color color, int power) {
        this.color = color;
        this.power = power;
    }

    public static BreakingStrength typeOf(int power) {
        for (BreakingStrength value : values()) {
            if (value.power == power) {
                return value;
            }
        }
        throw new IllegalArgumentException("Не найден тип " + power);
    }

    public int getPower() {
        return power;
    }

}
