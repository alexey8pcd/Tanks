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

    public AttackShells() {
    }

    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container) {
        Shell shell = ShellPool.getInstance().take();
        shell.setLocation(attacker.getX() + attacker.getWidth() / 2, 
                attacker.getY() + attacker.getHeight() / 2);
        shell.setBreakingStrength(attacker.getBreakingStrength());
        shell.setDirection(attacker.getDirection());
        container.add(shell);
    }

}
