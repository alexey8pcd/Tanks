package units.battle;

import actions.MoveAction;
import geometry.Drawable;
import geometry.Movable;
import geometry.RelocatingShape;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author alex
 */
public class CombatUnit extends RelocatingShape
        implements Movable, Attacker, Drawable {

    private int damage;
    private int armor;
    private int health;
    private BreakingStrength breakingStrength;

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
    public void attack(List<Shell> shells) {
        Shell shell = ShellPool.getInstance().take();
        shell.setLocation(getX() + getWidth() / 2, getY() + getHeight() / 2);
        shell.setBreakingStrength(breakingStrength);
        shell.setDirection(getDirection());
        shells.add(shell);
    }

}
