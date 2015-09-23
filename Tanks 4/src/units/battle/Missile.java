package units.battle;

import actions.BreakingStraightMove;
import actions.MoveAction;
import geometry.Drawable;
import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import geometry.RelocatingShape;
import java.awt.Graphics;
import java.util.EnumSet;

/**
 *
 * @author alex
 */
public class Missile extends RelocatingShape implements Movable, Drawable {

    private final int damage;
    private boolean alive;
    private final BreakingStrength breakingStrength;

    public Missile(int x, int y, int size, int speed, int damage,
            BreakingStrength breakingStrength, Direction direction) {
        //снаряд не может пролететь через лес, кирпич и броню
        super(speed, x, y, size, direction, new BreakingStraightMove(
                EnumSet.of(Material.ARMOR, Material.BRICK, Material.WOOD)));
        this.damage = damage;
        this.breakingStrength = breakingStrength;
        alive = true;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public BreakingStrength getBreakingStrength() {
        return breakingStrength;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
