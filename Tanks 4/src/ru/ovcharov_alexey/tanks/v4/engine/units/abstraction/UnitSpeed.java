package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public enum UnitSpeed {

    VERY_SLOW(1),
    SLOW(2),
    NORMAL(4),
    FAST(6),
    VERY_FAST(8);

    public static UnitSpeed typeOf(int speed) {
        for (UnitSpeed unitSpeed : values()) {
            if (unitSpeed.value == speed) {
                return unitSpeed;
            }
        }
        throw new IllegalArgumentException("Тип " + speed + " не найден");
    }

    private final int value;

    private UnitSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
