package units.battle;

import actions.MoveAction;
import geometry.Drawable;
import geometry.Movable;
import geometry.RelocatingShape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import units.LiveAndDeath;

/**
 *
 * @author alex
 */
public class CombatUnit extends RelocatingShape
        implements Movable, Attacker, Drawable, LiveAndDeath {

    private int damage;
    private int armor;
    private int health;
    private final int maxHealth;
    private BreakingStrength breakingStrength;
    public static final int MIN_HEALTH = 0;

    public static enum UnitSpeed {

        VERY_SLOW(1),
        LOW(2),
        NORMAL(4),
        HIGH(6),
        VERY_HIGH(8);
        private final int value;

        private UnitSpeed(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public CombatUnit(int speed, int x, int y, int size,
            Direction direction, MoveAction moveAction,
            int maxHealth) {
        super(speed, x, y, size, direction, moveAction);
        this.maxHealth = maxHealth;
        this.breakingStrength = BreakingStrength.BREAK_BRICKS;
    }

    public void setBreakingStrength(BreakingStrength breakingStrength) {
        this.breakingStrength = breakingStrength;
    }

    public BreakingStrength getBreakingStrength() {
        return breakingStrength;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void draw(Graphics g) {
        int x = getX();
        int y = getY();
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        switch (getDirection()) {
            case LEFT:
                g.fillRect(x, y + height / 2, width / 4, height / 4);
                break;
            case RIGHT:
                g.fillRect(x + width, y + height / 2, width / 4, height / 4);
                break;
            case UP:
                g.fillRect(x + width / 2, y, width / 4, height / 4);
                break;
            case DOWN:
                g.fillRect(x + width / 2, y + height, width / 4, height / 4);
        }
    }

    @Override
    public void attack(List<Shell> shells) {
        Shell shell = ShellPool.getInstance().take();
        shell.setLocation(getX() + getWidth() / 2, getY() + getHeight() / 2);
        shell.setBreakingStrength(breakingStrength);
        shell.setDirection(getDirection());
        shells.add(shell);
    }

    @Override
    public boolean isLive() {
        return health > MIN_HEALTH;
    }

    @Override
    public void setLive(boolean alive) {
        health = alive ? maxHealth : MIN_HEALTH;
    }

}
