package units.battle;

import java.awt.Color;

/**
 *
 * @author alex
 */
public enum BreakingStrength {

    NO_BREAK(Color.GREEN),
    BREAK_BRICKS(Color.RED),
    BREAK_ARMOR(Color.BLUE);

    private final Color color;

    public Color getColor() {
        return color;
    }

    private BreakingStrength(Color color) {
        this.color = color;
    }

}
