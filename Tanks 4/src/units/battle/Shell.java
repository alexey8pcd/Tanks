package units.battle;

import actions.BreakingStraightMove;
import actions.MoveAction;
import geometry.Drawable;
import geometry.GeometryMap.Material;
import geometry.Movable;
import geometry.RelocatingShape;
import java.awt.Graphics;
import java.util.EnumSet;

/**
 * Снаряд, который поражает боевые единицы
 *
 * @author alex
 */
public class Shell extends RelocatingShape
        implements Movable, Drawable, Breaking {

    private final int damage;
    private boolean alive;
    private BreakingStrength breakingStrength;
    
    public static final int SHELL_SIZE = 2;
    public static final int SHELL_SPEED = 1;
    //снаряд не может пролететь через лес, кирпич и броню, но может их разрушить
    private static final MoveAction MOVE_ACTION = new BreakingStraightMove(
            EnumSet.of(Material.ARMOR, Material.BRICK, Material.WOOD));

    public Shell(int x, int y, int size, int speed, int damage,
            BreakingStrength breakingStrength, Direction direction) {
        super(speed, x, y, size, direction, MOVE_ACTION);
        this.damage = damage;
        this.breakingStrength = breakingStrength;
        alive = true;
    }

    /**
     * Создает новый снаряд с координатами (0;0), шириной и высотой по
     * умолчанию, скоростью по умолчанию, уроном 0, разрушающей способность:
     * <code>BreakingStrength.BREAK_BRICKS</code>, в направлении
     * <code>Direction.RIGHT</code>
     */
    public Shell() {
        super(SHELL_SPEED, 0, 0, SHELL_SIZE, Direction.RIGHT, MOVE_ACTION);
        breakingStrength = BreakingStrength.BREAK_BRICKS;
        alive = true;
        damage = 0;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void setBreakingStrength(BreakingStrength breakingStrength) {
        this.breakingStrength = breakingStrength;
    }
    
    @Override
    public BreakingStrength getBreakingStrength() {
        return breakingStrength;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(breakingStrength.getColor());
        g.fillRect(getX(), getY(), SHELL_SIZE, SHELL_SIZE);
    }

    @Override
    public boolean isLive() {
        return alive;
    }

    @Override
    public void setLive(boolean alive) {
        this.alive = alive;
    }

}
