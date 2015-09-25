package units.battle;

import actions.AttackAction;
import actions.MoveAction;
import geometry.Drawable;
import geometry.Movable;
import geometry.RelocatingShape;
import geometry.drawers.RelocatingShapeDrawer;
import java.awt.Graphics;
import java.util.Collection;

/**
 *
 * @author alex
 */
public class CombatUnit extends RelocatingShape
        implements Movable, Attacker, Drawable, LiveAndDeath {

    private int damage;
    private int armor;
    private int currentHealth;
    private final UnitType type;
    private final int maxHealth;
    private AttackAction attackAction;
    private RelocatingShapeDrawer drawer;
    private BreakingStrength breakingStrength;
    public static final int MIN_HEALTH = 0;
    public static final int UNIT_SIZE = 24;

    @Override
    public void attack(Collection<DDamage> container) {
        attackAction.attack(this, container);
    }

    @Override
    public boolean isVisible() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

    public CombatUnit(UnitSpeed unitSpeed, UnitType type, int x, int y, int maxHealth,
            BreakingStrength breakingStrength,
            MoveAction moveAction,
            AttackAction attackAction,
            RelocatingShapeDrawer drawer) {

        super(unitSpeed.getValue(), x, y, UNIT_SIZE, Direction.RIGHT, moveAction);
        this.type = type;
        this.maxHealth = maxHealth;
        this.breakingStrength = breakingStrength;
        this.attackAction = attackAction;
        this.drawer = drawer;
    }

    public RelocatingShapeDrawer getDrawer() {
        return drawer;
    }

    public void setDrawer(RelocatingShapeDrawer drawer) {
        this.drawer = drawer;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public AttackAction getAttackAction() {
        return attackAction;
    }

    @Override
    public void setAttackAction(AttackAction attackAction) {
        this.attackAction = attackAction;
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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public UnitType getType() {
        return type;
    }

    @Override
    public void draw(Graphics g) {
        drawer.drawUnit(this, g);
        /* для тестирования
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
         */
    }


    @Override
    public boolean isLive() {
        return currentHealth > MIN_HEALTH;
    }

    @Override
    public void setLive(boolean alive) {
        currentHealth = alive ? maxHealth : MIN_HEALTH;
    }

}
