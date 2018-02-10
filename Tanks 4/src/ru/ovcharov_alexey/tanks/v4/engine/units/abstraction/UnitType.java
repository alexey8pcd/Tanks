package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

import java.util.Random;

/**
 *
 * @author alex
 */
public enum UnitType {

    LIGHT_COMBAT_VEHICLE(1, "Легкая боевая машина", 1.f),
    MIDDLE_COMBAT_VEHICLE(2, "Средняя боевая машина", 1.5f),
    HEAVY_COMBAT_VEHICLE(3, "Тяжелая боевая машина", 2.f),
    TANK(4, "Танк", 3.f),
    FOCUSED_BLASTING(5, "Фугас", 2.5f),
    DOUBLE_WEAPON_VEHICLE(6, "Двухорудийная машина", 2.2f);

    private final int type;
    private final String name;
    private final float experienceFactor;

    public static UnitType typeOf(int type) {
        for (UnitType value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new IllegalArgumentException("Тип " + type + " не существует");
    }

    public static UnitType typeOf(String name) {
        for (UnitType value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Тип с названием " + name + " не существует");
    }

    private UnitType(int type, String name, float experienceFactor) {
        this.type = type;
        this.name = name;
        this.experienceFactor = experienceFactor;
    }

    public float getExperienceFactor() {
        return experienceFactor;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public static UnitType randomType(Random random) {
        if (random == null) {
            random = new Random();
        }
        int type = random.nextInt(values().length) + 1;
        return typeOf(type);
    }

}
