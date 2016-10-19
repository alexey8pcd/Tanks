package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.InvisibleBomb;

/**
 * @author Alexey
 */
public class AttackExplosions implements AttackAction {
    
    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container,
            CombatUnit attackable) {
        if (attacker.intersectsWith(attackable)) {
            int damage = attackable.getCurrentHealth() - 1;
            if(damage < 1){
                damage = 1;
            }
            container.add(new InvisibleBomb((int)attacker.getX(), (int)attacker.getY(), 
                    attackable.getWidth(), damage));
            attacker.setCurrentHealth(CombatUnit.NOT_HEALTH);
        }
    }
    
}
