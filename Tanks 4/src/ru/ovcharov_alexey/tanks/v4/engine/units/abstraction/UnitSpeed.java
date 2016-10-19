package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public enum UnitSpeed {

    VERY_SLOW(0.4f),
    SLOW(0.8f),
    NORMAL(1.2f),
    FAST(1.6f),
    VERY_FAST(2f);

    public static UnitSpeed typeOf(float speed) {
        for (UnitSpeed unitSpeed : values()) {
            if (unitSpeed.value - speed < 0.00001f) {
                return unitSpeed;
            }
        }
        throw new IllegalArgumentException("Тип " + speed + " не найден");
    }

    private final float value;

    private UnitSpeed(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

}
