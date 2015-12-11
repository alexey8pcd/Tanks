package actions;

import java.util.Collection;
import units.battle.CombatUnit;
import units.battle.DamageDealer;
import units.battle.Shell;
import units.battle.ShellPool;

/**
 *
 * @author alex
 */
public interface AttackAction {

    public static final AttackAction UNIT_ATTACK_ACTION_WITH_SHELLS = 
            (CombatUnit attacker, Collection<DamageDealer> container) -> {
        Shell shell = ShellPool.getInstance().take();
        shell.setLocation(attacker.getX() + attacker.getWidth() / 2,
                attacker.getY() + attacker.getHeight() / 2);
        shell.setBreakingStrength(attacker.getBreakingStrength());
        shell.setDirection(attacker.getDirection());
        container.add(shell);
    };

    public void attack(CombatUnit attacker, Collection<DamageDealer> container);
}
