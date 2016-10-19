package ru.ovcharov_alexey.tanks.v4.engine.units.factory;

import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackExplosions;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackShells;

/**
 * @author Alexey
 */
public class AttackActionFactory {

    public static final AttackAction UNIT_ATTACK_ACTION_WITH_SHELLS = new AttackShells();
    public static final AttackAction UNIT_ATTACK_ACTION_EXPLOSION = new AttackExplosions();

    public static AttackAction createAttackAction(String className) {
        if (AttackShells.class.getCanonicalName().equals(className)) {
            return UNIT_ATTACK_ACTION_WITH_SHELLS;
        } else if (AttackExplosions.class.getCanonicalName().equals(className)) {
            return UNIT_ATTACK_ACTION_EXPLOSION;
        } else {
            throw new IllegalArgumentException("Класс " + className + " не найден");
        }
    }

}
