package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.ShellPool;

/**
 * @author Alexey
 */
public class AttackShells implements AttackAction {

    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container, CombatUnit attackable) {
        Shell shell = ShellPool.getInstance().take();
        int halfWidth = attacker.getWidth() / 2;
        int halfHeight = attacker.getHeight() / 2;
        int dx = halfWidth;
        int dy = halfHeight;
        Vector2D direction = attacker.getDirection();
        Direction approximate = Direction.approximate(direction);
        switch (approximate) {
            case LEFT:
                dx -= halfWidth;
                break;
            case UP:
                dy -= halfHeight;
                break;
            case RIGHT:
                dx += halfWidth;
                break;
            case DOWN:
                dy += halfHeight;
        }
        shell.setLocation(attacker.getX() + dx, attacker.getY() + dy);
        shell.setBreakingStrength(attacker.getBreakingStrength());
        shell.setDirection(Vector2D.create(approximate, Shell.SHELL_SPEED));
        shell.setDamage(attacker.getDamage());
        container.add(shell);
    }

    @Override
    public boolean isRepeated() {
        return true;
    }

}
