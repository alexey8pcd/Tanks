package units.battle;

import geometry.GeometryMap.Material;
import java.awt.Color;

/**
 *
 * @author alex
 */
public enum BreakingStrength {

    BREAK_WOOD(Color.GREEN, 1),
    BREAK_BRICKS(Color.RED, 2),
    BREAK_ARMOR(Color.BLUE, 3);

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

}
