package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import java.util.Random;

/**
 * @author Alexey
 */
public enum BonusType {
    POWER_UP(1), //увеличение скорости стрельбы и пробивание брони
    STOP_ENEMIES(2), //остановка вражеских юнитов
    ARMOR_UP(3),
    REPAIR(4),
    SWIM(5);

    //усиление брони
    private final int value;

    private BonusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BonusType randomType(Random random) {
        int next = random.nextInt(values().length);
        return values()[next];
    }

}
