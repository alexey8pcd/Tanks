package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.InvisibleBomb;

/**
 * @author Alexey
 */
public class AttackExplosions implements AttackAction {

    private static final float DELTA = CombatUnit.UNIT_SIZE * 1.5f;

    @Override
    public void attack(CombatUnit attacker, Collection<DamageDealer> container,
            CombatUnit attackable) {
        if (attacker.nearWith(attackable, DELTA)) {
            int damage = attacker.getDamage();
            if (damage < 1) {
                damage = 1;
            }
            container.add(new InvisibleBomb((int) attacker.getX(), (int) attacker.getY(),
                    attackable.getWidth(), damage));
            attacker.setHealth(CombatUnit.NOT_HEALTH);
        }
    }

    @Override
    public boolean isRepeated() {
        return false;
    }

}
