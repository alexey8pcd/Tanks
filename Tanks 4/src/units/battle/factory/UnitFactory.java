package units.battle.factory;

import static actions.AttackAction.*;
import static actions.MoveAction.*;
import geometry.drawers.DrawerFactory;
import static units.battle.BreakingStrength.*;
import units.battle.CombatUnit;
import static units.UnitSpeed.*;
import units.battle.CombatUnitBuilder;
import units.battle.UnitType;

/**
 *
 * @author alex
 */
public class UnitFactory {

    public static CombatUnit create(UnitType unitType) {
        switch (unitType) {
            case LIGHT_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(0).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_WOOD).
                        setMoveSpeed(VERY_FAST).
                        setDamage(50).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case MIDDLE_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(20).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_BRICKS).setMoveSpeed(FAST).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case HEAVY_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(40).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(SLOW).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case TANK:
                return new CombatUnitBuilder().setArmor(60).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(200).setType(unitType).createCombatUnit();
             case FOCUSED_BLASTING:
                break;
            case DOUBLE_WEAPON_VEHICLE:
                break;
            default:
                throw new AssertionError(unitType.name());

        }

        return null;
    }

    public static CombatUnit[] createSeveral(UnitType unitType, int amount) {
        return null;
    }

}
