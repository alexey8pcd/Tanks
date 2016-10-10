package ru.ovcharov_alexey.tanks.v4.engine.physics;

import java.awt.Color;

/**
 * Представляет материал для формирования объектов карты. Имеет цвет и
 * твердость.
 */
public enum Material {
    TERRA(0, Color.WHITE, 0), //по-умолчанию, можно установить цвет фона
    BRICK(2, new Color(0xff, 0x88, 0), 1), //коричневый
    ARMOR(3, Color.GRAY, 2), WATER(0, Color.BLUE, 3), 
    WOOD(1, Color.GREEN, 4), 
    ICE(0, Color.CYAN, 5);
    private final int hardness; //твердость
    private final Color color;
    private final int code;
    private static final int MIN_CODE = 0;
    private static final int MAX_CODE = 5;

    private Material(int hardness, Color color, int code) {
        this.hardness = hardness;
        this.color = color;
        this.code = code;
    }

    public int getHardness() {
        return hardness;
    }

    public Color getColor() {
        return color;
    }

    public int getCode() {
        return code;
    }

    public static Material getMaterial(int code) {
        if (code < MIN_CODE || code > MAX_CODE) {
            code = MIN_CODE;
        }
        for (Material m : Material.values()) {
            if (m.code == code) {
                return m;
            }
        }
        return TERRA;
    }

}
