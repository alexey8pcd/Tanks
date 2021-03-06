package ru.ovcharov_alexey.tanks.v4.engine.units.shell;

import java.awt.Color;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.BreakingStraightMove;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.physics.RelocatingShape;
import java.awt.Graphics2D;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Breaking;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;

/**
 * Снаряд, который поражает боевые единицы
 *
 * @author alex
 */
public class Shell extends RelocatingShape
        implements Drawable, Breaking, DamageDealer {

    private int damage;
    private boolean alive;
    private BreakingStrength breakingStrength;

    public static final int SHELL_SIZE = 4;
    public static final float SHELL_SPEED = 2f;
    //снаряд не может пролететь через лес, кирпич и броню, но может их разрушить
    private static final MoveAction MOVE_ACTION = new BreakingStraightMove(
            EnumSet.of(Material.METAL, Material.BRICKS));
//            EnumSet.of(Material.METAL, Material.BRICKS, Material.FOREST));
    private boolean critical;

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

    @Override
    public boolean canRandomChangeDirection() {
        return false;
    }

    @Override
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
    public void draw(Graphics2D g) {
        g.setColor(breakingStrength.getColor());
        g.fillOval((int) getX(), (int) getY(), SHELL_SIZE, SHELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval((int) getX(), (int) getY(), SHELL_SIZE, SHELL_SIZE);
    }

    @Override
    public boolean isLive() {
        return alive;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public boolean isFixedPosition() {
        return false;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void kill() {
        alive = false;
    }

    @Override
    public void restore() {
        alive = true;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public boolean isCritical() {
        return critical;
    }

}
