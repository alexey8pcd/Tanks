package units.battle;

import actions.MoveAction;
import geometry.Drawable;
import units.battle.Attacker;
import geometry.GeometryMap;
import geometry.Movable;
import geometry.RelocatingShape;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class CombatUnit extends RelocatingShape
        implements Movable, Attacker, Drawable {

    private int damage;
    private int armor;
    private int health;

    public CombatUnit(int speed, int x, int y, int size,
            Direction direction, MoveAction moveAction) {
        super(speed, x, y, size, direction, moveAction);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
