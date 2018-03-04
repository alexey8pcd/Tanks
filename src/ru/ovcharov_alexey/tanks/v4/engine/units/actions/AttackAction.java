package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;

/**
 *
 * @author alex
 */
public interface AttackAction {

    void attack(CombatUnit attacker, Collection<DamageDealer> container, CombatUnit attackable);

    boolean isRepeated();
}
