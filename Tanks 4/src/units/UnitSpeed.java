package units;

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
    private final int value;

    private UnitSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
