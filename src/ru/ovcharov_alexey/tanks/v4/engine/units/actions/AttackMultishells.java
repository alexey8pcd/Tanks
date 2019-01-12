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
public class AttackMultishells implements AttackAction {

    private static final double ANGLE = Math.PI / 90;
    private static final double ANGLE2 = Math.PI / 45;

    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container, CombatUnit attackable) {
        Shell shellC = ShellPool.getInstance().take();
        Shell shellUp = ShellPool.getInstance().take();
        Shell shellUp2 = ShellPool.getInstance().take();
        Shell shellDown = ShellPool.getInstance().take();
        Shell shellDown2 = ShellPool.getInstance().take();
        int halfWidth = attacker.getWidth() / 2;
        int halfHeight = attacker.getHeight() / 2;
        int dx = halfWidth;
        int dy = halfHeight;
        Direction approximate = Direction.approximate(attacker.getDirection());
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
        float locX = attacker.getX() + dx;
        float locY = attacker.getY() + dy;
        shellC.setSpeed(Shell.SHELL_SPEED);
        shellDown.setSpeed(Shell.SHELL_SPEED);
        shellDown2.setSpeed(Shell.SHELL_SPEED);
        shellUp.setSpeed(Shell.SHELL_SPEED);
        shellUp2.setSpeed(Shell.SHELL_SPEED);
        
        
        shellC.setLocation(locX, locY);
        shellDown.setLocation(locX, locY);
        shellDown2.setLocation(locX, locY);
        shellUp.setLocation(locX, locY);
        shellUp2.setLocation(locX, locY);

        shellC.setBreakingStrength(attacker.getBreakingStrength());
        shellDown.setBreakingStrength(attacker.getBreakingStrength());
        shellDown2.setBreakingStrength(attacker.getBreakingStrength());
        shellUp.setBreakingStrength(attacker.getBreakingStrength());
        shellUp2.setBreakingStrength(attacker.getBreakingStrength());

        shellC.setDamage(attacker.getDamage());
        shellDown.setDamage(attacker.getDamage() / 2);
        shellDown2.setDamage(attacker.getDamage() / 2);
        shellUp.setDamage(attacker.getDamage() / 2);
        shellUp2.setDamage(attacker.getDamage() / 2);

        shellC.setDirection(Vector2D.create(approximate));

        double alphaUp, alphaDown, alphaUp2, alphaDown2;
        alphaUp = Math.PI - ANGLE;
        alphaUp2 = Math.PI - ANGLE2;
        alphaDown = ANGLE - Math.PI;
        alphaDown2 = ANGLE2 - Math.PI;
        
        Vector2D directionUp = calculateDirection(alphaUp);
        shellUp.setDirection(directionUp.rotate(approximate.getRotateAngle()));

        Vector2D directionUp2 = calculateDirection(alphaUp2);
        shellUp2.setDirection(directionUp2.rotate(approximate.getRotateAngle()));
        
        Vector2D directionDown = calculateDirection(alphaDown);
        shellDown.setDirection(directionDown.rotate(approximate.getRotateAngle()));
        
        Vector2D directionDown2 = calculateDirection(alphaDown2);
        shellDown2.setDirection(directionDown2.rotate(approximate.getRotateAngle()));

        container.add(shellC);
        container.add(shellUp);
        container.add(shellUp2);
        container.add(shellDown);
        container.add(shellDown2);

    }

    @Override
    public boolean isRepeated() {
        return true;
    }

    private Vector2D calculateDirection(double alphaUp) {
        double tanAlphaUp = Math.tan(alphaUp);
        double tan2AlphaUp = tanAlphaUp * tanAlphaUp;
        double i = Math.sqrt(Shell.SHELL_SPEED * Shell.SHELL_SPEED / (1 + tan2AlphaUp));
        double j = i * tanAlphaUp;
        Vector2D direction = new Vector2D(i, j);
        return direction;
    }

}
