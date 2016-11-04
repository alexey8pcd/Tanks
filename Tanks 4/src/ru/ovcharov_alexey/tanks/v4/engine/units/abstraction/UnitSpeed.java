package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public enum UnitSpeed {

    VERY_SLOW(0.5f),
    SLOW(0.75f),
    NORMAL(1f),
    FAST(1.25f),
    VERY_FAST(1.5f);

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
