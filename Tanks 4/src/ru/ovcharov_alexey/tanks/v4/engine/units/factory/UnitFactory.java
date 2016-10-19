package ru.ovcharov_alexey.tanks.v4.engine.units.factory;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.DrawerFactory;
import static ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength.*;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import static ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitSpeed.*;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnitBuilder;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;

/**
 *
 * @author alex
 */
public class UnitFactory {

    public static CombatUnit createPlayerUnit() {
        return new CombatUnitBuilder().setArmor(40).
                setMoveAction(MoveActionFactory.UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                setBreakingStrength(BREAK_BRICKS).setMoveSpeed(SLOW).
                setDrawer(DrawerFactory.getPlayerUnitDrawer()).
                setDamage(50).setMaxHealth(100).setType(UnitType.TANK).createCombatUnit();
    }

    public static CombatUnit createEnemyUnit(UnitType unitType) {
        switch (unitType) {
            case LIGHT_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(0).
                        setMoveAction(MoveActionFactory.UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_WOOD).
                        setMoveSpeed(VERY_FAST).
                        setDamage(33).setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case MIDDLE_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(20).
                        setMoveAction(MoveActionFactory.UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_BRICKS).setMoveSpeed(FAST).
                        setDamage(50).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case HEAVY_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(40).
                        setMoveAction(MoveActionFactory.UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(SLOW).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setDamage(66).setMaxHealth(100).setType(unitType).createCombatUnit();
            case TANK:
                return new CombatUnitBuilder().setArmor(60).
                        setMoveAction(MoveActionFactory.UNIT_SIMPLE_SEARCH_MOVE).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setDamage(70).setMaxHealth(200).setType(unitType).createCombatUnit();
            case FOCUSED_BLASTING:
                return new CombatUnitBuilder().setArmor(80).
                        setMoveAction(MoveActionFactory.UNIT_SIMPLE_SEARCH_MOVE).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_EXPLOSION).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).setDamage(100).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case DOUBLE_WEAPON_VEHICLE:
                return new CombatUnitBuilder().setArmor(30).
                        setMoveAction(MoveActionFactory.UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(AttackActionFactory.UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDamage(45).setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(140).setType(unitType).createCombatUnit();
            default:
                throw new AssertionError(unitType.name());
        }
    }

    public static CombatUnit[] createSeveral(UnitType unitType, int amount) {
        return null;
    }

}
