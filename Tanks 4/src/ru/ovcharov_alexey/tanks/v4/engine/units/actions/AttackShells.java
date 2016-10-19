package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.ShellPool;

/**
@author Alexey
*/
public class AttackShells implements AttackAction {

    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container, CombatUnit attackable) {
        Shell shell = ShellPool.getInstance().take();
        shell.setLocation(attacker.getX() + attacker.getWidth() / 2, 
                attacker.getY() + attacker.getHeight() / 2);
        shell.setBreakingStrength(attacker.getBreakingStrength());
        shell.setDirection(attacker.getDirection());
        shell.setDamage(attacker.getDamage());
        container.add(shell);
    }

}
