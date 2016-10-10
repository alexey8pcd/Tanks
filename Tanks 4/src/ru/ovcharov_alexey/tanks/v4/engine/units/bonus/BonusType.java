package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import java.awt.Color;

/**
@author Alexey
 */
public enum BonusType {
    POWER_UP(1, Color.RED), //увеличение скорости стрельбы и пробивание брони
    STOP_ENEMIES(2, Color.ORANGE), //остановка вражеских юнитов
    ARMOR_UP(3, Color.PINK);
    //усиление брони
    private final int value;
    private final Color color;

    private BonusType(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

}
